package cfb.com.dailydevelopment5.example1.radar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cfb.com.dailydevelopment5.R;

/**
 * 行业对比雷达图效果View实现
 * Created by fengbincao on 2017/7/30.
 */

public class RadarView extends View {

    private static final String TAG = "RadarView";

    private Context mContext;

    // 雷达网中心坐标点
    private PointF mRadarPointCenter;

    public static final int EDGE_MODE_POLYGON = 1;              // 正N多边形模式
    public static final int EDGE_MODE_CIRCLE = 2;               // 圆形模式

    // 雷达图相关属性值
    private int mRadarLayers;                                   // 雷达图边框层级数
    private boolean mRotationEnable;                            // 是否开启旋转手势操作
    private int mRadarEdgeMode;                                 // 雷达图边缘模式(支持正N多边行以及圆形两种模式)
    private boolean mRadarEdgeLineEnable;                       // 是否绘制雷达图边框(默认开启)
    private int mRadarEdgeLineColor;                            // 雷达图边框线颜色值
    private float mRadarEdgeLineWidth;                          // 雷达图边框线宽度
    private int mVertexTextColor;                               // 雷达顶点文字颜色
    private float mVertexTextSize;                              // 雷达顶点文字大小
    private float mVertexTextOffset;                            // 雷达顶点文字偏移量
    private boolean mRadarViewSolid;                            // 雷达图数据部分是否是实心(默认是空心的)

    // 绘图相关元素
    private Path mRadarPath;

    private Paint mRadarLinePaint;                              // 绘制雷达图边框线(圆形或者正N边形)的paint,以及中心点到顶点的连线
    private Paint mLayerPaint;                                  // 绘制雷达图边框线的layer(后续可扩展不同层级的颜色)
    private Paint mRadarValuePaint;                             // 绘制不同组的指标数据的paint

    private TextPaint mVertexTextPaint;                         // 绘制雷达图顶点文字的paint
    private TextPaint mValueTextPaint;                          // 绘制雷达图上值文字的paint

    private Scroller mScroller;
    private GestureDetector mDetector;

    // 数据Model
    private List<RadarData> mRadarData;                         // 雷达图区域核心数Model
    private List<Integer> mLayerColor;                          // 雷达图边框layer颜色值

    // 雷达图顶点文字label
    private List<String> mVertexText;                           // 雷达图顶点的文字
    private String mMaxLengthVertexText;                        // 雷达图顶点文字长度最长的String

    // 最大指标值(非常的重要)
    private float mMaxValue;
    private int mMaxVertex;                                     // 雷达图顶点坐标数

