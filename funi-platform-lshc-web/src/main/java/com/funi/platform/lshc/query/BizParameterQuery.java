package com.funi.platform.lshc.query;

import com.funi.framework.biz.query.PageableQuery;
import com.funi.framework.utils.StringUtils;

/**
 * @author 3
 */
public class BizParameterQuery extends PageableQuery {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = StringUtils.hasText(type)?type:null;
    }
}
