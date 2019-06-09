package com.funi.platform.lshc.controller;

import com.funi.platform.lshc.dto.ComboboxDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 3
 */
@RequestMapping("/service")
@Controller
public class ServiceController {

    @ResponseBody
    @RequestMapping(value="/manager")
    public List<ComboboxDto> findManager(){
        return null;
    }
}
