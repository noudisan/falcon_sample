package com.zhiyi.falcon.gateway.transfer;

import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.gateway.result.*;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2015/7/2.
 */
public class SendEmployeeTransfer {

    /**
     * 登陆返回结果
     *
     * @param baseEmployeeDto
     * @return
     */
    public static LoginUserResult transToLoginResult(BaseEmployeeDto baseEmployeeDto) {
        LoginUserResult loginUserResult = new LoginUserResult();
        loginUserResult.setId(baseEmployeeDto.getId());
        loginUserResult.setCity(baseEmployeeDto.getCity());
        if (StringUtils.isNotEmpty(baseEmployeeDto.getDeviceId())) {
            loginUserResult.setIsFirstLogin(1);
        } else {
            loginUserResult.setIsFirstLogin(0);
        }

        return loginUserResult;
    }

    /**
     * 用户基本信息返回结果
     *
     * @param baseEmployeeDto
     * @return
     */
    public static UserBaseInfoResult transToBaseInfoResult(BaseEmployeeDto baseEmployeeDto) {
        UserBaseInfoResult userBaseInfoResult = new UserBaseInfoResult();
        userBaseInfoResult.setUserName(baseEmployeeDto.getUserName());
        userBaseInfoResult.setImageUrl(baseEmployeeDto.getHeadFile());
        userBaseInfoResult.setBalance("0");

        return userBaseInfoResult;
    }

    /**
     * 用户详情返回结果
     *
     * @param baseEmployeeDto
     * @return
     */
    public static UserDetailInfoResult transToDetailInfoResult(BaseEmployeeDto baseEmployeeDto) {
        UserDetailInfoResult userDetailInfoResult = new UserDetailInfoResult();
        UserDetailBaseInfoResult userBaseInfoResult = new UserDetailBaseInfoResult();
        UserDetailBankInfoResult userBankInfoResult = new UserDetailBankInfoResult();

        userBaseInfoResult.setImageUrl(baseEmployeeDto.getHeadFile());
        userBaseInfoResult.setName(baseEmployeeDto.getUserName());
        userBaseInfoResult.setGender(baseEmployeeDto.getSex());
        userBaseInfoResult.setProfession(baseEmployeeDto.getCareer());
        userBaseInfoResult.setPhone(numberFormat(baseEmployeeDto, "PHONE"));
        userBankInfoResult.setIdNumber(numberFormat(baseEmployeeDto, "IDNO"));
        userBankInfoResult.setCardHolder(baseEmployeeDto.getCardHolder());
        userBankInfoResult.setBankName(baseEmployeeDto.getBankName());
        userBankInfoResult.setCardNo(numberFormat(baseEmployeeDto, "BANKNO"));

        userDetailInfoResult.setUserInfo(userBaseInfoResult);
        userDetailInfoResult.setUserBankInfo(userBankInfoResult);

        return userDetailInfoResult;
    }

    public static String numberFormat(BaseEmployeeDto baseEmployeeDto, String flag) {
        String newNum = "";
        if ("PHONE".equals(flag) && StringUtils.isNotBlank(baseEmployeeDto.getPhone())
                && baseEmployeeDto.getPhone().length() == 11) {
            newNum = baseEmployeeDto.getPhone().substring(0, 3) + "****" + baseEmployeeDto.getPhone().substring(7);
            return newNum;
        }

        if ("IDNO".equals(flag) && StringUtils.isNotBlank(baseEmployeeDto.getIdNo())
                && (baseEmployeeDto.getIdNo().length() == 18 || baseEmployeeDto.getIdNo().length() == 15)) {
            String IdNumber = baseEmployeeDto.getIdNo();
            newNum = IdNumber.substring(0, 3) + "***********" + IdNumber.substring(IdNumber.length() - 4);
            return newNum;
        }

        if ("BANKNO".equals(flag) && StringUtils.isNotBlank(baseEmployeeDto.getBankNo())
                && baseEmployeeDto.getBankNo().length() > 4) {
            String bankNo = baseEmployeeDto.getBankNo();
            newNum = bankNo.substring(0, 4) + "_****_****_" + bankNo.substring(bankNo.length() - 4);
            return newNum;
        }
        return newNum;
    }


}
