package com.funi.platform.lshc.vo;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;

/**
 * @Description: [Excel导入、导出对象demo]
 * @Author: [yadong.luo]
 * @Version: [v1.0]
 */
public class ExcelDemoVo {
    @Excel(name="姓名")
    private String name;
    @Excel(name="查询结果(套数)",cellType= Excel.CellType.NUMERIC)
    private Integer totalSetNum;//套数
    @Excel(name="房屋登记时间",exportFormat="yyyy-mm-dd HH:mm:ss")
    private Date regiTime;//房屋登记时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalSetNum() {
        return totalSetNum;
    }

    public void setTotalSetNum(Integer totalSetNum) {
        this.totalSetNum = totalSetNum;
    }

    public Date getRegiTime() {
        return regiTime;
    }

    public void setRegiTime(Date regiTime) {
        this.regiTime = regiTime;
    }
}
