package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.api.service.ICashOutInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提现信息
 * Created by Adminstrator on 2015/6/24.
 */
@Service("cashOutInfoProxyService")
public class CashOutInfoProxyService implements ICashOutInfoService {

    Logger logger = Logger.getLogger(CashOutInfoProxyService.class);

    @Autowired
    private CashOutInfoService cashOutInfoService;

    /**
     * 保存提现信息
     * @param cashOutInfoDto
     * @return
     */
    @Override
    public CommonResult<Integer> save(CashOutInfoDto cashOutInfoDto) {
        logger.info("保存提现信息");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try{
            Integer result = cashOutInfoService.save(cashOutInfoDto);

            commonResult.setData(result);
        logger.info("保存提现信息成功");
        }catch (Exception e){
            logger.error("保存提现信息失败,methodName:" , e);
            commonResult.doErrorHandle("保存提现信息失败");
        }
        return commonResult;
    }



    /**
     * 修改
     * @param cashOutInfoDtoList
     * @return
     */
    @Override
    public CommonResult<Integer> update(List<CashOutInfoDto> cashOutInfoDtoList) {
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = 0;
            result = cashOutInfoService.update(cashOutInfoDtoList);
            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("修改提现信息失败,methodName:" , e);
            commonResult.doErrorHandle("修改提现信息失败");
        }
        return commonResult;
    }

        /**
     * 查询数据的总条数
     * @param cashOutInfoDto
     * @return
     */
    public Integer count(CashOutInfoDto cashOutInfoDto){
        int count = 0;
        try{
            count = cashOutInfoService.count(cashOutInfoDto);
        }catch (Exception e){
            logger.error("查询提现信息总条数失败,methodName:" , e);
        }
        return count;
    }

    @Override
    public CashOutInfoDto detail(int id) {
        try{
            return cashOutInfoService.detail(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询提现信息
     * @param cashOutInfoDto
     * @return
     */
    public List<CashOutInfoDto> search(CashOutInfoDto cashOutInfoDto){
        List<CashOutInfoDto> cashOutInfoDtoList = null;
        try{
            cashOutInfoDtoList = cashOutInfoService.search(cashOutInfoDto);
        }catch (Exception e){
            logger.error("查询提现信息失败,methodName:" , e);
        }
        return cashOutInfoDtoList;
    }

    /**
     * 用户提现
     * @param deviceId
     * @param version
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public String cashOut(String deviceId, String version, String userId, String amount) {
        String result = "";
        try{
            logger.info("用户编号："+userId + ", 提现："+amount + ", 设备号："+deviceId);
            result = cashOutInfoService.cashOut(deviceId, version, userId, amount);
        }catch (Exception e){
            logger.error("用户提现失败,methodName:" , e);
        }
        return result;
    }

    /**
     * 用户账户账户余额，卡号信息查询
     * @param deviceId
     * @param version
     * @param userId
     * @return
     */
    @Override
    public CashOutInfoDto balanceQuery(String deviceId, String version, String userId) {
        CashOutInfoDto cashOutInfoDto = null;
        try{
            cashOutInfoDto = cashOutInfoService.balanceQuery(deviceId, version, userId);
        }catch (Exception e){
            logger.error("查询用户余额信息失败，methodName:", e);
        }
        return cashOutInfoDto;
    }
}
