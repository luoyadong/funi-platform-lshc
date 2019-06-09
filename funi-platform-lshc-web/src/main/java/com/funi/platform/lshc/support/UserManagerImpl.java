package com.funi.platform.lshc.support;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.biz.support.CurrentUserAccessor;
import com.funi.platform.lshc.enumatation.Conclusion;
import com.funi.platform.lshc.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 3
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired(required = false)
    private CurrentUserAccessor currentUserAccessor;
    @Resource
    private CommonService commonService;

    @Override
    public CurrentUser findUser() {
        return (CurrentUser)currentUserAccessor.getCurrentUser();
    }

    @Override
    public String findRegionCode() {
        return Conclusion.REGION_CODE.getName();//findUser().getLogicRegionCodeList().get(0).getDm();
    }

    @Override
    public List<Dict> findAuthority() {
        List<Dict> authorityList = null;
        // 如果当前登录用户所属机构是房管局，不做权限控制，允许查询全部数据
        if(findUser().getOrganization().getDm().indexOf("ghouse_zf")==0){
            return null;
        } else if(findUser().getOrganization().getDm().indexOf("ghouse_dg")==0){
            // 如果当前登录用户所属机构属于代管公司，只返回所属机构
            for(Dict role:findUser().getRoles()){
                if(role.getDm().indexOf("ROLE_GHOUSE_DG_ADMIN")!=-1){
                    authorityList = new ArrayList<>();
                    authorityList.add(findUser().getOrganization());
                    return authorityList;
                }
            }
        } else {
            throw new RuntimeException("非房管局和代管公司用户的无效用户");
        }
        return null;
    }
}
