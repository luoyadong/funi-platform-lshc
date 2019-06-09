package com.funi.platform.lshc.support;

/**
 * Created by sam on 2018/11/22.9:58 AM
 */
public class EnumConstants {

    /** 房屋-物业类型*/
    public static final String ENUM_PROPERTY_TYPE = "PROPERTY_TYPE";
    /** 房屋-房屋类型*/
    public static final String ENUM_HOUSE_TYPE = "HOUSE_TYPE";
    /** 房屋-安全等级*/
    public static final String ENUM_SAFETY_GRADE = "SAFETY_GRADE";
    /** 房屋-建筑结构*/
    public static final String ENUM_BUILDING_STRUCTURE = "BUILDING_STRUCTURE";
    /** 土地使用性质*/
    public static final String ENUM_LAND_USE_NATURE = "LAND_USE_NATURE";

    /**
     * 房屋-物业类型
     */
    public enum PropertyType{
        RESIDENCE("住宅"),
        BUSINESS("商业"),
        CHANGE_BUSINESS("住改商"),
        WORKSHOP("厂房"),
        LAND_RENT("地租");

        private String desc;

        PropertyType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 房屋-房屋类型
     */
    public enum HouseType{
        UNIFIED("统管产"),
        ESCROW("代管产"),
        COMPANY("单位产"),
        PUBLIC("公产"),
        PUBLIC_RENTAL("公租房"),
        LOW_RENT("廉租房"),
        LAND("土地");

        private String desc;

        HouseType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 房屋-安全等级
     */
    public enum SafetyGrade{
        LEVEL_A("A级"),
        LEVEL_B("B级"),
        LEVEL_C("C级"),
        LEVEL_D("D级");

        private String desc;

        SafetyGrade(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    /**
     * 房屋-建筑结构
     */
    public enum BuildingStructure{
        BRICK_WOOD("砖木"),
        BRICK_CONCRETE("砖混"),
        TIMBER("木结构"),
        STEEL("钢结构"),
        REINFORCED("钢筋混凝土");

        private String desc;

        BuildingStructure(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 房屋-土地使用性质
     */
    public enum LandUseNature{
        SELL("出让"),
        TRANSFER("划拨");

        private String desc;

        LandUseNature(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
