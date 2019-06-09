package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.dto.ChargeDepositDto;
import com.funi.platform.lshc.dto.ChargeDto;
import com.funi.platform.lshc.query.charge.ArrearageQuery;
import com.funi.platform.lshc.query.charge.ChargeHistoryQuery;
import com.funi.platform.lshc.query.charge.ChargeQuery;
import com.funi.platform.lshc.query.charge.DepositQuery;
import com.funi.platform.lshc.service.ChargeService;
import com.funi.platform.lshc.utils.ResultUtils;
import com.funi.platform.lshc.vo.ArrearageVo;
import com.funi.platform.lshc.vo.ChargeDetailVo;
import com.funi.platform.lshc.vo.ChargeHistoryVo;
import com.funi.platform.lshc.vo.ChargeHouseVo;
import com.funi.platform.lshc.vo.ChargeVo;
import com.funi.platform.lshc.vo.DepositVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收费管理
 * Created by sam on 2018/11/4.11:16 PM
 */
@Controller
@RequestMapping("/charge/")
public class ChargeController extends BaseController {
    @Autowired
    private ChargeService chargeService;

    /**
     * 获取收费合同列表
     *
     * @return
     */
    @RequestMapping(value = "getChargeList")
    @ResponseBody
    public List<ChargeVo> getChargeList(ChargeQuery chargeQuery) {
        return chargeService.findChargeList(chargeQuery, getUserInfo());
    }

    /**
     * 根据合同ID查询合同房屋明细
     * @param contractId
     * @return
     */
    @RequestMapping(value = "getChargeHouseVoList")
    @ResponseBody
    public List<ChargeHouseVo> getChargeHouseVoList(@RequestParam String contractId) {
        return chargeService.findChargeHouseVoList(contractId);
    }

    /**
     * 根据合同ID查询收费详情
     * @param contractId
     * @return
     */
    @RequestMapping(value = "getChargeDetailVo")
    @ResponseBody
    public ChargeDetailVo getChargeDetailVo(@RequestParam String contractId) {
        return chargeService.findChargeDetailVo(contractId, null);
    }

    /**
     * 根据合同ID和缴费截止日期计算租金
     * @param contractId
     * @param chargeEndDate
     * @return
     */
    @RequestMapping(value = "getChargeDetailByMonth")
    @ResponseBody
    public ChargeDetailVo getChargeDetailByMonth(@RequestParam String contractId, @RequestParam String chargeEndDate) {
        return chargeService.findChargeDetailVo(contractId, chargeEndDate);
    }

    /**
     * 根据合同ID批量生成欠费记录
     * @param contractIds
     * @return
     */
    @RequestMapping(value = "generateArrearageHistory")
    @ResponseBody
    public Object generateArrearageHistory(@RequestParam String contractIds) {
        try {
            chargeService.generateArrearageHistory(contractIds);
            return ResultUtils.getSuccessResult("票据生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult("票据生成失败:" + e.getMessage());
        }
    }

    /**
     * 生成票据
     * @param chargeDto
     * @return
     */
    @RequestMapping(value = "generateBill")
    @ResponseBody
    public Object generateBill(ChargeDto chargeDto) {
        try {
            return ResultUtils.getSuccessResult(chargeService.createBill(chargeDto, getUserInfo()), "票据生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult("票据生成失败:" + e.getMessage());
        }
    }

    /**
     * 查询收费记录列表
     * @return
     */
    @RequestMapping(value = "getChargeHisList")
    @ResponseBody
    public List<ChargeHistoryVo> getChargeHisList(ChargeHistoryQuery chargeHistoryQuery) {
        return chargeService.findChargeHistoryVoList(chargeHistoryQuery, getUserInfo());
    }

    /**
     * 撤销收费
     * @param historyId
     * @param backReason
     * @return
     */
    @RequestMapping(value = "cancelCharge")
    @ResponseBody
    public Object cancelCharge(@RequestParam String historyId, @RequestParam String backReason) {
        try {
            chargeService.cancelCharge(getUserInfo(), historyId, backReason);
            return ResultUtils.getSuccessResult("撤销收费成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("撤销收费失败:" + e.getMessage());
        }
    }

    /**
     * 分页查询催缴合同列表
     * @param arrearageQuery
     * @return
     */
    @RequestMapping(value = "getArrearageVoList")
    @ResponseBody
    public List<ArrearageVo> getArrearageVoList(ArrearageQuery arrearageQuery) {
        return chargeService.findArrearageVoList(arrearageQuery, getUserInfo());
    }

    /**
     * 根据查询条件导出欠费列表（不分页）
     * @param arrearageQuery
     * @param response
     */
    @RequestMapping("exportArrearageVoList")
    public void exportArrearageVoList(ArrearageQuery arrearageQuery, HttpServletResponse response) {
        try {
            chargeService.exportArrearageVoList(arrearageQuery, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询保证金收取列表
     * @param depositQuery
     * @return
     */
    @RequestMapping(value = "getDepositVoList")
    @ResponseBody
    public List<DepositVo> getDepositVoList(DepositQuery depositQuery) {
        return chargeService.findDepositVoList(depositQuery, getUserInfo());
    }

    /**
     * 根据查询条件导出保证金列表（不分页）
     * @param depositQuery
     * @param response
     */
    @RequestMapping("exportDepositVoList")
    public void exportDepositVoList(DepositQuery depositQuery, HttpServletResponse response) {
        try {
            chargeService.exportDepositVoList(depositQuery, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 收取保证金
     * @param chargeDepositDto
     * @return
     */
    @RequestMapping(value = "chargeDeposit")
    @ResponseBody
    public Object chargeDeposit(ChargeDepositDto chargeDepositDto) {
        try {
            chargeDepositDto.setUserInfo(getUserInfo());
            chargeService.chargeDeposit(chargeDepositDto);
            return ResultUtils.getSuccessResult("保证金收取成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult("保证金收取失败");
        }
    }

    /**
     * 退回保证金
     * @param contractId
     * @param refundReason
     * @return
     */
    @RequestMapping(value = "refundDeposit")
    @ResponseBody
    public Object refundDeposit(@RequestParam String contractId, @RequestParam String refundReason) {
        try {
            chargeService.refundDeposit(getUserInfo(), contractId, refundReason);
            return ResultUtils.getSuccessResult("保证金退费成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("保证金退费失败");
        }
    }

    /**
     * 获取票据打印的模板名称
     * @return
     */
    @RequestMapping(value = "getBillTemplateName")
    @ResponseBody
    public Object getBillTemplateName() {
        try {
            return ResultUtils.getSuccessResult(chargeService.findBillTemplateName(), "保证金退费成功");
        } catch (Exception e) {
            return ResultUtils.getFailResult("保证金退费失败");
        }
    }

}
