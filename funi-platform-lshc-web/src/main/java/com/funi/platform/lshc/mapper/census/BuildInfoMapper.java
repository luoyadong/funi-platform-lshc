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
     * 根据楼栋地图编号
     * @param mapCode
     * @return
     */
    BuildInfo selectBuildInfoByMapCode(String mapCode);


    /**
     * 根据查询条件分页查询楼栋列表
     * @param buildInfoQuery
     * @return
     */
    List<BuildInfoVo> selectBuildInfoVoList(BuildInfoQuery buildInfoQuery);
}