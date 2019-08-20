package com.funi.platform.lshc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.funi.framework.app.invocation.AppInvoker;
import com.funi.framework.app.invocation.bo.AppInvocationResult;
import com.funi.framework.biz.BizException;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.dto.LshcRegion;
import com.funi.platform.lshc.dto.SecurityRegionDto;
import com.funi.platform.lshc.entity.census.RegiInfo;
import com.funi.platform.lshc.mapper.census.RegiInfoMapper;
import com.funi.platform.lshc.mapper.census.SysConfigMapper;
import com.funi.platform.lshc.service.BasicService;
import com.funi.platform.lshc.support.CensusConstants;
import com.funi.platform.lshc.support.UserManager;
import com.funi.platform.lshc.vo.census.LshcRegionVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2019/6/17.3:56 PM
 */
public class BasicServiceImpl implements BasicService {
    @Resource
    private AppInvoker ccsAppInvoker;
    @Resource
    private SysConfigMapper sysConfigMapper;
    @Resource
    private UserManager userManager;
    @Resource
    private RegiInfoMapper regiInfoMapper;

    @Override
    public List<ComboboxDto> findDictionaryListName(String type, String showALL) {
        boolean isShowAll = false;
        if(StringUtils.isNoneBlank(showALL) && CensusConstants.SHOW_ALL.equals(showALL)) {
            isShowAll = true;
        }
        List<ComboboxDto> rtList = initNewSelect(isShowAll);
        if(CensusConstants.DICTIONARY_TYPE_HOUSE_STATUS.equals(type)){
            return CensusConstants.DICTIONARY_HOUSE_STATUS;
        }
        String tableName = getDictionaryTable(type);
        if(! CensusConstants.DICTIONARY_DEFAULT_TABLE_NAME.equals(tableName)) {
            type = null;
        }
        List<ComboboxDto> comboboxDtoList = sysConfigMapper.selectSysConfDetaNameByType(tableName, type);
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }

    /**
     * 初始化选择框集合数据
     * @param addAllSelect 是否添加'全部'选项
     * @return
     */
    private List<ComboboxDto> initNewSelect(boolean addAllSelect) {
        List<ComboboxDto> rtList = new ArrayList<>();
        if(addAllSelect) {
            rtList.add(new ComboboxDto("全部", ""));
        }
        return rtList;
    }

    private String getDictionaryTable(String type) {
        switch (type){
//            case CensusConstants.DICTIONARY_TYPE_HOUSE_STRUCTURE :
//                return "A12_D";
//            case CensusConstants.DICTIONARY_TYPE_HOUSE_USE :
//                return "A14_D";
            case CensusConstants.DICTIONARY_TYPE_ID_TYPE :
                return "A34_D";
            case CensusConstants.DICTIONARY_TYPE_GENDER :
                return "A38_D";
            default:
                return "LSHC_DICTIONARY";
        }
    }

    @Override
    public List<ComboboxDto> findRegionList() {
        List<LshcRegionVo> allRegionList = new ArrayList<>();
        // 查询全部区县
        List<LshcRegionVo> regionVoList1 = getRegionVoList(CensusConstants.REGION_TYPE_CITY, null);
        if(CollectionUtils.isNotEmpty(regionVoList1)) {
            // 遍历查询街道信息
            for(LshcRegionVo lshcRegionVo1 : regionVoList1) {
                String code1 = lshcRegionVo1.getCode();
                List<LshcRegionVo> regionVoList2 = getRegionVoList(CensusConstants.REGION_TYPE_REGION, code1);
                if(CollectionUtils.isNotEmpty(regionVoList2)) {
                    for(LshcRegionVo lshcRegionVo2 : regionVoList2) {
                        String code2 = lshcRegionVo2.getCode();
                        List<LshcRegionVo> regionVoList3 = getRegionVoList(CensusConstants.REGION_TYPE_COUNTY, code2);
                        if(CollectionUtils.isNotEmpty(regionVoList3)) {
                            allRegionList.addAll(regionVoList3);
                        }
                    }
                }
            }
        }
        if(CollectionUtils.isNotEmpty(allRegionList)) {
            List<ComboboxDto> comboboxDtoList = initNewSelect(false);
            for(LshcRegionVo lshcRegionVo : allRegionList) {
                comboboxDtoList.add(new ComboboxDto(lshcRegionVo.getName()));
            }
            return comboboxDtoList;
        }
        return null;
    }

    @Override
    public List<ComboboxDto> findAllRegionList(String showALL) {
        return convertComboboxDto(getRegionVoList(CensusConstants.REGION_TYPE_CITY, null), showALL);
    }

