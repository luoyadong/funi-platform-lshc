package com.funi.platform.lshc.service;

import java.util.List;
import java.util.Map;

/**
 * 接口
 */
public interface AccountEntryService {
    /**
     * 主列表查询
     * @param paramsMap
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getAccountEntryPage(Map<String,Object> paramsMap) throws Exception;
}
