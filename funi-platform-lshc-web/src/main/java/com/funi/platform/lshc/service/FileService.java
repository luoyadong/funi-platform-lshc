package com.funi.platform.lshc.service;

import com.funi.platform.lshc.entity.sys.LinkFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 要件图片
 * @author by yadong on 2018/11/20.
 */
public interface FileService {
    /**
     * 根据业务Id查询业务对应的文件列表
     * @param bizId
     * @return
     */
    List<LinkFile> findAllFileList(String bizId,String fileType);

    void deleteFileByKey(String fileId);

    /**
     * 上传文件
     * @param uploadFile 上传文件（主要是二进制流）
     * @return
     */
    LinkFile createFile(MultipartFile uploadFile);

    void createHouseFile(List<LinkFile> linkFile,String linkId);
}
