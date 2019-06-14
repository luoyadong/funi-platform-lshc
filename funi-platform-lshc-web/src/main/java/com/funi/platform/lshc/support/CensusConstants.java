package com.funi.platform.lshc.support;

/**
 * Created by sam on 2019/6/14.10:28 AM
 */
public class CensusConstants {
    /** 数据有效*/
    public static final String DATA_VALIDE_VALID = "1";
    /** 数据无效*/
    public static final String DATA_VALIDE_INVALID = "0";

    /** 数据已经逻辑删除*/
    public static final Short DATA_VALIDE_DELETE = 1;
    /** 数据未逻辑删除*/
    public static final Short DATA_VALIDE_NOT_DELETE = 0;

    /** 数据初始版本号0*/
    public static final Integer DATA_DEFAULT_VERSION = 0;

    /** 房屋状态：录入*/
    public static final String HOUSE_STATUS_INPUT = "0";
    /** 房屋状态：提交*/
    public static final String HOUSE_STATUS_SUBMIT = "1";
    /** 房屋状态：初审*/
    public static final String HOUSE_STATUS_FIRST_APPROVAL = "2";
    /** 房屋状态：复审*/
    public static final String HOUSE_STATUS_SECOND_APPROVAL = "3";
    /** 房屋状态： 归档*/
    public static final String HOUSE_STATUS_FILE = "4";
}
