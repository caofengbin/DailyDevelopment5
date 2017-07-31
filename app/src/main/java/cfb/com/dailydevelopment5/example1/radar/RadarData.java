package cfb.com.dailydevelopment5.example1.radar;

import java.util.ArrayList;
import java.util.List;

/**
 * 雷达图所需的数据实体类
 * Created by fengbincao on 2017/7/30.
 */

class RadarData {

    private int mRadarValueColor;                           // 绘制雷达数据区域的边框的颜色

    private int mValueTextSize;                             // 雷达数据区域文字值的字体大小
    private int mValueTextColor;                            // 雷达数据区域文字值的字体颜色
    private boolean mValueTextEnable;                       // 雷达数据区域部分是否绘制具体的值信息

    private List<Float> mRadarValue;                        // 雷达图数据结构
    private List<String> mValueText;                        // 雷达图数据区域文字

    List<String> getValueText() {
        return mValueText;
    }

    private void initValueText() {
        mValueText = new ArrayList<>();
        for (int i = 0; i < mRadarValue.size(); i++) {
            mValueText.add(mRadarValue.get(i).toString());
        }
    }

    /**
     * 构造方法
     *
     * @param value 雷达图值数据部分
     * @param color 雷达图值部分的color
     */
    RadarData(List<Float> value, int color) {
        this.mRadarValue = value;
        this.mRadarValueColor = color;
        initValueText();

        mValueTextColor = 0xFF9E9E9E;
        mValueTextSize = 10;
        mValueTextEnable = false;
    }

    List<Float> getRadarValue() {
        return mRadarValue;
    }

    int getRadarValueLineColor() {
        return mRadarValueColor;
    }

    int getValueTextColor() {
        return mValueTextColor;
    }

    int getValueTextSize() {
        return mValueTextSize;
    }

    boolean isValueTextEnable() {
        return mValueTextEnable;
    }
}
