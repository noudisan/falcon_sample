package com.zhiyi.hero.platform.action;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.ResponseResult;
import com.zhiyi.hero.common.BaseController;
import com.zhiyi.hero.common.Result;
import com.zhiyi.hero.platform.action.form.PlatformAddForm;
import com.zhiyi.hero.platform.action.form.PlatformUpdateForm;
import com.zhiyi.hero.platform.api.IPlatformService;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.hero.platform.dto.PlatformSearchDto;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/platform")
public class PlatformController extends BaseController {

    @Autowired
    private IPlatformService platformService;

    /**
     * 平台管理首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""})
    @RequiresPermissions("platform:search")
    public String index() {
        return "platform";
    }

    /**
     * 平台查询
     *
     * @param searchDto
     * @param dtReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @RequiresPermissions("platform:search")
    public DtPagerResponse<PlatformDto> search(PlatformSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<PlatformDto, Integer> resultDto = platformService.search(searchDto);
        List<PlatformDto> list = resultDto.getResults();
        int totalSize = resultDto.getMessage();
        return ResponseResult.value(list, totalSize, dtReq);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public PlatformDto get(Long id) {
        return platformService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("platform:add")
    public Result add(PlatformAddForm addForm) throws Exception {
    	if(platformService.getByName(addForm.getName())  !=  null){
    		  return showError("已存在该平台名,无法新增");
    	}
        PlatformDto addDto = addForm.toDto(getLoginUserName());
        platformService.save(addDto);
        return ADD_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("platform:update")
    public Result update(PlatformUpdateForm updateForm) {
    	PlatformDto existPlatform = platformService.get(updateForm.getId());
    	String platformName = updateForm.getName();
    	if(!StringUtils.equals(platformName,existPlatform.getName())){
    		if(platformService.getByName(platformName) != null){
    			 return showError("已存在该平台名,无法修改");
    		}
    	}
        PlatformDto updateDto = updateForm.toDto(getLoginUserName());
        platformService.update(updateDto);
        return UPD_SUCCESS;
    }
}
