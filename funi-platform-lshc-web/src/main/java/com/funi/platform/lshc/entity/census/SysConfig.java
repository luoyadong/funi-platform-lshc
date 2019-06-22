package com.funi.platform.lshc.entity.census;

import com.funi.platform.lshc.entity.SuperEntity;
import java.io.Serializable;

/**
 * 参数配置信息
 * @author sam
 * @date 2019-06-22 15:03:27
 */
public class SysConfig extends SuperEntity implements Serializable {
    /** 参数名称 */
    private String configName;

    /** 参数编码 */
    private String configCode;

    /** 参数值 */
    private String configValue;

    /** 参数分类 */
    private String configType;

    /** 排序 */
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}