package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.service.IDeliveryDataBuildingService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 楼栋派发信息
 */
@Service
public class DeliveryDataBuildingProxyService implements IDeliveryDataBuildingService{
    private Logger logger = Logger.getLogger(DeliveryDataBuildingProxyService.class);


    @Autowired
    private DeliveryDataBuildingService deliveryDataBuildingService;

    @Override
    public CommonResult<Integer> save(DeliveryDataBuildingDto deliveryDataBuildingDto) {
        CommonResult<Integer> commonResult =  new CommonResult<>();
        try{
            int result = deliveryDataBuildingService.save(deliveryDataBuildingDto);
            commonResult.setData(result);
        }catch(Exception e){
            commonResult.doErrorHandle("保存楼栋派送数据失败，请刷新后重试");
            logger.error("保存楼栋派送数据失败,save:",e);

        }

        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateDeliveryData(DeliveryDataBuildingDto deliveryDataBuildingDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try{
            int result = deliveryDataBuildingService.updateDeliveryData(deliveryDataBuildingDto);

            commonResult.setData(result);
        }catch (Exception e) {
            commonResult.doErrorHandle("更新楼栋派送数据失败，请刷新后重试");
            logger.error("更新楼栋派送数据失败,updateDeliveryData:",e);

        }
        return null;
    }

    @Override
    public CommonResult<DeliveryDataBuildingDto> queryDeliveryData(int id) {
        CommonResult<DeliveryDataBuildingDto> commonResult = new CommonResult<>();
        try{
            DeliveryDataBuildingDto deliveryDataBuildingDto = deliveryDataBuildingService.queryDeliveryData(id);
            commonResult.setData(deliveryDataBuildingDto);
        }catch (Exception e) {
            commonResult.doErrorHandle("查询楼栋派送数据失败，请刷新后重试");
            logger.error("查询楼栋派送数据失败,queryDeliveryData:", e);

        }
        return commonResult;
    }

    @Override
    public List<DeliveryDataBuildingDto> search(DeliveryDataBuildingSearchDto searchDto) {
        try{
            List<DeliveryDataBuildingDto> deliveryDataBuildingDtos=deliveryDataBuildingService.search(searchDto);
            return deliveryDataBuildingDtos;
        }catch(Exception e ){
            logger.error("查询楼栋派送数据失败,search:", e);
        }

        return null;
    }

    @Override
    public Integer count(DeliveryDataBuildingSearchDto searchDto) {
       try{
           Integer total= deliveryDataBuildingService.count(searchDto);
           return total;
       }catch(Exception e){
           logger.error("查询楼栋派送数量失败,count:", e);
       }

        return null;
    }

    @Override
    public CommonResult<String> finishedBuilding(DeliveryDataBuildingDto deliveryDataBuildingDto){
        CommonResult<String> commonResult = new CommonResult<String>();
        try{
            String  result = deliveryDataBuildingService.finishedBuilding(deliveryDataBuildingDto);
            if(StringUtils.isNotBlank(result)){
                commonResult.doErrorHandle(result);
            }
        }catch (Exception e) {
            commonResult.doErrorHandle("完成单元派送出错，请刷新后重试");
            logger.error("完成单元派送出错,finishedBuilding:",e);

        }
        return commonResult;

    }

    @Override
    public CommonResult<List<DeliveryDataBuildingDto>> searchAllCommunityBuildingWithTask(Integer taskId, Integer communityId) {
        CommonResult<List<DeliveryDataBuildingDto>> commonResult=new CommonResult<>();
        try{
            List<DeliveryDataBuildingDto> list = deliveryDataBuildingService.searchAllCommunityWithTask(taskId, communityId);
            commonResult.setData(list);
        }catch(Exception e){
            commonResult.doErrorHandle("查询小区楼栋信息出错,请刷新后重试");
            logger.error("查询小区楼栋信息出错,searchAllCommunityBuildingWithTask:", e);
        }

        return commonResult;
    }

    @Override
    public CommonResult<String> addCommunityBuilding(Integer buildingId,
                                                     String buildingName,
                                                     Integer userId,
                                                     Integer taskId) {
        CommonResult<String> commonResult = new CommonResult<>();
        try {
            String  result  =deliveryDataBuildingService.addCommunityBuilding(buildingId,buildingName,userId,taskId);
            if(StringUtils.isNotBlank(result)){
                commonResult.doErrorHandle(result);
            }
        }catch (Exception e) {
            commonResult.doErrorHandle("添加楼栋失败，请刷新后重试");
            logger.error("添加楼栋失败,addCommunityBuilding:", e);

        }

        return commonResult;
    }

}
