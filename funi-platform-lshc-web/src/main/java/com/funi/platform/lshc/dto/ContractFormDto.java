package com.funi.platform.lshc.dto;

import com.funi.platform.lshc.entity.contr.Contr;
import com.funi.platform.lshc.entity.contr.ContrHouse;
import com.funi.platform.lshc.entity.contr.Lessee;
import com.funi.platform.lshc.entity.contr.Resident;
import com.funi.platform.lshc.vo.LinkFileVo;

import java.util.List;

/**
 * @author 3
 */
public class ContractFormDto {

    //合同
    private Contr contract;
    //房屋id
    private List<ContrHouse> houseList;
    //承租人
    private Lessee lessee;
    //住户
    private List<Resident> residentList;
    //档案
    private DocumentDto document;
    //老业务件号
    private String oldServiceNum;
    //承租人要件集合
    private List<LinkFileVo> lesseeAttachmentList;
    //档案要件集合
    private List<LinkFileVo> documentAttachmentList;

    public List<LinkFileVo> getDocumentAttachmentList() {
        return documentAttachmentList;
    }

    public void setDocumentAttachmentList(List<LinkFileVo> documentAttachmentList) {
        this.documentAttachmentList = documentAttachmentList;
    }

    public List<LinkFileVo> getLesseeAttachmentList() {
        return lesseeAttachmentList;
    }

    public void setLesseeAttachmentList(List<LinkFileVo> lesseeAttachmentList) {
        this.lesseeAttachmentList = lesseeAttachmentList;
    }

    public Contr getContract() {
        return contract;
    }

    public void setContract(Contr contract) {
        this.contract = contract;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }

    public List<ContrHouse> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<ContrHouse> houseList) {
        this.houseList = houseList;
    }

    public Lessee getLessee() {
        return lessee;
    }

    public void setLessee(Lessee lessee) {
        this.lessee = lessee;
    }

    public List<Resident> getResidentList() {
        return residentList;
    }

    public void setResidentList(List<Resident> residentList) {
        this.residentList = residentList;
    }

    public String getOldServiceNum() {
        return oldServiceNum;
    }

    public void setOldServiceNum(String oldServiceNum) {
        this.oldServiceNum = oldServiceNum;
    }
}
