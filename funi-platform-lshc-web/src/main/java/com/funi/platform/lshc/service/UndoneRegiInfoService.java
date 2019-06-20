package com.funi.platform.lshc.service;

import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/17.4:47 PM
 */
public interface UndoneRegiInfoService {
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
     * 根据普查信息ID集合执行批量审批操作
     * @param ids 普查信息ID集合
     * @param result 审批结果，0：不通过，1：通过
     * @parsm desc 审核描述
     */
    void batchAuditRegiInfoList(List<String> ids, String result, String desc);
}
