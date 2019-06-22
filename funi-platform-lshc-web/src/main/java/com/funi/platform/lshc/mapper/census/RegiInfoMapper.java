package com.funi.platform.lshc.mapper.census;

import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.query.census.BuildRegiQuery;
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
     * 根据楼栋ID集合查询楼栋关联的普查信息ID集合
     * @param ids
     * @return
     */
    List<String> selectRegiInfoIdListByBuildIds(List<String> ids);

    /**
     * 查询正在审核中的普查信息列表
     * @param ids
     * @return
     */
    List<RegiInfo> selectRegiInfoListInAudit(List<String> ids);

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
     * 根据楼栋ID批量导出楼栋和房屋信息
     * @param buildRegiQuery
     * @return
     */
    List<ExcelRegiInfoVo> exportBuildInfoVoList(BuildRegiQuery buildRegiQuery);

    /**
     * 根据房屋的唯一性条件查询房屋信息
     * 市+区+县+门牌号+楼栋地图编号+栋+单元+楼层+号
     * @param regiInfo
     * @return
     */
    List<RegiInfo> selectRegiInfoByUniqueQuery(RegiInfo regiInfo);

    /**
     * 根据普查信息主键ID修改普查信息状态
     * @param houseId
     * @param houseStatus
     * @param userId
     * @return
     */
    int updateRegiInfoStatus(@Param("houseId") String houseId, @Param("houseStatus") String houseStatus, @Param("userId") String userId);

    /**
     * 根据普查信息编号查询普查信息
     * @param houseId
     * @return
     */
    RegiInfo selectByHouseId(String houseId);
}