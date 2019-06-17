package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.BuildInfo;
import com.funi.platform.lshc.query.census.BuildInfoQuery;
import com.funi.platform.lshc.vo.census.BuildInfoVo;

import java.util.List;

public interface BuildInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(BuildInfo record);

    int insertSelective(BuildInfo record);

    BuildInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BuildInfo record);

    int updateByPrimaryKey(BuildInfo record);

    /**
     * 综合查询-根据查询条件分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> selectBuildInfoVoList(BuildInfoQuery buildInfoQuery);

    /**
     * 待办件-分页查询普查记录中的待办件
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> selectUndoneBuildInfoVoList(BuildInfoQuery buildInfoQuery);

    /**
     * 管理界面-分页查询普查记录中的待办件
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> selectManageBuildInfoVoList(BuildInfoQuery buildInfoQuery);

    /**
     * 已办件-分页查询普查记录中的待办件
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> selectCompletedBuildInfoVoList(BuildInfoQuery buildInfoQuery);

    /**
     * 根据楼栋地图编号
     * @param mapCode
     * @return
     */
    BuildInfo selectBuildInfoByMapCode(String mapCode);





}