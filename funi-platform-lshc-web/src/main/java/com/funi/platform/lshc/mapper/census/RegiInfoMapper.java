package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.query.census.RegiInfoQuery;
import com.funi.platform.lshc.vo.census.ExcelRegiInfoVo;
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
     * 获取房屋的业务件号
     * @return
     */
    String generateHouseId();

    /**
     * 根据房屋编号集合逻辑删除房屋信息
     * @param ids
     */
    void batchDeleteRegiInfo(@Param("ids") List<String> ids, @Param("userId") String userId);

    /**
     * 根据房屋编号查询楼栋、房屋、人员信息
     * @param ids
     * @return
     */
    List<ExcelRegiInfoVo> selectExcelRegiInfoVoList(List<String> ids);

    /**
     * 校验房屋的唯一性，以下字段相同，则认为房屋信息重复
     * 市+区+县+门牌号+楼栋地图编号+栋+单元+楼层+号
     * @param regiInfo
     * @return
     */
    int checkRegiInfoUnique(RegiInfo regiInfo);
}