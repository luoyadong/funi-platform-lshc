package com.funi.platform.lshc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author by guochuan.deng on 2017/8/21.
 */
public abstract class DateUtils {
    private final static SimpleDateFormat SDF;

    /**
     * 一天中一共的毫秒数
     */
    public static long millionSecondsOfDay = 86400000;

    public static final String FORMAT_DATE_STR = "yyyy-MM-dd";

    static {
        SDF=new SimpleDateFormat("yyyy-MM-dd");
    }
    /**
     * 解析日期格式成为yyyy-MM-dd 类型的
     * @param date 被解析的日期 java.util.Date
     * @return String 解析之后的日期
     */
    public static String parseFormatDate(Date date){
        if(date==null)
            return "";
        return SDF.format(date);
    }

    /**
     * 得到两个日期之间相差的天数,两头不算,取出数据后，可以根据需要再加
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferDay(Date date1, Date date2) {
        Long d1 = date1.getTime();
        Long d2 = date2.getTime();
        return (int) ((d2 - d1) / millionSecondsOfDay);
    }

    /**
     * 得到两个日期之间相差的天数,两头不算,取出数据后，可以根据需要再加
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferDay(String date1, String date2) {
        Date dateTime1_tmp = DateUtils.parse(date1, FORMAT_DATE_STR);
        Date dateTime2_tmp = DateUtils.parse(date2, FORMAT_DATE_STR);
        return getDifferDay(dateTime1_tmp, dateTime2_tmp);
    }

    /**
     * 根据指定日期格式格式化日期为Date型
     *
     * @param date
     * @param formater
     * @return
     */
    public static Date parse(String date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 格式化日期为String型(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, FORMAT_DATE_STR);
    }

    /**
     * 根据指定日期格式格式化日期为String型
     *
     * @param date
     * @param formater
     * @return
     */
    public static String format(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    /**
     * 格式化日期为Date型(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static Date parse(String date) {
        return parse(date, FORMAT_DATE_STR);
    }

    /**
     * 计算日期加月数
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date, int months) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }

    /**
     * 计算日期加月数
     * @param date
     * @param months
     * @return
     */
    public static String addMonth(String date, int months) {
        Calendar c = new GregorianCalendar();
        c.setTime(parse(date));
        // 加上一月
        c.add(Calendar.MONTH, months);
        // 减去一天
        c.add(Calendar.DATE, -1);
        return format(c.getTime());
    }

    /**
     * 计算日期加年
     *
     * @param date
     * @param years
     * @return
     */
    public static String addYear(String date, int years) {
        Calendar c = new GregorianCalendar();
        c.setTime(parse(date));
        c.add(Calendar.YEAR, years);
        // 减去一天
        c.add(Calendar.DATE, -1);
        return format(c.getTime());
    }

    /**
     * 计算日期加年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date addYear(Date date, int years) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }


    /**
     * 获取当前时间
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 得到两个日期之间相差的年数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferYear(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
    }

    /**
     * 得到两个日期之间相差的月数
     * 不考虑日期，差一天也算一月
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferMonth(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year = getDifferYear(date1, date2);
        return c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + year * 12;
    }

    /**
     * 得到两个日期之间相差的月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferMonth(String date1, String date2) {
        Date dateTime1_tmp = parse(date1, "yyyy-MM-dd");
        Date dateTime2_tmp = parse(date2, "yyyy-MM-dd");
        return getDifferMonth(dateTime1_tmp, dateTime2_tmp);
    }

    /**
     * 计算日期加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static String addDay(String date, int days) {
        Calendar c = new GregorianCalendar();
        c.setTime(parse(date));
        c.add(Calendar.DAY_OF_MONTH, days);
        return format(c.getTime());
    }

    /**
     * 计算日期加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * 格式化截止时间
     * @param endDate
     * @return
     */
    public static String formatEndDateTime(String endDate) {
        String format = addDay(endDate, 1);
        Date parse = parse(format);
        return format(parse) + " 23:59:59";
    }

    /**
     * 格式化开始时间
     * @param startDate
     * @return
     */
    public static String formatStartDateTime(String startDate) {
        Date parse = parse(startDate);
        return format(parse) + " 00:00:00";
    }
}
