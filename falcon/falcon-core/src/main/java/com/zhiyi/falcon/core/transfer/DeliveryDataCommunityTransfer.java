package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.core.model.DeliveryDataCommunity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryDataCommunityTransfer {

    public static DeliveryDataCommunityDto transForToDto(DeliveryDataCommunity deliveryDataCommunity){
        DeliveryDataCommunityDto deliveryDataCommunityDto = new DeliveryDataCommunityDto();

        deliveryDataCommunityDto.setId(deliveryDataCommunity.getId());
        deliveryDataCommunityDto.setSectionId(deliveryDataCommunity.getSectionId());
        deliveryDataCommunityDto.setCommunityId(deliveryDataCommunity.getCommunityId());
        deliveryDataCommunityDto.setDeliveryTaskId(deliveryDataCommunity.getDeliveryTaskId());
        deliveryDataCommunityDto.setDeliveryNum(deliveryDataCommunity.getDeliveryNum());
        deliveryDataCommunityDto.setCommunityName(deliveryDataCommunity.getCommunityName());
        if(deliveryDataCommunity.getDeliveryStatus() != null){
            deliveryDataCommunityDto.setDeliveryStatus(DeliveryStatus.valueOf(deliveryDataCommunity.getDeliveryStatus()));
        }

        deliveryDataCommunityDto.setRemark(deliveryDataCommunity.getRemark());
        deliveryDataCommunityDto.setDeliveryDt(dateToString(deliveryDataCommunity.getDeliveryDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataCommunityDto.setBeginDt(dateToString(deliveryDataCommunity.getBeginDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataCommunityDto.setDeliveryCity(deliveryDataCommunity.getDeliveryCity());
        return deliveryDataCommunityDto;
    }

    public static DeliveryDataCommunity transForToModel(DeliveryDataCommunityDto deliveryDataCommunityDto){
    	if( deliveryDataCommunityDto == null ){
    		return null;
    	}
        DeliveryDataCommunity deliveryDataCommunity = new DeliveryDataCommunity();

        deliveryDataCommunity.setId(deliveryDataCommunityDto.getId());
        deliveryDataCommunity.setSectionId(deliveryDataCommunityDto.getSectionId());
        deliveryDataCommunity.setDeliveryTaskId(deliveryDataCommunityDto.getDeliveryTaskId());
        deliveryDataCommunity.setCommunityId(deliveryDataCommunityDto.getCommunityId());
        deliveryDataCommunity.setDeliveryNum(deliveryDataCommunityDto.getDeliveryNum());
        deliveryDataCommunity.setCommunityName(deliveryDataCommunityDto.getCommunityName());
        if(deliveryDataCommunityDto.getDeliveryStatus() != null){
            deliveryDataCommunity.setDeliveryStatus(deliveryDataCommunityDto.getDeliveryStatus().name());
        }

        deliveryDataCommunity.setRemark(deliveryDataCommunityDto.getRemark());
        deliveryDataCommunity.setDeliveryDt(stringToDate(deliveryDataCommunityDto.getDeliveryDt(), "yyyy-MM-dd HH:mm:ss"));
        deliveryDataCommunity.setBeginDt(stringToDate(deliveryDataCommunityDto.getBeginDt(),"yyyy-MM-dd HH:mm:ss"));



        return deliveryDataCommunity;
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