    @Override
    public List<ComboboxDto> findAllRegionListByCityId(String regionId, String showALL) {
        return convertComboboxDto(getRegionVoList(CensusConstants.REGION_TYPE_REGION, regionId), showALL);
    }

    @Override
    public List<ComboboxDto> findAllStreetListByRegionId(String blockId, String showALL) {
        return convertComboboxDto(getRegionVoList(CensusConstants.REGION_TYPE_COUNTY, blockId), showALL);
    }

    @Override
    public String findCurrentUserRegionCode(String userId) {
        LshcRegion lshcRegion = getCurrentUserRegion(userId);
        String code = lshcRegion.getCode();
        if(StringUtils.isBlank(code)) {
            throw new BizException("获取当前登录用户所属区域code异常：村(居）委会没有数据");
        }
        return code;
    }

    private LshcRegion getCurrentUserRegion(String userId) {
        if(StringUtils.isBlank(userId)) {
            userId = userManager.findUser().getUserId();
        }
        String result = getAppInvocationResult("role.getRegionsByUserId",  userId);
        if(StringUtils.isBlank(result)) {
            throw new BizException("获取当前登录用户所属区域code异常：接口返回为空");
        }
        String data = JSONObject.parseObject(result).getString("data");
        // 获得区县信息
        List<SecurityRegionDto> securityRegionDtoList = JSONObject.parseArray(data, SecurityRegionDto.class);
        if(CollectionUtils.isEmpty(securityRegionDtoList)) {
            throw new BizException("获取当前登录用户所属区域code异常：县区没有数据");
        }
        // 获取区数据
        SecurityRegionDto securityRegionDto = securityRegionDtoList.get(0);
        // 获取街道信息
        List<LshcRegion> regionsList = securityRegionDto.getRegionsList();
        if(CollectionUtils.isEmpty(regionsList)) {
            throw new BizException("获取当前登录用户所属区域code异常：街道办事处没有数据");
        }
        // 获取社区信息
        LshcRegion lshcRegion = regionsList.get(0);
        List<LshcRegion> regionsList1 = lshcRegion.getRegionsList();
        if(CollectionUtils.isEmpty(regionsList1)) {
            throw new BizException("获取当前登录用户所属区域code异常：社区没有数据");
        }
        return regionsList1.get(0);
    }

    @Override
    public String findCurrentUserRegionName(String userId) {
        LshcRegion lshcRegion = getCurrentUserRegion(userId);
        String name = lshcRegion.getName();
        if(StringUtils.isBlank(name)) {
            throw new BizException("获取当前登录用户所属区域code异常：村(居）委会没有数据");
        }
        return name;
    }

    @Override
    public List<String> findCurrentUserRegionCodeList(String userId) {
        if(StringUtils.isBlank(userId)) {
            userId = userManager.findUser().getUserId();
        }
        String result = getAppInvocationResult("role.getRegionsByUserId", userId);
        if(StringUtils.isBlank(result)) {
            throw new BizException("获取当前登录用户所属区域code异常");
        }
        String data = JSONObject.parseObject(result).getString("data");
        List<SecurityRegionDto> securityRegionDtoList = JSONObject.parseArray(data, SecurityRegionDto.class);
        if(CollectionUtils.isEmpty(securityRegionDtoList)) {
            throw new BizException("获取当前登录用户所属区域code异常：县区没有数据");
        }
        List<String> regionCodeList = new ArrayList<>();
        for(SecurityRegionDto securityRegionDto : securityRegionDtoList) {
            // 第一级是县区数据
            List<LshcRegion> regionsList = securityRegionDto.getRegionsList();
            if(CollectionUtils.isNotEmpty(regionsList)) {
                // 第二级是街道数据
                for(LshcRegion lshcRegion : regionsList) {
                    // 第三级是社区数据
                    List<LshcRegion> regionsList1 = lshcRegion.getRegionsList();
                    if(CollectionUtils.isNotEmpty(regionsList1)) {
                        for(LshcRegion lshcRegion1 : regionsList1) {
                            regionCodeList.add(lshcRegion1.getCode());
                        }
                    }
                }
            }
        }
        return regionCodeList;
    }

    @Override
    public String findCurrentUserRegionCodeString() {
        List<String> currentUserRegionCodeList = findCurrentUserRegionCodeList(null);
        if(CollectionUtils.isNotEmpty(currentUserRegionCodeList)) {
            return StringUtils.join(currentUserRegionCodeList, ",");
        }
        return null;
    }

