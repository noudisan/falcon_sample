package com.zhiyi.falcon.gateway.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.api.service.ISendEmployeeService;
import com.zhiyi.falcon.gateway.result.*;
import com.zhiyi.falcon.gateway.transfer.SendEmployeeTransfer;
import com.zhiyi.falcon.gateway.utils.Utils;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * app派送员工
 */
@Controller
@RequestMapping(value = "/")
public class SendEmployeeController {
    private Logger logger = LoggerFactory.getLogger(SendEmployeeController.class);

    @Value("${user.image.save.path}")
    private String imageSavePath;

    @Value("${user.image.access.url}")
    private String imageHeadHttpUrl;

    @Value("${falcon.app.download.url}")
    private String appDownloadUrl;


    @Autowired
    @Qualifier("sendEmployeeService")
    private ISendEmployeeService iSendEmployeeService;

    /**
     * 用户登录
     *
     * @param userPhone <-- 目前只认手机号
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user/login", produces = "application/json;charsest=UTF-8")
    public CommonResult<LoginUserResult> login(@RequestParam(value = "userName") String userPhone,
                                               @RequestParam(value = "password") String password) {
        CommonResult<LoginUserResult> commonResult = new CommonResult<>();

        CommonResult<BaseEmployeeDto> loginResult = iSendEmployeeService.login(userPhone, password);

        if (loginResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
            BaseEmployeeDto baseEmployeeDto = loginResult.getData();
            commonResult.setData(SendEmployeeTransfer.transToLoginResult(baseEmployeeDto));
        } else {
            commonResult.doErrorHandle(loginResult.getMsg());
        }
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "user/changepassword")
    public CommonResult<Integer> changePassword(@RequestParam(value = "deviceId") String deviceId,
                                                @RequestParam(value = "userId") String userId,
                                                @RequestParam(value = "oldpassword") String oldpassword,
                                                @RequestParam(value = "changedPassword") String changedPassword) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        if (oldpassword.equals(changedPassword)) {
            commonResult.doErrorHandle("新密码和旧密码一样");
            return commonResult;
        }
        CommonResult<BaseEmployeeDto> dtoCommonResult = iSendEmployeeService.getUserInfo(Integer.parseInt(userId));
        if (dtoCommonResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle(dtoCommonResult.getMsg());
            return commonResult;
        }
        BaseEmployeeDto baseEmployeeDto = dtoCommonResult.getData();
        baseEmployeeDto.setPassword(changedPassword);
        baseEmployeeDto.setDeviceId(deviceId);
        baseEmployeeDto.setModifyDt(new Date());
        baseEmployeeDto.setModifyUser(baseEmployeeDto.getUserName());
        CommonResult<Integer> changePasswordResult = iSendEmployeeService.updateUser(baseEmployeeDto);
        if (changePasswordResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle(changePasswordResult.getMsg());
            return commonResult;
        }
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "user/logout", produces = "application/json;charset=UTF-8")
    public CommonResult<Integer> logout(@RequestParam(value = "userId") String userId) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            logger.info("用户登出：" + userId + "\t 时间：" + DateUtils.formatCurDate());
        } catch (Exception e) {
            logger.error("用户登出,logout:", e);
            commonResult.doErrorHandle("用户退出失败");
        }
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "user/info", produces = "application/json;charset=UTF-8")
    public CommonResult<UserBaseInfoResult> getUserInfo(@RequestParam(value = "userId") Integer userId) {
        CommonResult<UserBaseInfoResult> commonResult = new CommonResult<>();

        //基础信息
        CommonResult<BaseEmployeeDto> dtoCommonResult = iSendEmployeeService.getUserInfo(userId);
        if (dtoCommonResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle(dtoCommonResult.getMsg());
            return commonResult;
        }
        BaseEmployeeDto employeeDto = dtoCommonResult.getData();
        if (StringUtils.isNotBlank(employeeDto.getHeadFile())) {
            employeeDto.setHeadFile(imageHeadHttpUrl + employeeDto.getHeadFile());
        }
        UserBaseInfoResult baseInfoResult = SendEmployeeTransfer.transToBaseInfoResult(employeeDto);

        //账户信息
        AccountInfoDto accountInfoDto = iSendEmployeeService.getAccountAmount(userId);
        if (accountInfoDto.getAccountAmount() == null) {
            baseInfoResult.setBalance("0");
        } else {
            baseInfoResult.setBalance(accountInfoDto.getAccountAmount().toString());
        }

        commonResult.setData(baseInfoResult);
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "user/detail", produces = "application/json;charset=UTF-8")
    public CommonResult<UserDetailInfoResult> getUserDetailInfo(@RequestParam(value = "userId") Integer userId) {
        CommonResult<UserDetailInfoResult> commonResult = new CommonResult<>();

        CommonResult<BaseEmployeeDto> employeeResult = iSendEmployeeService.getUserInfo(userId);
        if (employeeResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle(employeeResult.getMsg());
            return commonResult;
        }
        BaseEmployeeDto employeeDto = employeeResult.getData();
        if (StringUtils.isNotBlank(employeeDto.getHeadFile())) {
            employeeDto.setHeadFile(imageHeadHttpUrl + employeeDto.getHeadFile());
        }
        commonResult.setData(SendEmployeeTransfer.transToDetailInfoResult(employeeDto));
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "user/update", produces = "application/json;charset=UTF-8")
    public CommonResult<Integer> updateUser(@RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "imageUrl", required = false) CommonsMultipartFile imageUrl,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "gender", required = false) String gender,
                                            @RequestParam(value = "profession", required = false) String profession,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "idNumber", required = false) String idNumber,
                                            @RequestParam(value = "cardHolder", required = false) String cardHolder,
                                            @RequestParam(value = "bankName", required = false) String bankName,
                                            @RequestParam(value = "cardNo", required = false) String cardNo
    ) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        CommonResult<BaseEmployeeDto> dtoCommonResult = iSendEmployeeService.getUserInfo(userId);

        if (dtoCommonResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle("更新失败,用户不存在");
            return commonResult;
        }
        BaseEmployeeDto employeeDto = dtoCommonResult.getData();
        //手机号唯一性检查
        if (StringUtils.isNotBlank(phone)) {
            if (!Utils.isMobileNO(phone)) {
                commonResult.doErrorHandle("更新失败，请输入11位手机号");
                return commonResult;
            } else {
                CommonResult<BaseEmployeeDto> baseEmployeeDtoResult = iSendEmployeeService.getUserByPhone(phone);
                if (baseEmployeeDtoResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
                    commonResult.doErrorHandle("更新失败！该手机号已被注册！");
                    return commonResult;
                } else {
                    employeeDto.setPhone(phone);
                }
            }
        }

        //保存头像
        if (imageUrl != null) {
            try {
                String imageFileName = userId + DateUtils.getCurTimeNum() + imageUrl.getOriginalFilename();
                String fullPath = imageSavePath + imageFileName;
                imageUrl.transferTo(new File(fullPath));
                employeeDto.setHeadFile(imageFileName);
            } catch (IOException e) {
                commonResult.doErrorHandle("保存头像失败");
                logger.error("保存头像，imageUrl transferTo:", e);
            }
        }

        employeeDto.setSex(gender);//性别
        //身份证号校验
        if (StringUtils.isNotBlank(idNumber)) {
            if (!Utils.isIDNO(idNumber)) {
                commonResult.doErrorHandle("更新失败！请输入正确的身份证号码！");
                return commonResult;
            } else {
                employeeDto.setIdNo(idNumber);//身份证号
            }
        }

        employeeDto.setBankName(bankName);//银行名称
        employeeDto.setCareer(profession);//职业
        //银行卡号校验
        if (StringUtils.isNotBlank(cardNo)) {
            if (cardNo.length() <= 4 || !Utils.isInteger(cardNo)) {
                commonResult.doErrorHandle("更新失败！请输入正确的银行卡号！");
                return commonResult;
            } else {
                employeeDto.setBankNo(cardNo);//银行卡号
            }
        }

        employeeDto.setId(userId); //用户ID
        employeeDto.setModifyDt(new Date());
        employeeDto.setModifyUser(StringUtils.isNotBlank(name) ? name : employeeDto.getUserName());

        CommonResult<Integer> updateUserResult = iSendEmployeeService.updateUser(employeeDto);
        if (updateUserResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle(updateUserResult.getMsg());
            return commonResult;
        }
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "falcon/version", produces = "application/json;charset=UTF-8")
    public CommonResult<DeliveryVersionResult> appVersionUpdate(@RequestParam("deviceId") String deviceId,
                                                                @RequestParam("deviceType") String deviceType,
                                                                @RequestParam("versionName") String versionName) {
        CommonResult<DeliveryVersionResult> commonResult = new CommonResult<>();
        CommonResult<DeliveryVersionDto> versionDtoCommonResult = iSendEmployeeService.getDeliveryVersion(deviceType);
        if (versionDtoCommonResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
            commonResult.doErrorHandle(versionDtoCommonResult.getMsg());
            return commonResult;
        }
        DeliveryVersionDto versionDto = versionDtoCommonResult.getData();
        DeliveryVersionResult versionResult = new DeliveryVersionResult();
        int result = versionName.compareTo(versionDto.getVersionName());
        if (result >= 0) {
            versionResult.setHasNewVersion(0);
            commonResult.setData(versionResult);
            return commonResult;
        } else {
            versionResult.setHasNewVersion(1);
        }
        versionResult.setVersionName(versionDto.getVersionName());
        versionResult.setReleaseNote(versionDto.getUpdateContent());
        versionResult.setIsForceUpdate(versionDto.getIsForceUpdate());
        versionResult.setDownloadUrl(appDownloadUrl);
        commonResult.setData(versionResult);
        return commonResult;
    }

}
