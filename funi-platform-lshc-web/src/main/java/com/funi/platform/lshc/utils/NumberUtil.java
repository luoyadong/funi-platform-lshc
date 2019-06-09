package com.funi.platform.lshc.utils;

import java.math.BigDecimal;

/**
 * Created by sam on 2018/11/14.9:32 PM
 */
public class NumberUtil {

    /**
     * 判断是否是有效数字
     * @param bigDecimal
     * @return
     */
    public static boolean isVaildDecimal(BigDecimal bigDecimal) {
        return bigDecimal != null && BigDecimal.ZERO.compareTo(bigDecimal) != 0;
    }

    /**
     * 四舍五入，保留两位小数
     * @param bigDecimal
     * @return
     */
    public static BigDecimal getAccuracyDecimal(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
