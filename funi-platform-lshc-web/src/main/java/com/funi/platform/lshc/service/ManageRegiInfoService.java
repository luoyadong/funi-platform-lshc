package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoCheckResultVo;
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
     * 批量提交普查信息
     * @param ids
     */
    void batchSubmitRegiInfo(List<String> ids);

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    List<ListRegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery);

    /**
     * 校验普查信息是否存在重复数据
     * @param regiInfo
     * @return
     * @throws IOException
     */
    RegiInfoCheckResultVo checkRegiInfo(RegiInfo regiInfo);

    /**
     * 创建普查信息
     * @param regiInfoDto
     * @param isSubmit 是否是提交操作
     * @return 普查信息ID
     */
    String createRegiInfo(RegiInfoDto regiInfoDto, boolean isSubmit);

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
     * 校验批量导入的普查信息是否有效，在本excel范围内查找重复
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
