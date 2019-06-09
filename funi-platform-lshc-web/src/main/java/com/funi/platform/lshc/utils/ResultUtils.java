package com.funi.platform.lshc.utils;

import com.funi.framework.mvc.eic.vo.ResultVo;

/**
 * Created by sam on 2018/11/20.9:44 PM
 */
public class ResultUtils {
    /**
     * 后台处理成功的返回对象
     * @param message
     * @return
     */
    public static ResultVo getSuccessResult(String message) {
        return getSuccessResult(null, message);
    }

    /**
     * 后台处理成功的返回对象
     * @param message
     * @return
     */
    public static ResultVo getSuccessResult(Object result, String message) {
        ResultVo resultVo = new ResultVo(result);
        resultVo.setMessage(message);
        return resultVo;
    }

    /**
     * 后台处理失败的返回对象
     * @param message
     * @return
     */
    public static ResultVo getFailResult(String message) {
        ResultVo resultVo = new ResultVo(null);
        resultVo.setMessage(message);
        resultVo.setSuccess(false);
        return resultVo;
    }

}
