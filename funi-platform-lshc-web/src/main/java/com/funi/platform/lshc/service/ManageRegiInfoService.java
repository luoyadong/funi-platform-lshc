package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by sam on 2019/6/17.10:30 PM
 */
public interface ManageRegiInfoService {
    /**
     * 分页查询楼栋列表，只查询新建和初审不通过的数据
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery);

    /**
     * 创建普查信息
     * @param regiInfoDto
     */
    void createRegiInfo(RegiInfoDto regiInfoDto);

    /**
     * 批量导入普查信息
     * @param uploadFile
     */
    void importRegiInfoList(MultipartFile uploadFile) throws IOException;

    /**
     * 根据楼栋ID集合删除楼栋以及普查信息
     * @param ids
     */
    void batchRemoveBuildInfo(List<String> ids);

    /**
     * 根据ID集合批量删除房屋普查记录
     * @param ids
     */
    void batchRemoveRegiInfo(List<String> ids);
}
