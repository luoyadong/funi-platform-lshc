package com.funi.platform.lshc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.funi.framework.app.invocation.AppInvoker;
import com.funi.framework.app.invocation.bo.AppInvocationResult;
import com.funi.framework.biz.BizException;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.mapper.census.SysConfigMapper;
import com.funi.platform.lshc.service.BasicService;
import com.funi.platform.lshc.support.CensusConstants;
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

    @Override
    public List<ComboboxDto> findDictionaryListName(String type) {
        List<ComboboxDto> rtList = new ArrayList<>();
        ComboboxDto allDto = new ComboboxDto("全部", "");
        rtList.add(allDto);
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

    private String getDictionaryTable(String type) {
        switch (type){
            case CensusConstants.DICTIONARY_TYPE_HOUSE_STRUCTURE :
                return "A12_D";
            case CensusConstants.DICTIONARY_TYPE_HOUSE_USE :
                return "A14_D";
            case CensusConstants.DICTIONARY_TYPE_ID_TYPE :
                return "A34_D";
            case CensusConstants.DICTIONARY_TYPE_GENDER :
                return "A38_D";
            default:
                return "LSHC_DICTIONARY";
        }
    }

    @Override
    public List<ComboboxDto> findAllRegionList() {
        return convertComboboxDto(getRegionVoList(CensusConstants.REGION_TYPE_CITY, null));
    }

    @Override
    public List<ComboboxDto> findAllRegionListByCityId(String regionId) {
        return convertComboboxDto(getRegionVoList(CensusConstants.REGION_TYPE_REGION, regionId));
    }

    @Override
    public List<ComboboxDto> findAllStreetListByRegionId(String blockId) {
        return convertComboboxDto(getRegionVoList(CensusConstants.REGION_TYPE_COUNTY, blockId));
    }

    private List<ComboboxDto> convertComboboxDto(List<LshcRegionVo> lshcRegionVoList) {
        List<ComboboxDto> comboboxDtoList = null;
        if(CollectionUtils.isNotEmpty(lshcRegionVoList)) {
            comboboxDtoList = new ArrayList<>();
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
}
