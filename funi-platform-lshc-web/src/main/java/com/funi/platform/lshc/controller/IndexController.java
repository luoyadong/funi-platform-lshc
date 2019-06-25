package com.funi.platform.lshc.controller;

import com.funi.framework.mvc.eic.controller.BaseController;
import com.funi.framework.mvc.eic.vo.ResultVo;
import com.funi.platform.lshc.query.census.NoticeQuery;
import com.funi.platform.lshc.service.IndexService;
import com.funi.platform.lshc.vo.census.IndexNoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sam on 2019/6/25.3:31 PM
 */
@Controller
@RequestMapping("/index/")
public class IndexController extends BaseController {
    @Autowired
    private IndexService indexService;

    /**
     * 分页查询文章列表
     * @param noticeQuery
     * @return
     */
    @RequestMapping("getIndexNoticeList")
    @ResponseBody
    public List<IndexNoticeVo> getIndexNoticeList(@RequestBody NoticeQuery noticeQuery) {
        return indexService.findIndexNoticeList(noticeQuery);
    }

    /**
     * 根据文章ID查询文章详情
     * @param id
     * @return
     */
    @RequestMapping("getIndexNoticeDetail")
    @ResponseBody
    public Object getIndexNoticeDetail(String id) {
        try {
            return ResultVo.newResult(indexService.findIndexNoticeDetail(id));
        } catch(Exception e) {
            new ResultVo(false);
            ResultVo resultVo = ResultVo.newResult("查询文章详情失败，原因：" + e.getMessage());
            resultVo.setSuccess(false);
            return resultVo;
        }
    }
}
