package com.funi.platform.lshc.controller;

import com.funi.framework.data.migrate.excel.ExcelImporter;
import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.platform.lshc.query.MapHouseQuery;
import com.funi.platform.lshc.query.address.AddressQuery;
import com.funi.platform.lshc.service.AddressService;
import com.funi.platform.lshc.service.MapHouseService;
import com.funi.platform.lshc.utils.ExcelUtil;
import com.funi.platform.lshc.vo.AddressVo;
import com.funi.platform.lshc.vo.ExcelDemoVo;
import com.funi.platform.lshc.vo.MapHouseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:公房分布图相关操作
 * @author by yadong.luo on 2018/11/12
 */
@Controller
@RequestMapping("/HouseMapController")
public class HouseMapController extends BaseController {

        @Resource
        private AddressService addressService;

        @Resource
        private MapHouseService mapHouseService;

        /**
         * 查询全域小区列表
         * @param keyWord
         * @return
         */
        @RequestMapping(value = "/findCommunityList",method = RequestMethod.POST)
        @ResponseBody
        public List<AddressVo> findCommunityList(String keyWord){

                AddressQuery addressQuery = new AddressQuery();
                if(StringUtils.isNotBlank(keyWord)){
                        addressQuery.setKeywords(keyWord);
                }
                List<AddressVo> addrList = addressService.findAddressVoList(addressQuery, getUserInfo());
                return addrList;
        }

        /**
         * 根据小区id，查询小区房屋列表
         * @param query:小区编号查询，对象
         * @return 房屋列表
         */
        @RequestMapping(value = "/findHouList",method = RequestMethod.POST)
        @ResponseBody
        public List<MapHouseVo> findHouList(MapHouseQuery query){
                List<MapHouseVo> mapHouList = mapHouseService.findMapHouseList(query);
                return mapHouList;
        }
        /**
         * 导出查询数据
         * @throws Exception
         */
        @RequestMapping("/excelExport")
        public void excelExport(String qParam, HttpServletResponse response)throws Exception{
                List<ExcelDemoVo> queryList = new ArrayList<ExcelDemoVo>();
                ExcelDemoVo object = new ExcelDemoVo();
                object.setName("房屋");
                object.setRegiTime(new Date());
                object.setTotalSetNum(new Integer(10));
                queryList.add(object);
                ExcelUtil.excelExport("房屋管理数据.xls","房屋数据",queryList,response);
        }
        /**
         * 导入数据
         * @throws Exception
         */
        @RequestMapping("/importExport")
        @ResponseBody
        public Map importExport(MultipartFile uploadFile) throws Exception{
                //返回报文头
                Map<String,Object> responseMap = new HashMap<String,Object>();
                List<ExcelDemoVo> queryList = null;
                ExcelImporter<ExcelDemoVo> importer = new ExcelImporter<ExcelDemoVo>();
                try{
                        queryList = importer.setStartRows(2)
                                .setHeadRows(1)
                                .setItemClass(ExcelDemoVo.class)
                                .execute(uploadFile.getInputStream());
                        for(ExcelDemoVo obj:queryList){
                                System.out.println(obj.getName());
                        }
                        responseMap.put("status",200);
                }catch(Exception e){
                        responseMap.put("status",500);
                }
                return responseMap;
        }
}
