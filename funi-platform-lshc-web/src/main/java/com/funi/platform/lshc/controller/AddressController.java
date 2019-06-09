package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.house.AddrInfo;
import com.funi.platform.lshc.query.address.AddressChangeQuery;
import com.funi.platform.lshc.query.address.AddressQuery;
import com.funi.platform.lshc.service.AddressService;
import com.funi.platform.lshc.utils.ResultUtils;
import com.funi.platform.lshc.vo.AddrChangeVo;
import com.funi.platform.lshc.vo.AddressEditVo;
import com.funi.platform.lshc.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sam on 2018/11/6.11:06 PM
 */
@Controller
@RequestMapping("/address/")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    /**
     * 添加小区
     * @param addrInfo
     * @return
     */
    @RequestMapping("addAddress")
    @ResponseBody
    public Object addAddress(AddrInfo addrInfo) {
        try {
            addressService.createAddress(addrInfo, getUserInfo());
            return ResultVo.newResult("添加成功");
        } catch (Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("添加失败");
            resultVo.setSuccess(false);
            return resultVo;
        }
    }

    /**
     * 分页查询小区列表
     * @param addressQuery
     * @return
     */
    @RequestMapping("getAddressList")
    @ResponseBody
    public List<AddressVo> getAddressList(AddressQuery addressQuery) {
        return addressService.findAddressVoList(addressQuery, getUserInfo());
    }

    /**
     * 根据小区ID查询小区详情
     * @param addressId
     * @return
     */
    @RequestMapping("getAddress")
    @ResponseBody
    public Object getAddress(String addressId) {
        return ResultVo.newResult(addressService.findAddrInfo(addressId));
    }

    /**
     * 修改小区地址
     * @param addressEditVo
     * @return
     */
    @RequestMapping(value = "editAddress")
    @ResponseBody
    public Object editAddress(AddressEditVo addressEditVo) {
        try {
            addressService.updateAddress(addressEditVo, getUserInfo());
            return ResultUtils.getSuccessResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult("修改失败:" + e.getMessage());
        }
    }

    /**
     * 分页查询地址变更列表
     * @param addressChangeQuery
     * @return
     */
    @RequestMapping("getAddressChangeList")
    @ResponseBody
    public List<AddrChangeVo> getAddressChangeList(AddressChangeQuery addressChangeQuery) {
        return addressService.findAddressChangeList(addressChangeQuery);
    }

    /**
     * 注销地址
     * @param addrInfo
     * @return
     */
    @RequestMapping("cancelAddress")
    @ResponseBody
    public Object cancelAddress(AddrInfo addrInfo) {
        try {
            addressService.removeAddress(addrInfo);
            return ResultUtils.getSuccessResult("注销成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("注销失败:" + e.getMessage());
        }
    }

    /**
     * 自动补全
     * @param addressQuery
     * @return 小区列表
     */
    @RequestMapping("auto/complete")
    @ResponseBody
    public List<ComboboxDto> autoComplete(AddressQuery addressQuery){
        addressQuery.setLimit(10);
        return addressService.findByQueryForComplete(addressQuery);
    }

}
