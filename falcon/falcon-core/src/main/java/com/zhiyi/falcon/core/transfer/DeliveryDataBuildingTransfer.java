package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.enumType.DeliveryType;
import com.zhiyi.falcon.core.model.DeliveryDataBuilding;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnit;
import com.zhiyi.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lirenguan on 6/24/15.
 */
public class  DeliveryDataBuildingTransfer {

    public static DeliveryDataBuildingDto transForToDto(DeliveryDataBuilding deliveryDataBuilding){
        DeliveryDataBuildingDto deliveryDataBuildingDto = new DeliveryDataBuildingDto();

        deliveryDataBuildingDto.setId(deliveryDataBuilding.getId());
        deliveryDataBuildingDto.setBuildId(deliveryDataBuilding.getBuildId());
        deliveryDataBuildingDto.setCommunityId(deliveryDataBuilding.getCommunityId());
        deliveryDataBuildingDto.setDeliveryEmployeeId(deliveryDataBuilding.getDeliveryEmployeeId());
        deliveryDataBuildingDto.setDeliveryTaskId(deliveryDataBuilding.getDeliveryTaskId());
        deliveryDataBuildingDto.setDeliveryNum(deliveryDataBuilding.getDeliveryNum());

        if(deliveryDataBuilding.getDeliveryStatus() != null){
            deliveryDataBuildingDto.setDeliveryStatus(DeliveryStatus.valueOf(deliveryDataBuilding.getDeliveryStatus()));
        }

        deliveryDataBuildingDto.setRemark(deliveryDataBuilding.getRemark());
        deliveryDataBuildingDto.setDeliveryDt(dateToString(deliveryDataBuilding.getDeliveryDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataBuildingDto.setBeginDt(dateToString(deliveryDataBuilding.getBeginDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataBuildingDto.setEndDt(dateToString(deliveryDataBuilding.getEndDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataBuildingDto.setName(deliveryDataBuilding.getBuildingName());
        deliveryDataBuildingDto.setCommunityName(deliveryDataBuilding.getCommunityName());
        deliveryDataBuildingDto.setLatitude(deliveryDataBuilding.getLatitude());
        deliveryDataBuildingDto.setLongitude(deliveryDataBuilding.getLongitude());
        deliveryDataBuildingDto.setDeliveryCity(deliveryDataBuilding.getDeliveryCity());
        return deliveryDataBuildingDto;
    }

    public static DeliveryDataBuilding transForToModel(DeliveryDataBuildingDto deliveryDataBuildingDto){
        DeliveryDataBuilding deliveryDataBuilding = new DeliveryDataBuilding();

        deliveryDataBuilding.setId(deliveryDataBuildingDto.getId());
        deliveryDataBuilding.setBuildId(deliveryDataBuildingDto.getBuildId());
        deliveryDataBuilding.setDeliveryTaskId(deliveryDataBuildingDto.getDeliveryTaskId());
        deliveryDataBuilding.setCommunityId(deliveryDataBuildingDto.getCommunityId());
        deliveryDataBuilding.setDeliveryEmployeeId(deliveryDataBuildingDto.getDeliveryEmployeeId());
        deliveryDataBuilding.setDeliveryNum(deliveryDataBuildingDto.getDeliveryNum());

        if(deliveryDataBuildingDto.getDeliveryStatus() != null){
            deliveryDataBuilding.setDeliveryStatus(deliveryDataBuildingDto.getDeliveryStatus().name());
        }

        deliveryDataBuilding.setRemark(deliveryDataBuildingDto.getRemark());
        deliveryDataBuilding.setDeliveryDt(DateUtils.parse(deliveryDataBuildingDto.getDeliveryDt(), "yyyy-MM-dd HH:mm:ss"));
        deliveryDataBuilding.setBeginDt(stringToDate(deliveryDataBuildingDto.getBeginDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataBuilding.setEndDt(stringToDate(deliveryDataBuildingDto.getEndDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataBuilding.setBuildingName(deliveryDataBuildingDto.getName());
        deliveryDataBuilding.setCommunityName(deliveryDataBuildingDto.getCommunityName());
        deliveryDataBuilding.setLongitude(deliveryDataBuildingDto.getLongitude());
        deliveryDataBuilding.setLatitude(deliveryDataBuildingDto.getLatitude());
        return deliveryDataBuilding;
    }

    public static boolean checkNullStr(String str) {
        if (null == str || "undefined".equalsIgnoreCase(str)|| "null".equalsIgnoreCase(str) || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String dateToString(Date date, String formatString) {
        if (checkNullStr(formatString)) {
            formatString = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat dd = new SimpleDateFormat(formatString);
        String str = "";
        if (null != date) {
            str = dd.format(date);
        }
        return str;
    }

    public static Date stringToDate(String date, String format) {
        if (checkNullStr(date)) {
            return null;
        }
        if (checkNullStr(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
