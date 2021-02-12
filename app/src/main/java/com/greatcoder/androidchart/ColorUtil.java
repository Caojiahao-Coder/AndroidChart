package com.greatcoder.androidchart;

import java.util.Random;

public class ColorUtil {
    /**
     * 我们使用这个方法生成了一个随机的rgb颜色值。
     *
     * @return 返回一个新的颜色值
     */
    public static String getRandomColor() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //因为颜色的长度是有限制的，所以我们在组合颜色的时候，我们需要去做个长度过滤等。
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        //最后完成对于颜色字符的拼接操作。
        return "#" + r + g + b;
    }
}
