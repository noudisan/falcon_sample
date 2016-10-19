package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;

import com.zhiyi.falcon.api.service.IDeliveryTaskEmployeeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryTaskEmployeeProxyService implements IDeliveryTaskEmployeeService{
    private static Logger logger = Logger.getLogger(DeliveryTaskEmployeeProxyService.class);

    @Autowired
    private DeliveryTaskEmployeeService deliveryTaskEmployeeService;

    @Override
    public List<DeliveryTaskEmployeeDto> queryByUserId(int userId) {
        try{
            List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = deliveryTaskEmployeeService.queryByUserId(userId);
            return deliveryTaskEmployeeDtos;
        }catch(Exception e){
            logger.error("查询派送任务失败,search");
        }
        return null;
    }

    @Override
    public List<DeliveryTaskEmployeeDto> queryBySendTaskId(int sendTaskId) {

        try{
            List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = deliveryTaskEmployeeService.queryBySendTaskId(sendTaskId);
            return deliveryTaskEmployeeDtos;
        }catch(Exception e){
            logger.error("查询派送人员失败,queryBySendTaskId",e);
        }
        return null;
    }

    @Override
    public CommonResult<String> updateTaskUserStatus(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto) {
        CommonResult<String> commonResult =new CommonResult<String>();
        try{
            String result = deliveryTaskEmployeeService.updateTaskUserStatus(deliveryTaskEmployeeDto);
            if(StringUtils.isNotBlank(result)){
                commonResult.doErrorHandle(result);
            }
        }catch(Exception e){
            logger.error("更新用户任务状态失败,updateTaskUserStatus:",e);

        }
        return commonResult;
    }

    @Override
    public List<DeliveryTaskEmployeeDto> search(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto){
        try{
            List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = deliveryTaskEmployeeService.search(deliveryTaskEmployeeDto);
            return deliveryTaskEmployeeDtos;
        }catch(Exception e){
            logger.error("查询派送人员失败,search",e);
        }
        return null;
    }

}
