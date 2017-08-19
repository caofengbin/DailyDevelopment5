package cfb.com.dailydevelopment5.example5.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 基本的绘制文字的控件
 * Created by fengbincao on 2017/8/11.
 */

public class DrawTextView extends View {

    private Paint paint = new Paint();
    //private TextPaint textPaint = new TextPaint();

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Rect bounds = new Rect();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int offsetX = 100;
        int offsetY = 100;

        String text = "Hello HenCoder";

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(58);

        int measuredCount;
        float[] measuredWidth = {0};

        float fontSpacing = paint.getFontSpacing();

        // 设置宽度上限为300，不够则进行截断操作
        // measuredCount返回的是在给定的宽度上限下，可以实际绘制的文字的数量
        measuredCount = paint.breakText(text, 0, text.length(), true, 300, measuredWidth);
        canvas.drawText(text, 0, measuredCount, 150, 150, paint);

        // 宽度上限 400 （不够用，截断）
        measuredCount = paint.breakText(text, 0, text.length(), true, 400, measuredWidth);
        canvas.drawText(text, 0, measuredCount, 150, 150 + fontSpacing, paint);

        // 宽度上限 500 （够用）
        measuredCount = paint.breakText(text, 0, text.length(), true, 500, measuredWidth);
        canvas.drawText(text, 0, measuredCount, 150, 150 + fontSpacing * 2, paint);

        // 宽度上限 600 （够用）
        measuredCount = paint.breakText(text, 0, text.length(), true, 600, measuredWidth);
        canvas.drawText(text, 0, measuredCount, 150, 150 + fontSpacing * 3, paint);
    }
}
