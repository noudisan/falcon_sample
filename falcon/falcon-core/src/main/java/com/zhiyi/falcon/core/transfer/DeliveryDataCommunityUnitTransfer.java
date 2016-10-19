package com.zhiyi.falcon.core.transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto.Sequence;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitResultDto;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryType;
import com.zhiyi.falcon.api.enumType.SettleStatus;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnit;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnitPicture;
import com.zhiyi.utils.BeanUtils;
import com.zhiyi.utils.DateUtils;

/**
 * 单元投递信息
 */
public class DeliveryDataCommunityUnitTransfer {

    public static DeliveryDataCommunityUnitDto transForToDto(DeliveryDataCommunityUnit deliveryDataCommunityUnit){
    	if( deliveryDataCommunityUnit == null ){
    		return null;
    	}
        DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = new DeliveryDataCommunityUnitDto();
        deliveryDataCommunityUnitDto.setId(deliveryDataCommunityUnit.getId());
        deliveryDataCommunityUnitDto.setBuildId(deliveryDataCommunityUnit.getBuildId());
        deliveryDataCommunityUnitDto.setCommunityUnitId(deliveryDataCommunityUnit.getCommunityUnitId());
        deliveryDataCommunityUnitDto.setCommunityId(deliveryDataCommunityUnit.getCommunityId());
        deliveryDataCommunityUnitDto.setDeliveryEmployeeId(deliveryDataCommunityUnit.getDeliveryEmployeeId());
        deliveryDataCommunityUnitDto.setDeliveryNum(deliveryDataCommunityUnit.getDeliveryNum());
        deliveryDataCommunityUnitDto.setCommunityName(deliveryDataCommunityUnit.getCommunityName());
        deliveryDataCommunityUnitDto.setBuildingName(deliveryDataCommunityUnit.getBuildingName());
        deliveryDataCommunityUnitDto.setCommunityUnitName(deliveryDataCommunityUnit.getCommunityUnitName());

        if(deliveryDataCommunityUnit.getDeliveryResult() != null){
            deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.valueOf(deliveryDataCommunityUnit.getDeliveryResult()));
        }

        deliveryDataCommunityUnitDto.setDeliveryTaskId(deliveryDataCommunityUnit.getDeliveryTaskId());

        if(deliveryDataCommunityUnit.getDeliveryType() != null){
            deliveryDataCommunityUnitDto.setDeliveryType(DeliveryType.valueOf(deliveryDataCommunityUnit.getDeliveryType()));
        }

        if(deliveryDataCommunityUnit.getSettleStatus() !=null){
            deliveryDataCommunityUnitDto.setSettleStatus(SettleStatus.valueOf(deliveryDataCommunityUnit.getSettleStatus()));
        }

        deliveryDataCommunityUnitDto.setDeliveryDt(DateUtils.format(deliveryDataCommunityUnit.getDeliveryDt()));
        deliveryDataCommunityUnitDto.setRemark(deliveryDataCommunityUnit.getRemark());
        deliveryDataCommunityUnitDto.setUserName(deliveryDataCommunityUnit.getDeliveryEmployeeName());

        if(StringUtils.isNotBlank(deliveryDataCommunityUnit.getTaskSampling()))
        deliveryDataCommunityUnitDto.setTaskSampling(TaskSampling.valueOf(deliveryDataCommunityUnit.getTaskSampling()));
        deliveryDataCommunityUnitDto.setDeliveryCity(deliveryDataCommunityUnit.getDeliveryCity());
       
        List<DeliveryDataCommunityUnitPictureDto> pictures = Lists.newArrayList();
       
//      pictures = BeanUtils.copyList(deliveryDataCommunityUnit.getPictures(),DeliveryDataCommunityUnitPictureDto.class);        
        for( DeliveryDataCommunityUnitPicture p : deliveryDataCommunityUnit.getPictures() ){        	
        	pictures.add(new DeliveryDataCommunityUnitPictureDto(
        			  p.getDeliveryDataCommunityUnitId()
        			, Sequence.get(p.getSequence())
        			, p.getPath()
        			, p.getSaveDate()
        			)
        	);
        }
        
