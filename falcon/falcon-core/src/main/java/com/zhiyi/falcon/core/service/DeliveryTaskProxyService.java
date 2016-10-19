package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSearchDto;
import com.zhiyi.falcon.api.service.IDeliveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务管理
 */
@Service
public class DeliveryTaskProxyService implements IDeliveryTaskService {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DeliveryTaskProxyService.class);
    @Autowired
    DeliveryTaskService deliveryTaskService;

    @Override
    public CommonResult<Integer> save(DeliveryTaskDto deliveryTaskDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = deliveryTaskService.save(deliveryTaskDto);
            commonResult.setData(result);
        } catch (Exception e) {
            commonResult.doErrorHandle("保存任务失败");
            logger.error("message", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> delete(int taskId) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = deliveryTaskService.delete(taskId);
            commonResult.setData(result);
        } catch (Exception e) {
            commonResult.doErrorHandle("删除任务失败");
            logger.error("message", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateStatus(DeliveryTaskDto taskDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = deliveryTaskService.updateStatus(taskDto);
            commonResult.setData(result);
        } catch (Exception e) {
            commonResult.doErrorHandle("更新任务状态失败");
            logger.error("updateStatus", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> update(DeliveryTaskDto deliveryTaskDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = deliveryTaskService.update(deliveryTaskDto);
            commonResult.setData(result);
        } catch (Exception e) {
            commonResult.doErrorHandle("更新任务失败");
            logger.error("message", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<DeliveryTaskDto> queryTaskData(int taskId) {
        CommonResult<DeliveryTaskDto> commonResult = new CommonResult<>();
        try {
            DeliveryTaskDto deliveryTaskDto = deliveryTaskService.queryOneTask(taskId);
            commonResult.setData(deliveryTaskDto);
        } catch (Exception e) {
            commonResult.doErrorHandle("查询任务失败");
            logger.error("queryTaskData", e);
        }
        return commonResult;
    }


    @Override
    public List<DeliveryTaskDto> search(DeliveryTaskSearchDto deliveryTaskSearchDto) {
        try {
            List<DeliveryTaskDto> list = deliveryTaskService.search(deliveryTaskSearchDto);
            return list;
        } catch (Exception e) {
            logger.error("search", e);
        }
        return null;
    }

    @Override
    public Integer count(DeliveryTaskSearchDto deliveryTaskSearchDto) {
        try {
            return deliveryTaskService.count(deliveryTaskSearchDto);
        } catch (Exception e) {
            logger.error("count", e);
        }
        return 0;
    }

}
