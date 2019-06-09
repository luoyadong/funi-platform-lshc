package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum Conclusion {

    TY("同意"),
    REGION_CODE("140202"),
    BTY("拒绝");

    private String name;

    Conclusion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Conclusion findByName(String name){
        switch (name){
            case  "同意":return Conclusion.TY;
            case  "拒绝":return Conclusion.BTY;
        }
        return null;
    }
}