        deliveryDataCommunityUnitDto.setPictures(pictures);
        return deliveryDataCommunityUnitDto;
    }

    public static DeliveryDataCommunityUnit transForToModel(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto){
        DeliveryDataCommunityUnit deliveryDataCommunityUnit = new DeliveryDataCommunityUnit();
        deliveryDataCommunityUnit.setId(deliveryDataCommunityUnitDto.getId());
        deliveryDataCommunityUnit.setBuildId(deliveryDataCommunityUnitDto.getBuildId());
        deliveryDataCommunityUnit.setBuildingName(deliveryDataCommunityUnitDto.getBuildingName());
        deliveryDataCommunityUnit.setCommunityUnitId(deliveryDataCommunityUnitDto.getCommunityUnitId());
        deliveryDataCommunityUnit.setCommunityUnitName(deliveryDataCommunityUnitDto.getCommunityUnitName());
        deliveryDataCommunityUnit.setDeliveryEmployeeId(deliveryDataCommunityUnitDto.getDeliveryEmployeeId());
        deliveryDataCommunityUnit.setDeliveryNum(deliveryDataCommunityUnitDto.getDeliveryNum());
        deliveryDataCommunityUnit.setCommunityId(deliveryDataCommunityUnitDto.getCommunityId());
        deliveryDataCommunityUnit.setCommunityName(deliveryDataCommunityUnitDto.getCommunityName());

        if(deliveryDataCommunityUnitDto.getDeliveryResult() != null){
            deliveryDataCommunityUnit.setDeliveryResult(deliveryDataCommunityUnitDto.getDeliveryResult().name());
        }
        deliveryDataCommunityUnit.setDeliveryTaskId(deliveryDataCommunityUnitDto.getDeliveryTaskId());
        if(deliveryDataCommunityUnitDto.getDeliveryType() != null){
            deliveryDataCommunityUnit.setDeliveryType(deliveryDataCommunityUnitDto.getDeliveryType().name());
        }
        if(deliveryDataCommunityUnitDto.getSettleStatus() !=null){
            deliveryDataCommunityUnit.setSettleStatus(deliveryDataCommunityUnitDto.getSettleStatus().name());
        }
        deliveryDataCommunityUnit.setDeliveryDt(stringToDate(deliveryDataCommunityUnitDto.getDeliveryDt(),"yyyy-MM-dd HH:mm:ss"));
        deliveryDataCommunityUnit.setRemark(deliveryDataCommunityUnitDto.getRemark());
        deliveryDataCommunityUnit.setDeliveryEmployeeName(deliveryDataCommunityUnitDto.getUserName());

        if(deliveryDataCommunityUnitDto.getTaskSampling() != null){
            deliveryDataCommunityUnit.setTaskSampling(deliveryDataCommunityUnitDto.getTaskSampling().name());
        }
        
        if(CollectionUtils.isNotEmpty( deliveryDataCommunityUnitDto.getPictures() )){
        	List<DeliveryDataCommunityUnitPicture> pictures = Lists.newArrayList();
        	for( DeliveryDataCommunityUnitPictureDto p : deliveryDataCommunityUnitDto.getPictures() ){        		
        		DeliveryDataCommunityUnitPicture pic = new DeliveryDataCommunityUnitPicture();
        		pic.setDeliveryDataCommunityUnitId(p.getDeliveryDataCommunityUnitId());
        		pic.setPath(p.getPath());
        		pic.setSaveDate(p.getSaveDate());
        		pic.setSequence(p.getSequence());
        		pictures.add(pic);
        	}
        	deliveryDataCommunityUnit.setPictures(pictures);
        }
        
        return deliveryDataCommunityUnit;
    }


    /**
     * 转换为Model集合
     * @param deliveryDataCommunityUnitDtoList
     * @return
     */
    public static List<DeliveryDataCommunityUnit> transForToModelList(List<DeliveryDataCommunityUnitDto> deliveryDataCommunityUnitDtoList){
        List<DeliveryDataCommunityUnit> deliveryDataCommunityUnitList = new ArrayList<DeliveryDataCommunityUnit>();
        for( DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto : deliveryDataCommunityUnitDtoList){
            deliveryDataCommunityUnitList.add(DeliveryDataCommunityUnitTransfer.transForToModel(deliveryDataCommunityUnitDto));
        }
        return deliveryDataCommunityUnitList;
    }

    /**
     * 转换为DTO集合
     * @param deliveryDataCommunityUnitList
     * @return
     */
    public static List<DeliveryDataCommunityUnitDto> transForToDtoList(List<DeliveryDataCommunityUnit> deliveryDataCommunityUnitList){
        List<DeliveryDataCommunityUnitDto> deliveryDataCommunityUnitDtoList = new ArrayList<DeliveryDataCommunityUnitDto>();
        for( DeliveryDataCommunityUnit deliveryDataCommunityUnit : deliveryDataCommunityUnitList){
            deliveryDataCommunityUnitDtoList.add(DeliveryDataCommunityUnitTransfer.transForToDto(deliveryDataCommunityUnit));
        }
        return deliveryDataCommunityUnitDtoList;
    }


    public static void main(String[] args){
        DeliveryType deliveryType =DeliveryType.valueOf("IN_DELIVERY");
        System.out.print(deliveryType.getStatus());
    }

    public static List<DeliveryDataCommunityUnitDto> transForToResultDtoList(List<DeliveryDataCommunityUnitResultDto> deliveryDataCommunityUnitResultDtoList) {
        List<DeliveryDataCommunityUnitDto> deliveryDataCommunityUnitDtoList = new ArrayList<DeliveryDataCommunityUnitDto>();
        for( DeliveryDataCommunityUnitResultDto deliveryDataCommunityUnitResultDto : deliveryDataCommunityUnitResultDtoList){
            deliveryDataCommunityUnitDtoList.add(DeliveryDataCommunityUnitTransfer.transForToResultDto(deliveryDataCommunityUnitResultDto));
        }
        return deliveryDataCommunityUnitDtoList;
    }

    private static DeliveryDataCommunityUnitDto transForToResultDto(DeliveryDataCommunityUnitResultDto deliveryDataCommunityUnitResultDto) {
        DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = new DeliveryDataCommunityUnitDto();
        deliveryDataCommunityUnitDto.setId(deliveryDataCommunityUnitResultDto.getId());
        deliveryDataCommunityUnitDto.setBuildId(deliveryDataCommunityUnitResultDto.getBuildId());
        deliveryDataCommunityUnitDto.setCommunityUnitId(deliveryDataCommunityUnitResultDto.getCommunityUnitId());
        deliveryDataCommunityUnitDto.setCommunityId(deliveryDataCommunityUnitResultDto.getCommunityId());
        deliveryDataCommunityUnitDto.setDeliveryEmployeeId(deliveryDataCommunityUnitResultDto.getDeliveryEmployeeId());
        deliveryDataCommunityUnitDto.setDeliveryNum(deliveryDataCommunityUnitResultDto.getDeliveryNum());
        deliveryDataCommunityUnitDto.setCommunityName(deliveryDataCommunityUnitResultDto.getCommunityName());
        deliveryDataCommunityUnitDto.setBuildingName(deliveryDataCommunityUnitResultDto.getBuildingName());
        deliveryDataCommunityUnitDto.setCommunityUnitName(deliveryDataCommunityUnitResultDto.getCommunityUnitName());
        if(deliveryDataCommunityUnitResultDto.getDeliveryResult() != null){
            deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.valueOf(deliveryDataCommunityUnitResultDto.getDeliveryResult()));
        }
        deliveryDataCommunityUnitDto.setDeliveryTaskId(deliveryDataCommunityUnitResultDto.getDeliveryTaskId());
        if(deliveryDataCommunityUnitResultDto.getDeliveryType() != null){
            deliveryDataCommunityUnitDto.setDeliveryType(DeliveryType.valueOf(deliveryDataCommunityUnitResultDto.getDeliveryType()));
        }
        if(deliveryDataCommunityUnitResultDto.getSettleStatus() !=null){
            deliveryDataCommunityUnitDto.setSettleStatus(SettleStatus.valueOf(deliveryDataCommunityUnitResultDto.getSettleStatus()));
        }
        deliveryDataCommunityUnitDto.setDeliveryDt(DateUtils.format(deliveryDataCommunityUnitResultDto.getDeliveryDt()));
        deliveryDataCommunityUnitDto.setRemark(deliveryDataCommunityUnitResultDto.getRemark());
        deliveryDataCommunityUnitDto.setCity(deliveryDataCommunityUnitResultDto.getCity());
        deliveryDataCommunityUnitDto.setArea(deliveryDataCommunityUnitResultDto.getArea());

        if(deliveryDataCommunityUnitResultDto.getTaskSampling() != null){
            deliveryDataCommunityUnitDto.setTaskSampling(TaskSampling.valueOf(deliveryDataCommunityUnitResultDto.getTaskSampling()));
        }

        return deliveryDataCommunityUnitDto;
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
