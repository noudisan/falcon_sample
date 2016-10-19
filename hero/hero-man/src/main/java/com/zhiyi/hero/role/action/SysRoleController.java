package com.zhiyi.hero.role.action;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.ResponseResult;
import com.zhiyi.hero.common.BaseController;
import com.zhiyi.hero.common.Result;
import com.zhiyi.hero.platform.api.IPlatformService;
import com.zhiyi.hero.platform.dto.PlatformSearchDto;
import com.zhiyi.hero.role.action.form.SysRoleAddForm;
import com.zhiyi.hero.role.action.form.SysRoleUpdateForm;
import com.zhiyi.hero.role.api.ISysRoleService;
import com.zhiyi.hero.role.dto.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;


    @Autowired
    private IPlatformService platformService;

    /**
     * 角色管理首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""})
    @RequiresPermissions("sysRole:search")
    public String index(ModelMap model) {
        model.put("platforms", platformService.search((PlatformSearchDto)
                new PlatformSearchDto().disablePaging()).getResults());
        return "sysRole";
    }

    /**
     * @param searchDto
     * @param dtReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermissions("sysRole:search")
    public DtPagerResponse<SysRoleDto> search(SysRoleSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<SysRoleDto, Integer> resultDto = roleService.search(searchDto);
        List<SysRoleDto> resultList = resultDto.getResults();
        int totalSize = resultDto.getMessage();
        return ResponseResult.value(resultList, totalSize, dtReq);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public SysRoleDto get(Long id) {
        return roleService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("sysRole:add")
    public Result add(SysRoleAddForm addForm) {
        SysRoleDto roleDto = addForm.toDto(getLoginUserName());
        roleService.save(roleDto);
        return ADD_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("sysRole:update")
    public Result update(SysRoleUpdateForm updateForm) {
        SysRoleDto roleDto = updateForm.toDto(getLoginUserName());
        roleService.update(roleDto);
        return UPD_SUCCESS;
    }


    @ResponseBody
    @RequestMapping(value = "/searchResources", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermissions("sysRole:assignResources")
    public List<SysRoleResourcesDto> searchResources(SysRoleResourcesSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<SysRoleResourcesDto, Integer> resultDto = roleService.searchResources(searchDto);
        return resultDto.getResults();
    }


    @ResponseBody
    @RequestMapping(value = "/assignResources", method = RequestMethod.POST)
    @RequiresPermissions("sysRole:assignResources")
    public Result assignRole(Long roleId, Long platformId, String resourcesIds) {
        SysRoleResourcesAssignDto assignDto = new SysRoleResourcesAssignDto(roleId, platformId, resourcesIds);
        assignDto.setCreateUser(this.getLoginUserName());
        assignDto.setModifyUser(this.getLoginUserName());
        roleService.assignResources(assignDto);
        return showSuccess("分配成功");
    }

}
