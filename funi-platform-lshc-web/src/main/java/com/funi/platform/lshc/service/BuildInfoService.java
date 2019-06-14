package com.funi.platform.lshc.service;

import com.funi.platform.lshc.dto.RegiInfoDto;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/14.12:14 AM
 */
public interface BuildInfoService {
    /**
     * 分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> findBuildInfoList(BuildInfoQuery buildInfoQuery);

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    List<RegiInfoVo> findRegiInfoVoList(RegiInfoQuery regiInfoQuery);

    /**
     * 创建普查信息
     * @param regiInfoDto
     */
    void createRegiInfo(RegiInfoDto regiInfoDto);

    /**
     * 根据房屋编号查询房屋详情
     * @param houseId
     * @return
     */
    RegiInfoVo findRegiInfoDetail(String houseId);

    /**
     * 根据ID集合批量删除房屋普查记录
     * @param ids
     */
    void batchRemoveRegiInfo(List<String> ids);

    /**
     * 批量导入普查信息
     * @param uploadFile
     */
    void importRegiInfo(MultipartFile uploadFile);

    /**
     * 根据查询条件导出普查信息列表
     * @param regiInfoQuery
     * @param response
     */
    void exportRegiInfoVoList(RegiInfoQuery regiInfoQuery, HttpServletResponse response);

}
