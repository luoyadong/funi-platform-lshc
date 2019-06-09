package com.funi.platform.lshc.mapper.charge;

import com.funi.platform.lshc.entity.charge.Bill;

public interface BillMapper {

    /**
     * 获取下一个票据编号
     * @return
     */
    int selectNextBillSerial();

    int deleteByPrimaryKey(String id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
}