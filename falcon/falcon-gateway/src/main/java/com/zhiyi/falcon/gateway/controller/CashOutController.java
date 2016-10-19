package com.zhiyi.falcon.gateway.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.api.service.ICashOutInfoService;
import com.zhiyi.falcon.gateway.result.CashOutResult;
import com.zhiyi.falcon.gateway.result.WithdrawalResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 提现管理
 */
@Controller
@RequestMapping("/withdrawal")
public class CashOutController {

    private Logger logger = Logger.getLogger(CashOutController.class);

    @Autowired
    private ICashOutInfoService cashOutInfoService;


    /**
     * 提现查询
     * @param deviceId
     * @param version
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public CommonResult<CashOutResult> search(@RequestParam(value = "deviceId", required = false) String deviceId,
                                              @RequestParam(value = "version", required = false) String version,
                                              @RequestParam(value = "userId", required = false) String userId){
        CommonResult<CashOutResult> commonResult = new CommonResult<CashOutResult>();
        CashOutResult cashOutResult = new CashOutResult();

        CashOutInfoDto cashOutInfoDto = cashOutInfoService.balanceQuery(deviceId, version, userId);
        if( cashOutInfoDto == null){
            commonResult.doErrorHandle("提现查询失败");
            return commonResult;
        }

        cashOutResult.setToDate(cashOutInfoDto.getToDate());
        cashOutResult.setUpAmount(cashOutInfoDto.getUpAmount());
        cashOutResult.setTailNumber(cashOutInfoDto.getTailNumber());
        cashOutResult.setBankName(cashOutInfoDto.getBankName());
        commonResult.setData(cashOutResult);
        return commonResult;
    }


    /**
     * 用户提现
     * @param deviceId
     * @param version
     * @param userId
     * @param amount
     * @return
     */
    @ResponseBody
    @RequestMapping( produces = "application/json;charset=UTF-8")
    public CommonResult<WithdrawalResult> withdrawal(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                         @RequestParam(value = "version", required = false) String version,
                                                         @RequestParam(value = "userId", required = false) String userId,
                                                         @RequestParam(value = "amount", required = false) String amount){
        CommonResult<WithdrawalResult> commonResult = new CommonResult<WithdrawalResult>();
        Double amountCash = Double.valueOf(amount);
        if(amountCash <100){
            commonResult.doErrorHandle("提现金额不能小于100");
            return commonResult;
        }
        WithdrawalResult withdrawalResult = new WithdrawalResult();
        String result = cashOutInfoService.cashOut(deviceId, version, userId, amount);
        if(!"提现成功".equals(result)){
            commonResult.doErrorHandle(result);
            return commonResult;
        }
        withdrawalResult.setSuccess(true);
        commonResult.setMsg("提现成功");
        commonResult.setData(withdrawalResult);
        return commonResult;

        /*
        try{
            Double amount_d = Double.valueOf(amount);
            if( amount_d < 100){
                withdrawalResult.setSuccess(false);
                commonResult.setData(withdrawalResult);
                commonResult.setCode(CommonResult.RESULT_STATUS_FAILURE);
                commonResult.setMsg("提现金额不能小于100");
                return commonResult;
            }

            String reg = "^[1-9]\\d*|0$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(amount);
            if( !matcher.matches() ){
               commonResult.doErrorHandle("必须为整数");
                return commonResult;
            }


        }catch (Exception e){
            logger.error(e.getMessage());
            withdrawalResult.setSuccess(false);
            commonResult.doErrorHandle("提现失败");
        }
        commonResult.setData(withdrawalResult);*/


    }


    public static void main(String[] args) {

       try {
//           Integer.parseInt("1.1");
           double amt = 2.1;
           if( amt < 2){
               System.out.println("小于100");
           }else{
               System.out.println("大于100");
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
