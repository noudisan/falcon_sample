package com.zhiyi.falcon.gateway.result;

import org.apache.commons.collections.CollectionUtils;

import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.enumType.DeliveryResult;

/**
 * 小区单元任务
 */
public class CommunityUnitTaskResult {
    private Integer communityUnitId;
    private String communityUnitName;
    private String status;
    private String statusStr;

    public Integer getCommunityUnitId() {
        return communityUnitId;
    }

    public void setCommunityUnitId(Integer communityUnitId) {
        this.communityUnitId = communityUnitId;
    }

    public String getCommunityUnitName() {
        return communityUnitName;
    }

    public void setCommunityUnitName(String communityUnitName) {
        this.communityUnitName = communityUnitName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public static CommunityUnitTaskResult toResult(DeliveryDataCommunityUnitDto dto) {
        CommunityUnitTaskResult communityUnitTaskResult =new CommunityUnitTaskResult();
        communityUnitTaskResult.setCommunityUnitId(dto.getCommunityUnitId());
        communityUnitTaskResult.setCommunityUnitName(dto.getCommunityUnitName());        
        
        if(dto.getDeliveryResult() !=null ){
        	if( (DeliveryResult.CANDELIVERY.equals(dto.getDeliveryResult()) && enoughPictures(dto))//可投递、有足够图片
        		|| DeliveryResult.NOTDELIVERY.equals(dto.getDeliveryResult())
        	){	//可投递并有足够图片时，认为完成
            	communityUnitTaskResult.setStatusStr("");
                communityUnitTaskResult.setStatus("");
                return communityUnitTaskResult;
        	}
        }
            
        communityUnitTaskResult.setStatusStr("未完成");
        communityUnitTaskResult.setStatus("未完成");
        return communityUnitTaskResult;
    }
    //加入图片数量的判断
    public static boolean enoughPictures(DeliveryDataCommunityUnitDto dto){
    	if( dto == null ){
    		return false;
    	}
    	if( CollectionUtils.size(dto.getPictures())>=4 ){
    		return true;
    	}
    	return false;
    }
    
}
