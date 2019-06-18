package com.funi.platform.lshc.service;

import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import com.funi.platform.lshc.vo.census.RegiInfoDetailVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/17.3:47 PM
 */
public interface RegiInfoService {

    /**
     * 分页查询楼栋列表
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
     * 根据房屋编号查询房屋详情
     * @param hcId
     * @return
     */
    RegiInfoDetailVo findRegiInfoDetail(String hcId);

    /**
     * 根据房屋编号导出普查信息列表
     * @param ids
     * @param response
     */
    void exportRegiInfoVoList(List<String> ids, HttpServletResponse response) throws Exception;
}
