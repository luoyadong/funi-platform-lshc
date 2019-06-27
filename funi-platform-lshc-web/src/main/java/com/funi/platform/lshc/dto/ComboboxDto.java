package com.funi.platform.lshc.dto;

import com.funi.platform.lshc.vo.census.LshcRegionVo;

/**
 * 下拉框数据传输对象
 * @author 3
 */
public class ComboboxDto {

    private String name;
    private String value;

    public ComboboxDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ComboboxDto() {
    }

    public ComboboxDto(LshcRegionVo lshcRegionVo) {
        this.name = lshcRegionVo.getName();
        this.value = lshcRegionVo.getCode();
    }

    public ComboboxDto(String name) {
        this.name = name;
        this.value = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
