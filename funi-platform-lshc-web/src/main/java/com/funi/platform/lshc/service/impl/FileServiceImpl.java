package com.funi.platform.lshc.service.impl;

import com.funi.framework.mvc.eic.support.FileUploader;
import com.funi.framework.mvc.eic.utils.UploadFileUriUtils;
import com.funi.platform.lshc.entity.sys.LinkFile;
import com.funi.platform.lshc.mapper.sys.LinkFileMapper;
import com.funi.platform.lshc.service.FileService;
import com.funi.platform.lshc.support.UserManager;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author by yadong on 2018/11/20.
 */
public class FileServiceImpl implements FileService {
    @Resource
    private LinkFileMapper linkFileMapper;
    @Resource
    private FileUploader fileUploader;
    @Resource
    private UserManager userManager;

    @Override
    public List<LinkFile> findAllFileList(String bizId,String fileType) {
        Map param = new HashMap<String,String>();
        param.put("bizId",bizId);
        param.put("fileType",fileType);
        return linkFileMapper.selectListByBiz(param);
    }
    @Override
    public void deleteFileByKey(String fileId) {
        linkFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public LinkFile createFile(MultipartFile uploadFile) {
        String fileId = fileUploader.upload(uploadFile);
        String fileUrl = UploadFileUriUtils.createFileUrl(fileId);
        LinkFile f = new LinkFile();
        f.setLinkFileUrl(fileUrl);
        f.setUnitName(userManager.findUser().getOrganization().getMc());
        f.setUserName(userManager.findUser().getUsername());
        f.setUploadDate(new Date());
        return f;
    }

    @Override
    public void createHouseFile(List<LinkFile> linkFile,String linkId) {
        for(LinkFile file:linkFile){
            file.setId(UUID.randomUUID().toString());
            file.setLinkId(linkId);
            linkFileMapper.insert(file);
        }

    }
}