    @Override
    public List<String> findCurrentUserRegionCodeListByStreet() {
        String userId = userManager.findUser().getUserId();
        String result = getAppInvocationResult("role.getRegionsByUserId", userId);
        if(StringUtils.isBlank(result)) {
            throw new BizException("获取当前登录用户所属区域code异常");
        }
        String data = JSONObject.parseObject(result).getString("data");
        List<SecurityRegionDto> securityRegionDtoList = JSONObject.parseArray(data, SecurityRegionDto.class);
        if(CollectionUtils.isEmpty(securityRegionDtoList)) {
            throw new BizException("获取当前登录用户所属区域code异常：县区没有数据");
        }
        List<String> regionCodeList = new ArrayList<>();
        for(SecurityRegionDto securityRegionDto : securityRegionDtoList) {
            // 第一级是县区数据
            List<LshcRegion> regionsList = securityRegionDto.getRegionsList();
            if(CollectionUtils.isNotEmpty(regionsList)) {
                // 第二级是街道数据
                for(LshcRegion lshcRegion : regionsList) {
                    // 第三级是社区数据
                    List<LshcRegion> regionsList1 = lshcRegion.getRegionsList();
                    if(CollectionUtils.isNotEmpty(regionsList1)) {
                        List<String> temp = new ArrayList<>();
                        for(LshcRegion lshcRegion1 : regionsList1) {
                            temp.add(lshcRegion1.getCode());
                        }
//                      regionCodeList.add("'" + StringUtils.join(temp, "','") + "'");
                        //在普查中有的才返回
                        String regionStr = StringUtils.join(temp, ",");
                        RegiInfo rInfo = new RegiInfo();
                        rInfo.setCommon(regionStr);
                        List<RegiInfo> regList = regiInfoMapper.selectRegiInfoByUniqueQuery(rInfo);
                        if(null != regList && regList.size() > 0){
                            temp.clear();
                            for(RegiInfo r:regList){
                                if(null != r && !"".equals(r.getCommon())){
                                    temp.add(r.getCommon());
                                }
                            }
                            regionCodeList.add("" + StringUtils.join(temp, ",") + "");
                        }

                    }
                }
            }
        }
        return regionCodeList;
    }

    private List<ComboboxDto> convertComboboxDto(List<LshcRegionVo> lshcRegionVoList, String showALL) {
        List<ComboboxDto> comboboxDtoList = null;
        if(CollectionUtils.isNotEmpty(lshcRegionVoList)) {
            boolean isShowAll = false;
            if(StringUtils.isNoneBlank(showALL) && CensusConstants.SHOW_ALL.equals(showALL)) {
                isShowAll = true;
            }
            comboboxDtoList = initNewSelect(isShowAll);
            for(LshcRegionVo lshcRegionVo : lshcRegionVoList) {
                comboboxDtoList.add(new ComboboxDto(lshcRegionVo));
            }
        }
        return comboboxDtoList;
    }

    /**
     * 根据类型和区域code查询下级区域
     * @param type
     * @param code
     * @return
     */
    private List<LshcRegionVo> getRegionVoList(Integer type, String code) {
        List<LshcRegionVo> lshcRegionVoList = null;
        String rpcName = "role.getRegionsInfo";
        String jsonParam;
        if(CensusConstants.REGION_TYPE_CITY.equals(type)) {
            jsonParam = "{type:" + type + "}";
        } else {
            jsonParam = "{type:"+type+",code:\"" + code + "\"}";
        }
        AppInvocationResult appInvocationResult = ccsAppInvoker.invoke(rpcName, jsonParam);
        if (appInvocationResult.isSuccess()) {
            String result = appInvocationResult.getData();
            if(StringUtils.isNotBlank(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String data = jsonObject.getString("data");
                lshcRegionVoList =  JSONObject.parseArray(data, LshcRegionVo.class);
            }
        } else {
            String errorCode = appInvocationResult.getCode();
            String errorMessage = appInvocationResult.getMessage();
            throw new BizException(errorMessage + ",error code:" + errorCode);
        }
        return lshcRegionVoList;
    }

    /**
     * 根据指定的RPC服务名称和json参数调用rpc服务，并返回结果
     * @param rpcName
     * @param jsonParam
     * @return
     */
    private String getAppInvocationResult(String rpcName, String jsonParam) {
        AppInvocationResult appInvocationResult = ccsAppInvoker.invoke(rpcName, jsonParam);
        if (appInvocationResult.isSuccess()) {
            return appInvocationResult.getData();
        } else {
            String errorCode = appInvocationResult.getCode();
            String errorMessage = appInvocationResult.getMessage();
            throw new BizException(errorMessage + ",error code:" + errorCode);
        }
    }
}
