package com.zhiyi.hero.user.action;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.ResponseResult;
import com.zhiyi.hero.common.BaseController;
import com.zhiyi.hero.common.Result;
import com.zhiyi.hero.user.action.form.SysUserAddForm;
import com.zhiyi.hero.user.action.form.SysUserUpdateForm;
import com.zhiyi.hero.user.action.form.SysUserUpdatePwdForm;
import com.zhiyi.hero.user.api.ISysUserService;
import com.zhiyi.hero.user.dto.*;
import com.zhiyi.utils.crypto.Md5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 系统用户管理首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""})
    @RequiresPermissions("sysUser:search")
    public String index() {
        return "sysUser";
    }

    /**
     * 系统用户查询
     *
     * @param searchDto
     * @param dtReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @RequiresPermissions("sysUser:search")
    public DtPagerResponse<SysUserDto> search(SysUserSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<SysUserDto, Integer> resultDto = sysUserService.search(searchDto);
        List<SysUserDto> resultList = resultDto.getResults();
        int totalSize = resultDto.getMessage();
        return ResponseResult.value(resultList, totalSize, dtReq);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public SysUserDto get(Long id) {
        return sysUserService.get(id);
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("sysUser:add")
    public Result add(SysUserAddForm addForm) {
        if (sysUserService.getByUserName(addForm.getUserName()) != null) {
            return showError("已存在该用户名,无法新增");
        }
        SysUserDto userDto = addForm.toDto(getLoginUserName());
        sysUserService.save(userDto);
        return ADD_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("sysUser:update")
    public Result update(SysUserUpdateForm updateForm) {
        SysUserDto user = sysUserService.get(updateForm.getId());
        String userName = updateForm.getUserName();
        if (!StringUtils.equals(userName, user.getUserName())) {
            if (sysUserService.getByUserName(userName) != null) {
                return showError("已存在该用户名，无法修改");
            }
        }
        SysUserDto userDto = updateForm.toDto(getLoginUserName());
        String password = StringUtils.isBlank(userDto.getPassword()) ? user.getPassword()
                : Md5Utils.encode32(userDto.getPassword());
        userDto.setPassword(password);
        sysUserService.update(userDto);
        return UPD_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Result update(SysUserUpdatePwdForm updatePwdForm) {
        if (!StringUtils.equals(updatePwdForm.getNewPassword(), updatePwdForm.getConfirmNewPassword())) {
            return showError("新密码和确认密码不一致");
        }
        SysUserUpdatePwdDto updatePwdDto = new SysUserUpdatePwdDto(updatePwdForm.getUserName(),
                Md5Utils.encode32(updatePwdForm.getPassword()), Md5Utils.encode32(updatePwdForm.getNewPassword()));
        SysUserUpdatePwdResultDto resultDto = sysUserService.updatePassword(updatePwdDto);
        if (resultDto.getStatus() == SysUserUpdatePwdResultDto.Status.FAIL) {
            return showError(resultDto.getMessage());
        }
        return UPD_SUCCESS;
    }


    @ResponseBody
    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    @RequiresPermissions({"sysUser:lock","sysUser:unlock"})
    public Result setting(Long id, int statusFlag) {
        SysUserDto userDto = sysUserService.get(id);
        userDto.setStatusFlag(statusFlag);
        sysUserService.update(userDto);
        return SET_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/searchRole", method = RequestMethod.POST)
    @RequiresPermissions("sysUser:assignRole")
    public DtPagerResponse<SysUserRoleDto> searchRole(SysUserRoleSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<SysUserRoleDto, Integer> resultDto = sysUserService.searchRole(searchDto);
        List<SysUserRoleDto> resultList = resultDto.getResults();
        int totalSize = resultDto.getMessage();
        return ResponseResult.value(resultList, totalSize, dtReq);
    }


    @ResponseBody
    @RequestMapping(value = "/assignRole", method = RequestMethod.POST)
    @RequiresPermissions("sysUser:assignRole")
    public Result assignRole(Long userId, String roleIds) {
        SysUserRoleAssignDto assignDto = new SysUserRoleAssignDto(userId, roleIds);
        assignDto.setCreateUser(this.getLoginUserName());
        assignDto.setModifyUser(this.getLoginUserName());
        sysUserService.assignRole(assignDto);
        return showSuccess("分配成功");
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @RequiresPermissions("sysUser:assignRole")
    public Result deleteRole(Long userId, String roleIds) {
        SysUserRoleDeleteDto deleteDto = new SysUserRoleDeleteDto(userId, roleIds);
        sysUserService.deleteRole(deleteDto);
        return DEL_SUCCESS;
    }
}
