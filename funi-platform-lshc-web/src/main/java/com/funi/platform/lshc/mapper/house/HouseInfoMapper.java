package com.funi.platform.lshc.mapper.house;

import com.funi.platform.lshc.dto.HouseDto;
import com.funi.platform.lshc.entity.contr.ContrHouse;
import com.funi.platform.lshc.entity.house.HouseInfo;
import com.funi.platform.lshc.query.HouseListExcel;
import com.funi.platform.lshc.query.HouseListQuery;
import com.funi.platform.lshc.query.HouseQuery;
import com.funi.platform.lshc.query.MapHouseQuery;
import com.funi.platform.lshc.vo.AddressVo;
import com.funi.platform.lshc.vo.ExcelHouseVo;
import com.funi.platform.lshc.vo.HouseVo;
import com.funi.platform.lshc.vo.MapHouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HouseInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HouseInfo record);

    int insertSelective(HouseInfo record);

    HouseInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseInfo record);

    int updateByPrimaryKey(HouseInfo record);

    /**
     * 查询未签合同的房屋
     * creator 3
     * @param houseQuery
     * @return
     */
    List<HouseDto> selectByQuery(HouseQuery houseQuery);

    List<HouseVo> selectHouseList(HouseListQuery query);
    /**
     * 根据小区Id，查询房屋列表
     * @param query
     * @return
     */
    List<MapHouseVo> selectMapHouseList(MapHouseQuery query);

    /**
     * 修改租赁状态
     * @param list  房屋id集合
     * @param rentStatus 租赁状态
     */
    void modifyRentStatus(@Param("list")List<ContrHouse> list,@Param("rentStatus")int rentStatus);

    /**
     * 判断房屋是否处于租赁总
     * @param list
     * @return
     */
    Boolean isExistRent(@Param("list")List<ContrHouse> list);

    List<AddressVo> selectAdddressList(@Param("id")String id,@Param("keywords")String keywords);

    List<ExcelHouseVo> selectHouseExcelList(HouseListExcel query);

    void updateHouseStatus(@Param("id")String id, @Param("status")String status);

    List<HouseVo> selectHouseSysNo(@Param("id")String id, @Param("keyword")String keyword);

    void updateHouseHasProperty(String houseId);

    /**
     * 获取房屋的业务件号
     * @return
     */
    String generateHouseNo();

    /**
     * 查询合同下房屋的管理类型
     * @param contractId
     * @return
     */
    List<String> selectManageTypeByContractId(String contractId);

    String selectHouseNoInfo(@Param("houseNo")String houseNo);

    /**
     * 判断房屋是否存在过程中业务
     * @param houseMap
     * @return
     */
    List<Map<String,String>> existHouseBiz(@Param("houseMap")Map<String,String> houseMap);
}