package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionSearchDto;
import com.zhiyi.falcon.api.service.IDeliveryVersionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryVersionProxyService implements IDeliveryVersionService {
    private static Logger logger = Logger.getLogger(DeliveryVersionProxyService.class);

    @Autowired
    DeliveryVersionService deliveryVersionService;

    @Override
    public CommonResult<Integer> saveDeliveryVersion(DeliveryVersionDto deliveryVersionDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            Integer result = deliveryVersionService.saveDeliveryVersion(deliveryVersionDto);
            if (result == 0) {
                commonResult.doErrorHandle("版本保存失败");
                return commonResult;
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("版本保存失败，请稍后再试");
            logger.error("保存版本，saveDeliveryVersion:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> deleteDeliveryVersion(int id) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            Integer result = deliveryVersionService.deleteDeliveryVersion(id);
            if (result == 0) {
                commonResult.doErrorHandle("版本删除失败");
                return commonResult;
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("版本删除失败，请稍后再试");
            logger.error("删除版本，deleteDeliveryVersion:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateDeliveryVersion(DeliveryVersionDto deliveryVersionDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            Integer result = deliveryVersionService.updateDeliveryVersion(deliveryVersionDto);
            if (result == 0) {
                commonResult.doErrorHandle("版本更新失败");
                return commonResult;
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("版本更新失败，请稍后再试");
            logger.error("版本更新，updateDeliveryVersion:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<DeliveryVersionDto> queryOneDeliveryVersion(int versionId) {
        CommonResult<DeliveryVersionDto> commonResult = new CommonResult<>();
        try {
            DeliveryVersionDto versionDto = deliveryVersionService.queryOneDeliveryVersion(versionId);
            if (versionDto == null) {
                commonResult.doErrorHandle("查询失败");
                return commonResult;
            }
            commonResult.setData(versionDto);
        } catch (Exception e) {
            commonResult.doErrorHandle("查询失败，请稍后再试");
            logger.error("版本查询，queryOneDeliveryVersion:", e);
        }
        return commonResult;
    }

    @Override
    public List<DeliveryVersionDto> search(DeliveryVersionSearchDto deliveryVersionDto) {
        try {
            return deliveryVersionService.search(deliveryVersionDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("版本列表查询，search:", e);
        }
        return null;
    }

    @Override
    public Integer count(DeliveryVersionSearchDto deliveryVersionDto) {
        try {
            return deliveryVersionService.count(deliveryVersionDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("版本件数查询，count:", e);
        }
        return null;
    }
}
