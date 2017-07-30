package cfb.com.dailydevelopment5.example1.radar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 随机生成颜色值的工具类
 * Created by fengbincao on 2017/7/30.
 */

class RandomColor {
    private HashMap<Integer, Integer> LightColor;

    {
        LightColor = new HashMap<>();
        LightColor.put(0xFFE84E40, 0);
        LightColor.put(0xffab47bc, 0);
        LightColor.put(0xff7e57c2, 0);
        LightColor.put(0xff738ffe, 0);
        LightColor.put(0xff29b6f6, 0);
        LightColor.put(0xff26c6da, 0);
        LightColor.put(0xff9ccc65, 0);
        LightColor.put(0xffd4e157, 0);
        LightColor.put(0xffffca28, 0);
        LightColor.put(0xffff7043, 0);
        LightColor.put(0xff8d6e63, 0);
    }

    int randomColor() {
        ArrayList<Integer> color = new ArrayList<>(LightColor.keySet());
        int randomColor;
        int i = 0;
        int count = 0;
        do {
            randomColor = color.get(new Random().nextInt(color.size()));
            count++;
            if (count > color.size()) {
                i++;
            }
        } while (LightColor.get(randomColor) != i);
        LightColor.put(randomColor, LightColor.get(randomColor) + 1);
        return randomColor;
    }
}
