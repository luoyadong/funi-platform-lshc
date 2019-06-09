package com.funi.platform.lshc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.funi.framework.app.invocation.AppInvoker;
import com.funi.framework.app.invocation.bo.AppInvocationResult;
import com.funi.framework.biz.BizException;
import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.dto.GHouseOrgDto;
import com.funi.platform.lshc.dto.GHouseUserInfoDto;
import com.funi.platform.lshc.dto.ManageOrgDto;
import com.funi.platform.lshc.enumatation.CommonBizType;
import com.funi.platform.lshc.mapper.sys.SysConfDetaMapper;
import com.funi.platform.lshc.service.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2018/11/22.9:48 AM
 */
public class CommonServiceImpl implements CommonService {
    @Resource
    private SysConfDetaMapper sysConfDetaMapper;
    @Resource
    private AppInvoker ccsAppInvoker;

    @Override
    public List<ComboboxDto> findDictionaryList(String type, Boolean isForm) {
        List<ComboboxDto> rtList = new ArrayList<>();
        List<ComboboxDto> comboboxDtoList = sysConfDetaMapper.selectSysConfDetaByType(type);
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            if(isForm == null || ! isForm) {
                ComboboxDto allDto = new ComboboxDto("全部", "");
                rtList.add(allDto);
            }
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }

    @Override
    public List<ComboboxDto> findDictionaryListName(String type) {
        List<ComboboxDto> rtList = new ArrayList<>();
        ComboboxDto allDto = new ComboboxDto("全部", "");
        rtList.add(allDto);
        List<ComboboxDto> comboboxDtoList = sysConfDetaMapper.selectSysConfDetaNameByType(type);
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }

    @Override
    public List<ManageOrgDto> findManagerOrgList(String type ,CurrentUser user) {
        List<ManageOrgDto> rtList = new ArrayList<ManageOrgDto>();
        //rpc远程服务名
        String rpcName = "organization.findZggfOrgList";
        //param
        String jsonParam;

        if(null != type &&
                type.equals(String.valueOf(CommonBizType.ALL_ORG.getBizType()))){
            jsonParam = "{isAll:true,userId:\"\"}";
        }else{
            String userId = user.getUserId();
            jsonParam = "{isAll:false,userId:\""+userId+"\"}";
        }

        AppInvocationResult appInvocationResult = ccsAppInvoker.invoke(rpcName, jsonParam);
        if (appInvocationResult.isSuccess()) {
            rtList =  JSONObject.parseArray(appInvocationResult.getData(), ManageOrgDto.class);
        } else {
            String errorCode = appInvocationResult.getCode();
            String errorMessage = appInvocationResult.getMessage();
            throw new BizException(errorMessage + ",error code:" + errorCode);
        }

        return rtList;

    }

    @Override
    public List<String> getCurrentScopeOrgIdList(CurrentUser user) {
        // 查询当前登录用户权限范围内的机构编码集合
        List<ManageOrgDto> managerOrgList = findManagerOrgList(CommonBizType.USER_ORG.getBizType().toString(), user);
        if(CollectionUtils.isNotEmpty(managerOrgList)) {
            List<String> currentScopeOrgIdList = new ArrayList<>();
            for(ManageOrgDto manageOrgDto : managerOrgList) {
                currentScopeOrgIdList.add(manageOrgDto.getOrgCode());
            }
            return currentScopeOrgIdList;
        }
        return null;
    }

    @Override
    public boolean isScopeDate(CurrentUser user, String bizCreateOrgCode) {
        if(StringUtils.isNotBlank(bizCreateOrgCode)) {
            // 获得当前登录用户权限范围内的机构编码集合
            List<String> currentScopeOrgIdList = getCurrentScopeOrgIdList(user);
            if(CollectionUtils.isNotEmpty(currentScopeOrgIdList)) {
                return currentScopeOrgIdList.contains(bizCreateOrgCode);
            }
        }
        return false;
    }

    @Override
    public List<GHouseUserInfoDto> getUserList(CurrentUser user) {
        String rpcName = "user.findGHouseUserList";
        List<GHouseUserInfoDto> gHouseUserInfoDtoList = null;
        AppInvocationResult appInvocationResult = ccsAppInvoker.invoke(rpcName, user.getId());
        if (appInvocationResult.isSuccess()) {
            gHouseUserInfoDtoList =  JSONObject.parseArray(appInvocationResult.getData(), GHouseUserInfoDto.class);
        } else {
            String errorCode = appInvocationResult.getCode();
            String errorMessage = appInvocationResult.getMessage();
            throw new BizException(errorMessage + ",error code:" + errorCode);
        }
        return gHouseUserInfoDtoList;
    }

    @Override
    public GHouseOrgDto getOrgByUserId(String userId) {
        String rpcName = "organization.loadByUserId";
        GHouseOrgDto gHouseOrgDto = null;
        AppInvocationResult appInvocationResult = ccsAppInvoker.invoke(rpcName, userId);
        if (appInvocationResult.isSuccess()) {
            gHouseOrgDto =  JSONObject.parseObject(appInvocationResult.getData(), GHouseOrgDto.class);
        } else {
            String errorCode = appInvocationResult.getCode();
            String errorMessage = appInvocationResult.getMessage();
            throw new BizException(errorMessage + ",error code:" + errorCode);
        }
        return gHouseOrgDto;
    }
}
