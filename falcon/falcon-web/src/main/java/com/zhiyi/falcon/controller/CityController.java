package com.zhiyi.falcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyi.falcon.api.service.ICityService;

/**
 * 城市
 */
@Controller
@RequestMapping("/citys")
public class CityController {

    @Autowired
    private ICityService cityService;
    
    /**
     * 获取所有
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(
		 value = {"/","","/unlocked"}
		,method = RequestMethod.GET
		,produces = "application/json;charset=UTF-8"
    )
    public Object getUnlockedCitys() {
    	return cityService.getUnlockedCitys();
    }
}
