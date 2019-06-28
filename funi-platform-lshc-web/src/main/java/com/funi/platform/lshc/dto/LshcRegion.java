package com.funi.platform.lshc.dto;

import java.util.List;

/**
 * Created by sam on 2019/6/26.4:42 PM
 */
public class LshcRegion {
    private String code;
    private String name;
    private String type;
    private String perCode;
    private List<LshcRegion> regionsList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public List<LshcRegion> getRegionsList() {
        return regionsList;
    }

    public void setRegionsList(List<LshcRegion> regionsList) {
        this.regionsList = regionsList;
    }
}