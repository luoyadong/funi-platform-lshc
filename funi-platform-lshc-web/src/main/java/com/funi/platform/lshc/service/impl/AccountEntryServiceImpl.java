package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.mapper.AccountBizMapper;
import com.funi.platform.lshc.service.AccountEntryService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by
 */
public class AccountEntryServiceImpl implements AccountEntryService {
    @Resource
    private AccountBizMapper bizMapper;

    @Override
    public List<Map<String, Object>> getAccountEntryPage(Map<String,Object> paramsMap) throws Exception {
        return null;
    }
}
