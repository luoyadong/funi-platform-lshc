package com.funi.platform.lshc.utils;


/**
 * String类型工具类
 * @author by
 */
public abstract class StringUtils {

    /**
     * 验证传入的数组是否有空值存在，存在则返回true
     * @param val 传入数组值
     * @return 有空为true
     */
    public static boolean isEmpty(String[] val){
        if(val!=null && val.length>0){
            for(int i=0;i<val.length;i++){
                if(isEmpty(val[i])){
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }

    /**
     * 验证传入值是否为空
     * @param val 传入值
     * @return 为空返回true
     */
    public static boolean isEmpty(String val){
        return val == null || val.length() == 0;
    }

    /**
     * 格式化数字，位数不满的前面补零
     * @param num
     * @param length
     * @return
     */
    public static String formatNumber(int num, int length) {
        String format = "%" + length + "d";
        return String.format(format, num).replace(" ", "0");
    }

}
