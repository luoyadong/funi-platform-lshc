package com.funi.platform.lshc.service;

import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sam on 2019/6/17.10:39 PM
 */
public interface CompletedRegiService {
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

}
