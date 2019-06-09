package com.funi.platform.lshc.service.impl;

import com.funi.platform.lshc.entity.contr.Lessee;
import com.funi.platform.lshc.mapper.contr.LesseeMapper;
import com.funi.platform.lshc.service.LesseeService;
import com.funi.platform.lshc.support.UserManager;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 3
 */
public class LesseeServiceImpl implements LesseeService {

    @Resource
    private LesseeMapper lesseeMapper;
    @Resource
    private UserManager userManager;

    @Override
    public String create(Lessee lessee) {
        lesseeMapper.insert(lessee);
        return lessee.getId();
    }

    @Override
    public String createFinal(String serviceNum,int isFinal,int isFinalW) {
        String id = UUID.randomUUID().toString();
        lesseeMapper.insertFinal(serviceNum,userManager.findUser().getUserId(),id,isFinal,isFinalW);
        return id;
    }
}
