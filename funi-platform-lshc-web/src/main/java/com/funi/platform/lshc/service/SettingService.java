package com.funi.platform.lshc.service;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.entity.charge.BillTemplet;
import com.funi.platform.lshc.entity.contr.ContrTemplet;
import com.funi.platform.lshc.query.BillTempQuery;
import com.funi.platform.lshc.query.ContrTempQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SettingService {
    /**
     * 查询票据列表
     * @param query
     * @return 票据模板列表
     */
    List<BillTemplet> findBillTempList(BillTempQuery query);
    /**
     * 新增票据模板信息
     * @param obj
     * @return
     */
    int addBillTemp(BillTemplet obj);
    /**
     * 更新票据模板信息
     * @param obj
     * @return
     */
    int updateBillTemp(BillTemplet obj);

    /**
     * 根据Id，删除模板
     * @param id
     * @return
     */
    int delBillTempById(String id);

    /**
     * 查询合同模板列表
     * @param query
     * @return 合同模板列表
     */
    List<ContrTemplet> findContrTempList(ContrTempQuery query);

    /**
     * 更新合同模板信息
     * @param obj
     * @return
     */
    int updateContrTemp(ContrTemplet obj);

    /**
     * 根据Id，删除合同模板
     * @param id
     * @return
     */
    int delContrTempById(String id);

    /**
     * 上传合同模板
     * @param uploadFile 上传文件（主要是二进制流）
     * @return
     */
    ContrTemplet createFile(MultipartFile uploadFile,CurrentUser userInfo);
}
