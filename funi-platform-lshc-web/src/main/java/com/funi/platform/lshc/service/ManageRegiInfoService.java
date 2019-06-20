package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
     * 根据楼栋ID批量导出楼栋和房屋信息
     * @param ids
     * @param response
     * @throws Exception
     */
    void exportBuildInfoVoList(List<String> ids, HttpServletResponse response) throws Exception;

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery);

    /**
     * 创建普查信息
     * @param regiInfoDto
     */
    void createRegiInfo(RegiInfoDto regiInfoDto);

    /**
     * 创建普查信息
     * @param regiInfoDto
     */
    void modifyRegiInfo(RegiInfoDto regiInfoDto);

    /**
     * 批量导入普查信息
     * @param uploadFile
     */
    void importRegiInfoList(MultipartFile uploadFile) throws IOException;

    /**
     * 校验批量导入的普查信息是否有效，是否与已存在普查信息重复
     * @param uploadFile
     * @throws IOException
     */
    String checkRegiInfoList(MultipartFile uploadFile) throws IOException;

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
