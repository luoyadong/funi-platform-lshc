package com.funi.platform.lshc.utils;


import com.funi.framework.data.migrate.excel.ExcelExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ExcelUtil {
    /**
     * 导出excel文件
     * @param fileName 导出文件名称
     * @param sheetName sheet名称
     * @param list 需要导出的数据结果集，eg:List<VObject>
     * @param response
     * @throws Exception
     * ExcelUtil.excelExport("批量查询导出数据.xls","批量查询导出",queryList,response);
     */
    public static void excelExport(String fileName,String sheetName,List list,HttpServletResponse response)throws Exception{
        try {
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + java.net.URLEncoder.encode(fileName, "utf-8"));
            //  客户端不缓存 
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            ExcelExporter excelExporter = new ExcelExporter();
            excelExporter.setSheetName(sheetName).setData(list).execute(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            response.getOutputStream().close();
        }
    }
}
