package com.funi.platform.lshc.dto;

/**
 * @author 3
 */
public class BizParameterDto {

    private String id;
    private String bizTypeName;
    private String parameterName;
    private int parameterOrder;

    public String getBizTypeName() {
        return bizTypeName;
    }

    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getParameterOrder() {
        return parameterOrder;
    }

    public void setParameterOrder(int parameterOrder) {
        this.parameterOrder = parameterOrder;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
}
