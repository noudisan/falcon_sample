package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.dto.DeliveryStepsSearchDto;
import com.zhiyi.falcon.api.service.IDeliveryStepsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryStepsProxyService implements IDeliveryStepsService {
    private static Logger logger = Logger.getLogger(DeliveryStepsProxyService.class);
    @Autowired
    DeliveryStepsService deliveryStepsService;


    @Override
    public List<DeliveryStepsDto> search(DeliveryStepsSearchDto deliveryStepsSearchDto) {
        try {
            return deliveryStepsService.search(deliveryStepsSearchDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("列表查询，search:", e);
        }
        return null;
    }

    @Override
    public Integer count(DeliveryStepsSearchDto deliveryStepsSearchDto) {
        try {
            return deliveryStepsService.count(deliveryStepsSearchDto);
        } catch (Exception e) {
            logger.error("件数查询，count:", e);
        }
        return null;
    }

    @Override
    public CommonResult<Integer> saveSteps(DeliveryStepsDto stepsDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            Integer result = deliveryStepsService.saveSteps(stepsDto);
            if (result == 0) {
                commonResult.doErrorHandle("保存步数失败");
                return commonResult;
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("保存步数失败，请刷新后重试");
            logger.error("保存步数，saveSteps:", e);
        }

        return commonResult;
    }

    @Override
    public CommonResult<Integer> deleteByPrimaryKey(Integer id) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            Integer result = deliveryStepsService.deleteByPrimaryKey(id);
            if (result == 0) {
                commonResult.doErrorHandle("删除步数信息失败");
                return commonResult;
            }
        } catch (Exception e) {
            commonResult.setMsg("删除步数失败,请刷新后重试");
            logger.error("删除步数，deleteByPrimaryKey:", e);
        }

        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateSteps(DeliveryStepsDto stepsDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {

            Integer result = deliveryStepsService.updateSteps(stepsDto);
            if (result == 0) {
                commonResult.doErrorHandle("更新步数失败");
                return commonResult;
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("更新步数信息失败,请刷新后重试");
            logger.error("更新步数，updateSteps:", e);
        }

        return commonResult;
    }

    @Override
    public CommonResult<DeliveryStepsDto> querySteps(Integer id) {
        CommonResult<DeliveryStepsDto> commonResult = new CommonResult<>();
        try {

            DeliveryStepsDto deliveryStepsDto = deliveryStepsService.querySteps(id);
            if (deliveryStepsDto == null) {
                commonResult.doErrorHandle("查询步数失败");
                return commonResult;
            }
            commonResult.setData(deliveryStepsDto);
        } catch (Exception e) {
            commonResult.doErrorHandle("查询步数失败,请刷新后重试");
            logger.error("查询步数，querySteps:", e);
        }

        return commonResult;
    }
}
