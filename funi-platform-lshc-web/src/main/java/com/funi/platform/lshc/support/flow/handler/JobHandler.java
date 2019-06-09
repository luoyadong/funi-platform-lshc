package com.funi.platform.lshc.support.flow.handler;

import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.Conclusion;

/**
 * @author 3
 */
public interface JobHandler {

    boolean support(BusinessType businessType);

    void handle(String serviceNum,Conclusion conclusion);
}
