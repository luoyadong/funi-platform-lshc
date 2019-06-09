package com.funi.platform.lshc.service;

import com.funi.platform.lshc.entity.contr.Lessee;

/**
 * 承租人业务层
 * @author 3
 */
public interface LesseeService {

    /**
     * 创建承租人
     * @param lessee
     */
    String create(Lessee lessee);

    /**
     * 创建结果数据
     * @return
     */
    String createFinal(String serviceNum,int isFinal,int isFinalW);
}
