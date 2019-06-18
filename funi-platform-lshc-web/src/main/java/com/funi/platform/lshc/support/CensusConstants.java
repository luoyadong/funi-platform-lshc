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
    /** 房屋状态：待初审*/
    public static final String HOUSE_STATUS_SUBMIT = "1";
    /** 房屋状态：初审通过*/
    public static final String HOUSE_STATUS_FIRST_APPROVAL_PASS = "2";
    /** 房屋状态：初审不通过*/
    public static final String HOUSE_STATUS_FIRST_APPROVAL_REJECT = "3";
    /** 房屋状态：复审通过*/
    public static final String HOUSE_STATUS_SECOND_APPROVAL_PASS = "4";
    /** 房屋状态：复审不通过*/
    public static final String HOUSE_STATUS_SECOND_APPROVAL_REJECT = "5";
    /** 房屋状态：退回*/
    public static final String HOUSE_STATUS_BACK = "6";

    /** 楼栋列表页查询类型：管理页面查询*/
    public static final String BUILD_QUERY_TYPE_MANAGE = "0";
    /** 楼栋列表页查询类型：综合查询*/
    public static final String BUILD_QUERY_TYPE_COLLECT = "1";
    /** 楼栋列表页查询类型：待办件查询*/
    public static final String BUILD_QUERY_TYPE_UNDONE = "2";
    /** 楼栋列表页查询类型：已办件查询*/
    public static final String BUILD_QUERY_TYPE_COMPLETED = "3";

    /** 区域查询类型：根据城市查询区域*/
    public static final Integer REGION_TYPE_CITY = 0;
    /** 区域查询类型：根据区域查询街道*/
    public static final Integer REGION_TYPE_REGION = 1;
    /** 区域查询类型：根据街道查询社区*/
    public static final Integer REGION_TYPE_COUNTY = 2;
}
