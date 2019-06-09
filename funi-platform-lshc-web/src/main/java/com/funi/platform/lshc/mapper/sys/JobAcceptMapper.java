package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.dto.JobDto;
import com.funi.platform.lshc.entity.sys.JobAccept;


public interface JobAcceptMapper {

    /**
     * 创建受理记录
     * @param record
     * @return
     */
    int insert(JobAccept record);

    /**
     * 修改当前状态
     * @param record
     */
    void modifyCurStatus(JobAccept record);

    /**
     * 通过业务件号查询受理信息
     * @param serviceNum
     * @return
     */
    JobDto selectByServiceNum(String serviceNum);


}