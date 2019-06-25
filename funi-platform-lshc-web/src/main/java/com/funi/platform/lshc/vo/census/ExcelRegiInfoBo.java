package com.funi.platform.lshc.vo.census;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by sam on 2019/6/15.1:09 PM
 */
public class ExcelRegiInfoBo {
   List<ExcelRegiInfoVo> excelRegiInfo;

    public List<ExcelRegiInfoVo> getExcelRegiInfo() {
        return excelRegiInfo;
    }

    public void setExcelRegiInfo(List<ExcelRegiInfoVo> excelRegiInfo) {
        this.excelRegiInfo = excelRegiInfo;
    }
}
