package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum RentType {

    A("市场招租"),
    B("公房招租"),
    C("公租房"),
    D("廉租房招租");

    private String description;

    RentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