    // 无数据时的错误提示文字
    private String mEmptyDataTips = "暂无数据";

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        initView();
    }

    /**
     * 初始化雷达图View自定义属性值
     *
     * @param attrs collection of attributes
     */
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.RadarView);
        mRadarLayers = typedArray.getInt(R.styleable.RadarView_radar_layer, 6);
        mRotationEnable = typedArray.getBoolean(R.styleable.RadarView_rotation_enable, true);
        mRadarEdgeMode = typedArray.getInt(R.styleable.RadarView_web_mode, EDGE_MODE_POLYGON);
        mRadarEdgeLineEnable = typedArray.getBoolean(R.styleable.RadarView_radar_line_enable, true);
        mRadarEdgeLineWidth = typedArray.getDimension(R.styleable.RadarView_radar_line_width, dp2px(1));
        mRadarEdgeLineColor = typedArray.getColor(R.styleable.RadarView_radar_line_color, 0xFF9E9E9E);
        mVertexTextColor = typedArray.getColor(R.styleable.RadarView_vertex_text_color, mRadarEdgeLineColor);
        mVertexTextSize = typedArray.getDimension(R.styleable.RadarView_vertex_text_size, dp2px(12));
        mVertexTextOffset = typedArray.getDimension(R.styleable.RadarView_vertex_text_offset, 0);
        mRadarViewSolid = typedArray.getBoolean(R.styleable.RadarView_rotation_enable, false);
        mMaxValue = typedArray.getFloat(R.styleable.RadarView_max_value, 0);
        typedArray.recycle();
    }

    /**
     * 绘图相关操作初始化
     */
    private void initView() {
        mRadarPath = new Path();

        mScroller = new Scroller(mContext);
        mDetector = new GestureDetector(mContext, new GestureListener());
        mDetector.setIsLongpressEnabled(false);

        // 绘图相关的paint
        mRadarLinePaint = new Paint();
        mLayerPaint = new Paint();
        mRadarValuePaint = new Paint();

        mRadarLinePaint.setAntiAlias(true);
        mLayerPaint.setAntiAlias(true);
        mRadarValuePaint.setAntiAlias(true);

        // 绘制文字相关的paint
        mVertexTextPaint = new TextPaint();
        mValueTextPaint = new TextPaint();

        mVertexTextPaint.setAntiAlias(true);
        mValueTextPaint.setAntiAlias(true);

        // 数据model初始化
        mRadarData = new ArrayList<>();
        mLayerColor = new ArrayList<>();
        initLayerColor();

        mAnimateUtil = new AnimateUtil(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        // 确定雷达网中心坐标点
        mRadarPointCenter = new PointF(w / 2, h / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRadarData.size() == 0) {
            mValueTextPaint.setTextSize(dp2px(16));
            float hintWidth = mValueTextPaint.measureText(mEmptyDataTips);
            canvas.drawText(mEmptyDataTips, mRadarPointCenter.x - hintWidth / 2, mRadarPointCenter.y, mValueTextPaint);
        } else {
            initPaint();
            calculateRadius();
            drawRadar(canvas);
            drawRadarData(canvas);
        }
    }

    /**
     * 增加一条雷达图数据结构的方法
     * @param data          雷达图value数据结构
     */
    public void addRadarData(RadarData data) {
        mRadarData.add(data);
        initRadarData(data);
        animateValue(2000, data);
    }

    /**
     * 初始化一组雷达图数据
     * @param data              雷达图value数据结构
     */
    private void initRadarData(RadarData data) {
        List<Float> value = data.getRadarValue();
        float max = Collections.max(value);
        float calc = max * 5 / 4;
        if (mMaxValue == 0 || mMaxValue < max) {
            mMaxValue = calc;
        }
        int valueSize = value.size();
        if (mMaxVertex < valueSize) {
            mMaxVertex = valueSize;
        }
        mAngle = 2 * Math.PI / mMaxVertex;
        initVertexText();
    }

    /**
     * 初始化 paint 相关的设置
     */
    private void initPaint() {
        // 雷达图边框线paint
        mRadarLinePaint.setStrokeWidth(mRadarEdgeLineWidth);
        mRadarLinePaint.setColor(mRadarEdgeLineColor);
        mRadarLinePaint.setStyle(Paint.Style.STROKE);

        // 雷达图顶点文字的paint
        mVertexTextPaint.setColor(mVertexTextColor);
        mVertexTextPaint.setTextSize(mVertexTextSize);
        mRadarValuePaint.setStrokeWidth(dp2px(1));

        // 雷达图边框线的layer的paint
        mLayerPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 计算最外层N边形的外接圆半径
     * 为顶点描述文字预留空间
     * 顶点描述文字和顶点之间也有一定的间距
     */
    private void calculateRadius() {
        if (mVertexText == null || mVertexText.size() == 0) {
            mRadius = Math.min(mRadarPointCenter.x, mRadarPointCenter.y) - mVertexTextOffset;
        } else {
            float maxVertexTextWidth = mVertexTextPaint.measureText(mMaxLengthVertexText);
            if (mVertexTextOffset == 0) {
                Paint.FontMetrics fontMetrics = mVertexTextPaint.getFontMetrics();
                float textHeight = fontMetrics.descent - fontMetrics.ascent;
                mVertexTextOffset = (int) Math.sqrt(Math.pow(maxVertexTextWidth, 2) + Math.pow(textHeight, 2)) / 2;
                if (mVertexTextOffset < dp2px(15)) {
                    mVertexTextOffset = dp2px(15);
                }
            }
            // 最终半径值，是整体View的宽高值取小，然后减去最大顶点文字宽度以及预留的间距值(默认为15dp)
            mRadius = Math.min(mRadarPointCenter.x, mRadarPointCenter.y) - (maxVertexTextWidth + mVertexTextOffset);
            mPerimeter = 2 * Math.PI * mRadius;
        }
    }

    private void drawRadar(Canvas canvas) {
        if (mRadarEdgeMode == EDGE_MODE_POLYGON) {
            drawPolygonRadar(canvas);
        } else if (mRadarEdgeMode == EDGE_MODE_CIRCLE) {
            drawCircleRadar(canvas);
        }
        drawRadarLine(canvas);
    }

    /**
     * 绘制正N边行边框的雷达图
     */
    private void drawPolygonRadar(Canvas canvas) {
        for (int i = mRadarLayers; i >= 1; i--) {
            float radius = mRadius / mRadarLayers * i;
            int layerColor = mLayerColor.get(i - 1);
            mRadarPath.reset();
            for (int j = 0; j < mMaxVertex; j++) {
                double angleSin = Math.sin(mAngle * j + mRotateAngle);
                double angleCos = Math.cos(mAngle * j + mRotateAngle);
                float x = (float) (mRadarPointCenter.x + angleSin * radius);
                float y = (float) (mRadarPointCenter.y + angleCos * radius);
                if (j == 0) {
                    mRadarPath.moveTo(x, y);
                } else {
                    mRadarPath.lineTo(x, y);
                }
            }
            mRadarPath.close();
            // 设置为非 Color.TRANSPARENT 时，需要绘制layer部分
            if (layerColor != Color.TRANSPARENT) {
                mLayerPaint.setColor(layerColor);
                canvas.drawPath(mRadarPath, mLayerPaint);
            }
            // 绘制雷达图的边框
            if (mRadarEdgeLineEnable) {
                canvas.drawPath(mRadarPath, mRadarLinePaint);
            }
        }
    }

    /**
     * 绘制圆形边框的雷达图
     */
    private void drawCircleRadar(Canvas canvas) {
        for (int i = mRadarLayers; i >= 1; i--) {
            float radius = mRadius / mRadarLayers * i;
            int layerColor = mLayerColor.get(i - 1);
            if (layerColor != Color.TRANSPARENT) {
                mLayerPaint.setColor(layerColor);
                canvas.drawCircle(mRadarPointCenter.x, mRadarPointCenter.y, radius, mLayerPaint);
            }
            if (mRadarEdgeLineEnable) {
                canvas.drawCircle(mRadarPointCenter.x, mRadarPointCenter.y, radius, mRadarLinePaint);
            }
        }
    }

    /**
     * 绘制雷达图中心点到各个N边形顶点的连线，以及顶点值
     */
    private void drawRadarLine(Canvas canvas) {
        for (int i = 1; i <= mMaxVertex; i++) {
            double angleSin = Math.sin(mAngle * i + mRotateAngle);
            double angleCos = Math.cos(mAngle * i + mRotateAngle);
            drawVertexText(canvas, i, angleSin, angleCos);
            drawVertexLine(canvas, angleSin, angleCos);
        }
    }

    private void drawVertexLine(Canvas canvas, double angleSin, double angleCos) {
        if (!mRadarEdgeLineEnable) {
            return;
        }
        float x = (float) (mRadarPointCenter.x + angleSin * mRadius);
        float y = (float) (mRadarPointCenter.y + angleCos * mRadius);
        canvas.drawLine(mRadarPointCenter.x, mRadarPointCenter.y, x, y, mRadarLinePaint);
    }

    /**
     * 绘制雷达图的顶点值
     */
    private void drawVertexText(Canvas canvas, int index, double angleSin, double angleCos) {
        float x = (float) (mRadarPointCenter.x + angleSin * (mRadius + mVertexTextOffset));
        float y = (float) (mRadarPointCenter.y + angleCos * (mRadius + mVertexTextOffset));
        String vertexTextStr = mVertexText.get(index - 1);
        float textWidth = mVertexTextPaint.measureText(vertexTextStr);
        Paint.FontMetrics fontMetrics = mVertexTextPaint.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        canvas.drawText(vertexTextStr, x - textWidth / 2, y + textHeight / 4, mVertexTextPaint);
    }

    /**
     * 绘制雷达图核心区域的数据部分
     */
    private void drawRadarData(Canvas canvas) {
        for (int i = 0; i < mRadarData.size(); i++) {
            RadarData radarData = mRadarData.get(i);

            // 绘制信息初始化
            mRadarValuePaint.setColor(radarData.getRadarValueLineColor());

            // 具体值信息的paint
            mValueTextPaint.setTextSize(dp2px(radarData.getValueTextSize()));
            mValueTextPaint.setColor(radarData.getValueTextColor());

            List<Float> values = radarData.getRadarValue();

            mRadarPath.reset();

            // textPoint记录每个雷达数据顶点的坐标位置
            PointF[] textPoint = new PointF[values.size()];
            for (int j = 1; j <= values.size(); j++) {
                float value = values.get(j - 1);
                double percent = value / mMaxValue;
                float x = (float) (mRadarPointCenter.x + Math.sin(mAngle * j + mRotateAngle) * mRadius * percent);
                float y = (float) (mRadarPointCenter.y + Math.cos(mAngle * j + mRotateAngle) * mRadius * percent);
                if (j == 1) {
                    mRadarPath.moveTo(x, y);
                } else {
                    mRadarPath.lineTo(x, y);
                }
                textPoint[j - 1] = new PointF(x, y);
            }
            mRadarPath.close();
            mRadarValuePaint.setAlpha(255);
            mRadarValuePaint.setStyle(Paint.Style.STROKE);
            mRadarValuePaint.setStrokeWidth(10);
            canvas.drawPath(mRadarPath, mRadarValuePaint);

            if (mRadarViewSolid) {
                // 设置的属性中是否需要绘制实心的数据部分
                mRadarValuePaint.setStyle(Paint.Style.FILL);
                mRadarValuePaint.setAlpha(150);
                canvas.drawPath(mRadarPath, mRadarValuePaint);
            }

            // 在雷达图上绘制具体的值信息(效果不是太好，不推荐打开)
            if (radarData.isValueTextEnable()) {
                List<String> valueText = radarData.getValueText();
                for (int k = 0; k < textPoint.length; k++) {
                    String text = valueText.get(k);
                    float textWidth = mValueTextPaint.measureText(text);
                    Paint.FontMetrics fontMetrics = mValueTextPaint.getFontMetrics();
                    float textHeight = fontMetrics.descent - fontMetrics.ascent;
                    canvas.drawText(text, textPoint[k].x - textWidth / 2, textPoint[k].y + textHeight / 3, mValueTextPaint);
                }
            }
        }
    }

    public void setVertexText(List<String> vertexText) {
        this.mVertexText = vertexText;
        initVertexText();
        invalidate();
    }

    private void initVertexText() {
        if (mVertexText == null || mVertexText.size() == 0) {
            mVertexText = new ArrayList<>();
            for (int i = 0; i < mMaxVertex; i++) {
                char text = (char) ('A' + i);
                mVertexText.add(String.valueOf(text));
            }
        } else if (mVertexText.size() < mMaxVertex) {
            int size = mMaxVertex - mVertexText.size();
            for (int i = 0; i < size; i++) {
                mVertexText.add("");
            }
        }
        mMaxLengthVertexText = Collections.max(mVertexText, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.length() - rhs.length();
            }
        });
    }

    /**
     * 初始化雷达图各层layer的颜色值
     * 未指定layer颜色时默认使用 Color.TRANSPARENT 进行填充
     */
    private void initLayerColor() {
        if (mLayerColor == null) {
            mLayerColor = new ArrayList<>();
        }
        if (mLayerColor.size() < mRadarLayers) {
            int size = mRadarLayers - mLayerColor.size();
            for (int i = 0; i < size; i++) {
                mLayerColor.add(Color.TRANSPARENT);
            }
        }
    }

    public int getRadarEdgeMode() {
        return mRadarEdgeMode;
    }

    /**
     * 设置雷达图边缘模式(支持正N多边行以及圆形两种模式)
     */
    public void setRadarEdgeMode(int mWebMode) {
        if (mWebMode != EDGE_MODE_POLYGON && mWebMode != EDGE_MODE_CIRCLE) {
            throw new IllegalStateException("only support EDGE_MODE_POLYGON or EDGE_MODE_CIRCLE");
        }
        this.mRadarEdgeMode = mWebMode;
        invalidate();
    }

    /**
     * 设置雷达网格每层的颜色
     *
     * @param layerColor 雷达图各层layer颜色值
     */
    public void setLayerColor(List<Integer> layerColor) {
        this.mLayerColor = layerColor;
        initLayerColor();
        invalidate();
    }


    /**
     * 设置雷达图外围层级数量
     *
     * @param layerNumber 雷达图层级数
     */
    public void setLayerNumbers(int layerNumber) {
        this.mRadarLayers = layerNumber;
        initLayerColor();
        invalidate();
    }

    public boolean isRadarLineEnable() {
        return mRadarEdgeLineEnable;
    }

    /**
     * 设置是否绘制雷达图边框
     */
    public void setRadarLineEnable(boolean radarLineEnable) {
        this.mRadarEdgeLineEnable = radarLineEnable;
        invalidate();
    }


    private double mPerimeter;

    private float mRadius;







    private double mAngle;
    private double mRotateAngle = 3 * Math.PI / 5;


    private float mFlingPoint;
    private double mRotateOrientation;


    private AnimateUtil mAnimateUtil;




    public float getMaxValue() {
        return mMaxValue;
    }

    public void setMaxValue(float maxValue) {
        this.mMaxValue = maxValue;
        invalidate();
    }


    public boolean isRotationEnable() {
        return mRotationEnable;
    }

    public void setRotationEnable(boolean enable) {
        this.mRotationEnable = enable;
    }

    public void animateValue(int duration) {
        for (RadarData radarData : mRadarData) {
            animateValue(duration, radarData);
        }
    }

    public void animateValue(int duration, RadarData data) {
        if (!mAnimateUtil.isPlaying(data)) {
            mAnimateUtil.animeValue(AnimateUtil.AnimationType.ZOOM, duration, data);
        }
    }



    public void setEmptyHint(String hint) {
        mEmptyDataTips = hint;
        invalidate();
    }

    public void removeRadarData(RadarData data) {
        mRadarData.remove(data);
        invalidate();
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent(MotionEvent event)");
        if (!mRotationEnable) {
            return super.onTouchEvent(event);
        }
        return mDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            if (!mScroller.isFinished()) {
                mScroller.forceFinished(true);
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                mFlingPoint = e2.getX();
                mScroller.fling((int) e2.getX(), 0, (int) velocityX, 0, (int) (-mPerimeter + e2.getX()),
                        (int) (mPerimeter + e2.getX()), 0, 0);
            } else if (Math.abs(velocityY) > Math.abs(velocityX)) {
                mFlingPoint = e2.getY();
                mScroller.fling(0, (int) e2.getY(), 0, (int) velocityY, 0, 0, (int) (-mPerimeter + e2.getY()),
                        (int) (mPerimeter + e2.getY()));
            }
            invalidate();
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            double rotate = mRotateAngle;
            double dis = RotateUtil.getRotateAngle(new PointF(e2.getX() - distanceX, e2.getY() - distanceY)
                    , new PointF(e2.getX(), e2.getY()), mRadarPointCenter);
            rotate += dis;
            handleRotate(rotate);
            mRotateOrientation = dis;
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            int max = Math.max(Math.abs(x), Math.abs(y));
            double rotateDis = RotateUtil.CIRCLE_ANGLE * (Math.abs(max - mFlingPoint) / mPerimeter);
            double rotate = mRotateAngle;
            if (mRotateOrientation > 0) {
                rotate += rotateDis;
            } else if (mRotateOrientation < 0) {
                rotate -= rotateDis;
            }
            handleRotate(rotate);
            mFlingPoint = max;
            invalidate();
        }
    }

    private void handleRotate(double rotate) {
        rotate = RotateUtil.getNormalizedAngle(rotate);
        mRotateAngle = rotate;
        invalidate();
    }


    private float dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }


    private void textCenter(String[] contentStr, Paint paint, Canvas canvas, PointF point, Paint.Align align) {
        paint.setTextAlign(align);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int length = contentStr.length;
        float total = (length - 1) * (-top + bottom) + (-fontMetrics.ascent + fontMetrics.descent);
        float offset = total / 2 - bottom;
        for (int i = 0; i < length; i++) {
            float yAxis = -(length - i - 1) * (-top + bottom) + offset;
            canvas.drawText(contentStr[i], point.x, point.y + yAxis, paint);
        }
    }

}