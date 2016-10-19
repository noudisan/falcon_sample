package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.core.model.DeliveryVersion;


public class DeliveryVersionTransfer {

    public static DeliveryVersionDto transferForDto(DeliveryVersion deliveryVersion) {
        DeliveryVersionDto deliveryVersionDto = new DeliveryVersionDto();
        deliveryVersionDto.setId(deliveryVersion.getId());
        deliveryVersionDto.setVersionCode(deliveryVersion.getVersionCode());
        deliveryVersionDto.setVersionName(deliveryVersion.getVersionName());
        deliveryVersionDto.setSystemType(deliveryVersion.getSystemType());
        deliveryVersionDto.setUpdateContent(deliveryVersion.getUpdateContent());
        deliveryVersionDto.setIsForceUpdate(deliveryVersion.getIsForceUpdate());
        deliveryVersionDto.setModifyUser(deliveryVersion.getModifyUser());
        deliveryVersionDto.setModifyDt(deliveryVersion.getModifyDt());
        deliveryVersionDto.setCreateUser(deliveryVersion.getCreateUser());
        deliveryVersionDto.setCreateDt(deliveryVersion.getCreateDt());

        return deliveryVersionDto;
    }

    public static DeliveryVersion transferForModel(DeliveryVersionDto deliveryVersionDto){
        DeliveryVersion deliveryVersion = new DeliveryVersion();
        deliveryVersion.setId(deliveryVersionDto.getId());
        deliveryVersion.setVersionCode(deliveryVersionDto.getVersionCode());
        deliveryVersion.setVersionName(deliveryVersionDto.getVersionName());
        deliveryVersion.setSystemType(deliveryVersionDto.getSystemType());
        deliveryVersion.setUpdateContent(deliveryVersionDto.getUpdateContent());
        deliveryVersion.setIsForceUpdate(deliveryVersionDto.getIsForceUpdate());
        deliveryVersion.setModifyUser(deliveryVersionDto.getModifyUser());
        deliveryVersion.setModifyDt(deliveryVersionDto.getModifyDt());
        deliveryVersion.setCreateUser(deliveryVersionDto.getCreateUser());
        deliveryVersion.setCreateDt(deliveryVersionDto.getCreateDt());
        return deliveryVersion;
    }
}
