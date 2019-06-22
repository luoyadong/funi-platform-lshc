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

    /** 审批结果：不通过*/
    public static final String AUDIT_RESULT_REFUSE = "0";
    /** 审批结果：通过*/
    public static final String AUDIT_RESULT_PASS = "1";

    /** Excel导入普查信息，正文开始行号*/
    public static final int EXCEL_CONTENT_START_ROW_NO = 4;
    /** Excel导入普查信息，表头占用的行数*/
    public static final int EXCEL_CONTENT_HEAD_ROWS_NO = 3;

    /** 字典表类型-是否*/
    public static final String DICTIONARY_TYPE_WHETHER = "WHETHER";
    /** 字典表类型-房屋户型，室、厅、卫共用*/
    public static final String DICTIONARY_TYPE_HOUSE_STYLE = "HOUSE_STYLE";
    /** 字典表类型-建成年份*/
    public static final String DICTIONARY_TYPE_BUILD_YEAR = "BUILD_YEAR";
    /** 字典表类型-房屋类别*/
    public static final String DICTIONARY_TYPE_HOUSE_TYPE = "HOUSE_TYPE";
    /** 字典表类型-房屋结构*/
    public static final String DICTIONARY_TYPE_HOUSE_STRUCTURE = "HOUSE_STRUCTURE";
    /** 字典表类型-房屋用途*/
    public static final String DICTIONARY_TYPE_HOUSE_USE = "HOUSE_USE";
    /** 字典表类型-土地性质*/
    public static final String DICTIONARY_TYPE_LAND_NATURE = "LAND_NATURE";
    /** 字典表类型-装修状态*/
    public static final String DICTIONARY_TYPE_FIT_STATUS = "FIT_STATUS";
    /** 字典表类型-人员类别*/
    public static final String DICTIONARY_TYPE_ENT_TYPE = "ENT_TYPE";
    /** 字典表类型-性别*/
    public static final String DICTIONARY_TYPE_GENDER = "GENDER";
    /** 字典表类型-民族*/
    public static final String DICTIONARY_TYPE_ENT_NATION = "ENT_NATION";
    /** 字典表类型-籍贯*/
    public static final String DICTIONARY_TYPE_ENT_NATIVE = "ENT_NATIVE";
    /** 字典表类型-婚姻状态*/
    public static final String DICTIONARY_TYPE_MARRIAGE_STATUS = "MARRIAGE_STATUS";
    /** 字典表类型-证件类型*/
    public static final String DICTIONARY_TYPE_ID_TYPE = "ID_TYPE";
    /** 字典表类型-职业*/
    public static final String DICTIONARY_TYPE_CAREER = "CAREER";
}
