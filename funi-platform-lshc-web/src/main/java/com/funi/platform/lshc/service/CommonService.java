package com.funi.platform.lshc.service;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.dto.GHouseOrgDto;
import com.funi.platform.lshc.dto.GHouseUserInfoDto;
import com.funi.platform.lshc.dto.ManageOrgDto;

import java.util.List;

/**
 * Created by sam on 2018/11/22.9:47 AM
 */
public interface CommonService {
    /**
     * 根据字典类型获取该类型字典数据集合
     * @param type
     * @param isForm 如果是form表单，不需要添加全部选项
     * @return
     */
    List<ComboboxDto> findDictionaryList(String type, Boolean isForm);

    /**
     * 返回以name为key，以name为value
     * @param type
     * @return
     */
    List<ComboboxDto> findDictionaryListName(String type);

    /**
     * 获取管理机构集合
     * @param type 枚举类型
     * @return
     */
    List<ManageOrgDto> findManagerOrgList(String type, CurrentUser user);

    /**
     * 获得当前登录用户角色权限范围内的机构编码集合
     * @param user
     * @return
     */
    List<String> getCurrentScopeOrgIdList(CurrentUser user);

    /**
     * 判断当前用户是否有操作指定业务件的权限
     * @param user 当前登录用户
     * @param bizCreateOrgCode 业务件创建者所属的机构编码
     * @return
     */
    boolean isScopeDate(CurrentUser user, String bizCreateOrgCode);

    /**
     * 根据当前登录用户获取用户权限范围内的全部房管员列表
     * @param user
     * @return
     */
    List<GHouseUserInfoDto> getUserList(CurrentUser user);

    /**
     * 根据用户ID查询机构信息
     * @param userId
     * @return
     */
    GHouseOrgDto getOrgByUserId(String userId);
}
