package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum BusinessType {

    cnew("Ghouse_Common_Flow",2,"新增合同","GhouseCommonFlow"),
    cchange("Ghouse_Common_Flow",2,"变更合同","GhouseCommonFlow"),
    ccanel("Ghouse_Common_Flow",2,"合同注销","GhouseCommonFlow"),
    crenew("Ghouse_Common_Flow",2,"续租","GhouseCommonFlow"),
    repairnew("Ghouse_Maintain_Flow",1,"新增维修","GhouseMaintainFlow")
    ;

    private String key;
    private Integer version;
    private String workName;
    private String name;

    BusinessType(String key,Integer version, String workName,String name) {
        this.key = key;
        this.version = version;
        this.workName = workName;
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public String getWorkName() {
        return workName;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static BusinessType findByWorkName(String workName){
        switch (workName){
            case  "新增合同":return BusinessType.cnew;
            case  "变更合同":return BusinessType.cchange;
            case  "合同注销":return BusinessType.ccanel;
            case  "续租"    :return BusinessType.crenew;
            case  "新增维修":return BusinessType.repairnew;
        }
        return null;
    }

    public static BusinessType findByKey(String key){
        switch (key){
            case  "新增合同":return BusinessType.cnew;
        }
        return null;
    }
}
