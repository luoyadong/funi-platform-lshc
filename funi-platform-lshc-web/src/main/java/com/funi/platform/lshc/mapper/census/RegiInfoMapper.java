package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.ListRegiInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegiInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(RegiInfo record);

    int insertSelective(RegiInfo record);

    RegiInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RegiInfo record);

    int updateByPrimaryKey(RegiInfo record);

    /**
     * 分页查询房屋列表
     * @param regiInfoQuery
     * @return
     */
    List<ListRegiInfoVo> selectRegiInfoVoList(RegiInfoQuery regiInfoQuery);

    /**
     * 根据房屋编码查询房屋
     * @param houseId
     * @return
     */
    RegiInfo selectRegiInfoByHouseId(String houseId);

    /**
     * 根据房屋编号集合逻辑删除房屋信息
     * @param ids
     */
    void batchDeleteRegiInfo(@Param("ids") List<String> ids, @Param("userId") String userId);
}