package com.funi.platform.lshc.support;


import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.biz.eic.bo.Dict;

import java.util.List;

/**
 * @author 3
 */
public interface UserManager {
    /**
     * 获取当前认证用户
     * @return
     */
    CurrentUser findUser();

    /**
     * 获取区域编码
     * @return
     */
    String findRegionCode();

    /**
     * 获取机构数据权限
     * @return
     */
    List<Dict> findAuthority();
}
