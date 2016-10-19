package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.core.model.DeliveryDataBuilding;
import com.zhiyi.falcon.core.model.DeliveryTask;
import com.zhiyi.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * startTime 指的是任务在哪天执行
 */
public class DeliveryTaskTransfer {

    public static DeliveryTaskDto transForToDto(DeliveryTask deliveryTask){
        DeliveryTaskDto deliveryTaskDto = new DeliveryTaskDto();
        deliveryTaskDto.setId(deliveryTask.getId());
        deliveryTaskDto.setCode(deliveryTask.getCode());
        deliveryTaskDto.setDriver(deliveryTask.getDriver());
        deliveryTaskDto.setDriverPhoneNum(deliveryTask.getDriverPhoneNum());
        if(deliveryTask.getIsSampling() != null){
            deliveryTaskDto.setIsSampling(TaskSampling.valueOf(deliveryTask.getIsSampling()));
        }
        deliveryTaskDto.setLeader(deliveryTask.getLeader());
        deliveryTaskDto.setLeaderPhoneNum(deliveryTask.getLeaderPhoneNum());
        deliveryTaskDto.setMassAddress(deliveryTask.getMassAddress());
        deliveryTaskDto.setMassTime(DateUtils.format(deliveryTask.getMassTime()));
        deliveryTaskDto.setCreateDt(DateUtils.format(deliveryTask.getCreateDt()));
        deliveryTaskDto.setCreateUser(deliveryTask.getCreateUser());
        deliveryTaskDto.setModifyDt(DateUtils.format(deliveryTask.getModifyDt()));
        if(deliveryTask.getStatus() != null){
            deliveryTaskDto.setStatus(TaskStatus.valueOf(deliveryTask.getStatus()));
        }
        deliveryTaskDto.setModifyUser(deliveryTask.getModifyUser());
        deliveryTaskDto.setSendCount(deliveryTask.getSendCount());
        deliveryTaskDto.setStartTime(DateUtils.format(deliveryTask.getStartTime(),"yyyy-MM-dd"));
        deliveryTaskDto.setTaskDesc(deliveryTask.getTaskDesc());
        deliveryTaskDto.setRegion(deliveryTask.getRegion());
        deliveryTaskDto.setSectionNameStr(deliveryTask.getSectionNames());
        return deliveryTaskDto;
    }

    public static DeliveryTask transForToModel(DeliveryTaskDto deliveryTaskDto){
        DeliveryTask deliveryTask = new DeliveryTask();

        deliveryTask.setId(deliveryTaskDto.getId());
        deliveryTask.setCode(deliveryTaskDto.getCode());
        deliveryTask.setDriver(deliveryTaskDto.getDriver());
        deliveryTask.setDriverPhoneNum(deliveryTaskDto.getDriverPhoneNum());
        if(deliveryTaskDto.getIsSampling() !=null){
            deliveryTask.setIsSampling(deliveryTaskDto.getIsSampling().name());
        }
        deliveryTask.setLeader(deliveryTaskDto.getLeader());
        deliveryTask.setLeaderPhoneNum(deliveryTaskDto.getLeaderPhoneNum());
        deliveryTask.setMassAddress(deliveryTaskDto.getMassAddress());
        deliveryTask.setMassTime(DateUtils.parse(deliveryTaskDto.getMassTime()));
        deliveryTask.setCreateDt(DateUtils.parse(deliveryTaskDto.getCreateDt()));
        deliveryTask.setCreateUser(deliveryTaskDto.getCreateUser());
        deliveryTask.setModifyDt(DateUtils.parse(deliveryTaskDto.getModifyDt()));
        if(deliveryTaskDto.getStatus() != null){
            deliveryTask.setStatus(deliveryTaskDto.getStatus().name());
        }
        deliveryTask.setModifyUser(deliveryTaskDto.getModifyUser());
        deliveryTask.setSendCount(deliveryTaskDto.getSendCount());
        deliveryTask.setStartTime(DateUtils.parse(deliveryTaskDto.getStartTime(),"yyyy-MM-dd"));
        deliveryTask.setTaskDesc(deliveryTaskDto.getTaskDesc());
        deliveryTask.setRegion(deliveryTaskDto.getRegion());
        deliveryTask.setSectionNames(deliveryTaskDto.getSectionNameStr());
        return deliveryTask;
    }
}
