package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum BusinessType {

    pnew("Lshc_Regi_Flow",2,"新建普查","LshcRegiFlow"),
    cnew("Lshc_Regi_XXX",1,"变更普查","LshcRegiXxx"),
    cchange("Lshc_Regi_XXX",1,"变更普查","LshcRegiXxx"),
    crenew("Lshc_Regi_XXX",1,"变更普查","LshcRegiXxx"),
    repairnew("Lshc_Regi_XXX",1,"变更普查","LshcRegiXxx"),
    ccanel("Lshc_Regi_XXX",1,"变更普查","LshcRegiXxx");

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
            case  "新建普查":return BusinessType.pnew;
        }
        return null;
    }

    public static BusinessType findByKey(String key){
        switch (key){
            case  "新建普查":return BusinessType.pnew;
        }
        return null;
    }
}
