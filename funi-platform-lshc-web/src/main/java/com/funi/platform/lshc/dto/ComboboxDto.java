package com.funi.platform.lshc.dto;

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
