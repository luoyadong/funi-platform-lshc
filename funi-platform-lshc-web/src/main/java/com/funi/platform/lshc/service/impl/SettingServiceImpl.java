package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.mvc.eic.support.FileUploader;
import com.funi.framework.mvc.eic.utils.UploadFileUriUtils;
import com.funi.platform.lshc.entity.charge.BillTemplet;
import com.funi.platform.lshc.entity.contr.ContrTemplet;
import com.funi.platform.lshc.enumatation.CommonBizType;
import com.funi.platform.lshc.mapper.charge.BillTempletMapper;
import com.funi.platform.lshc.mapper.contr.ContrTempletMapper;
import com.funi.platform.lshc.query.BillTempQuery;
import com.funi.platform.lshc.query.ContrTempQuery;
import com.funi.platform.lshc.service.SettingService;
import com.funi.platform.lshc.utils.BaseEntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service("settingService")
public class SettingServiceImpl implements SettingService {
    @Resource
    private BillTempletMapper billTempletMapper;
    @Resource
    private ContrTempletMapper contrTempletMapper;
    @Resource
    private FileUploader fileUploader;

    @Override
    public List<BillTemplet> findBillTempList(BillTempQuery query) {
        List<BillTemplet> rtList = billTempletMapper.selectBillTempList(query);
        return rtList;
    }

    @Override
    public int addBillTemp(BillTemplet obj) {

        if(null != obj){
            //是启用标志，则先禁用其他标志
            updateAllUseFlag(obj);
            obj.setVersion(1);
            return billTempletMapper.insert(obj);
        }
        return 0;
    }

    @Override
    public int updateBillTemp(BillTemplet obj) {
        if(null != obj){
            //是启用标志，则先禁用其他标志
            updateAllUseFlag(obj);
            return billTempletMapper.updateByPrimaryKeySelective(obj);
        }
        return 0;
    }

    @Override
    public List<ContrTemplet> findContrTempList(ContrTempQuery query) {
        List<ContrTemplet> rtList = contrTempletMapper.selectByQuery(query);
        return rtList;
    }

    @Override
    public int delBillTempById(String id) {
        return billTempletMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateContrTemp(ContrTemplet obj) {
        if(null != obj && StringUtils.isNotBlank(obj.getId())){
            //是启用标志，则先禁用其他标志
            updateAllContrFlag(obj);
            return contrTempletMapper.updateStatus(obj);
        }
        return 0;
    }

    @Override
    public int delContrTempById(String id) {

        return contrTempletMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ContrTemplet createFile(MultipartFile uploadFile,CurrentUser userInfo) {

        String fileName = uploadFile.getOriginalFilename();
        String fileId = fileUploader.upload(uploadFile);
        String fileUrl = UploadFileUriUtils.createFileUrl(fileId);

        ContrTemplet objTmp = new ContrTemplet();
        //默认启动
        objTmp.setValidFlg(String.valueOf(CommonBizType.BIZ_OPEN.getBizType()));
        objTmp.setContractTempletName(fileName);
        objTmp.setTempUrl(fileUrl);

        new BaseEntityUtils<ContrTemplet>().buildCreateEntity(objTmp, userInfo);
        this.updateAllContrFlag(objTmp);

        contrTempletMapper.insert(objTmp);
        return objTmp;
    }

    //全量更新合同启用状态
    private void updateAllContrFlag(ContrTemplet obj){

        if(null != obj && String.valueOf(CommonBizType.BIZ_OPEN.getBizType()).equals(obj.getValidFlg())){
            ContrTemplet objTmp = new ContrTemplet();
            objTmp.setValidFlg(String.valueOf(CommonBizType.BIZ_CLOSE.getBizType()));
            contrTempletMapper.updateStatus(objTmp);
        }

    }
    //全量更新启用状态
    private void updateAllUseFlag(BillTemplet obj){

        if(null != obj && String.valueOf(CommonBizType.BIZ_OPEN.getBizType()).equals(obj.getUserFlag())){
            BillTemplet objTmp = new BillTemplet();
            objTmp.setUserFlag(String.valueOf(CommonBizType.BIZ_CLOSE.getBizType()));
            billTempletMapper.updateAllUseFlag(objTmp);
        }

    }
}
