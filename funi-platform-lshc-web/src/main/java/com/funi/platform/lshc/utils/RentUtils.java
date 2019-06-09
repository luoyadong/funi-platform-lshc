package com.funi.platform.lshc.utils;

import java.math.BigDecimal;

/**
 * Created by sam on 2018/11/26.11:10 PM
 */
public class RentUtils {
    /**
     * 根据月租金，交租开始截止日期计算租金
     * @param startDate
     * @param endDate
     * @param rentalMonthAmount 月租金
     * @return
     */
    public static BigDecimal calcRent(String startDate, String endDate, BigDecimal rentalMonthAmount) {
        BigDecimal backRentAmount = BigDecimal.ZERO;
        int monthCount = RentUtils.getMonthCount(startDate, endDate);
        if(monthCount > 0) {
            // 获取月租金
            backRentAmount = backRentAmount.add(rentalMonthAmount).multiply(new BigDecimal(monthCount));
        }
        // 获取日租金
        BigDecimal dayRent = RentUtils.getDayRent(rentalMonthAmount);
        // 获得日租天数
        int arrearsDateCount = RentUtils.getDateCount(startDate, endDate);
        if(arrearsDateCount != 0) {
            backRentAmount = backRentAmount.add(dayRent.multiply(new BigDecimal(arrearsDateCount)));
        }
        return NumberUtil.getAccuracyDecimal(backRentAmount);
    }

    /**
     * 获取租金缴费期内除去足月的，日数量
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDateCount(String startDate, String endDate) {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(startDate) && org.apache.commons.lang3.StringUtils.isNotBlank(endDate)) {
            String temp = DateUtils.addMonth(startDate, 1);
            // 如果不足月不进入循环，直接计算日差并返回
            if(DateUtils.getDifferDay(endDate, temp) > 0) {
                return DateUtils.getDifferDay(startDate, endDate) + 1;
            }
            // 每次循环添加一月后的日期，第一次进来默认等于开始日期
            String lastMonthDate = startDate;
            while(true) {
                lastMonthDate = DateUtils.addMonth(lastMonthDate, 1);
                int differDay = DateUtils.getDifferDay(endDate, lastMonthDate);
                if(differDay < 0) {
                    // 结束时间加一天当做下一轮循环的开始时间
                    lastMonthDate = DateUtils.addDay(lastMonthDate, 1);
                    String addMonth = DateUtils.addMonth(lastMonthDate, 1);
                    // 判断天数是否足月，不足月直接返回，否则进入下一轮循环
                    int differDay1 = DateUtils.getDifferDay(addMonth, endDate);
                    if(differDay1 < 0) {
                        return DateUtils.getDifferDay(lastMonthDate, endDate) + 1;
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            }
        }
        return 0;
    }

    /**
     * 根据月租金获取日租金
     * @return
     */
    public static BigDecimal getDayRent(BigDecimal rentalMonthAmount) {
        return rentalMonthAmount.divide(new BigDecimal(30), 4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取租金缴费期内的足月，月数量
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMonthCount(String startDate, String endDate) {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(startDate) && org.apache.commons.lang3.StringUtils.isNotBlank(endDate)) {
            // 获取欠租月数
            int arrearsMonthCount = 0;
            String tempMonthDate = startDate;
            while(true) {
                // 开始时间加上一月
                tempMonthDate = DateUtils.addMonth(tempMonthDate, 1);
                int differDay = DateUtils.getDifferDay(endDate, tempMonthDate);
                if(differDay < 0) {
                    tempMonthDate = DateUtils.addDay(tempMonthDate, 1);
                    arrearsMonthCount ++;
                    continue;
                } else if(differDay == 0) {
                    arrearsMonthCount ++;
                    break;
                } else {
                    break;
                }
            }
            return arrearsMonthCount;
        }
        return 0;
    }
}
