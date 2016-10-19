package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityService;
import com.zhiyi.falcon.core.dao.BaseCommunityMapper;
import com.zhiyi.falcon.core.model.BaseCommunity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryDataCommunityProxyService implements IDeliveryDataCommunityService{
    private static Logger logger = Logger.getLogger(DeliveryDataCommunityProxyService.class);

    @Autowired
    private DeliveryDataCommunityService deliveryDataCommunityService;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Override
    public CommonResult<Integer> save(DeliveryDataCommunityDto deliveryDataCommunityDto) {
        CommonResult<Integer> commonResult =  new CommonResult<>();
        try{
            BaseCommunity baseCommunity = baseCommunityMapper.selectByPrimaryKey(deliveryDataCommunityDto.getCommunityId());
            if( baseCommunity != null) {
                deliveryDataCommunityDto.setDeliveryCity(baseCommunity.getCity());
            }
            int result = deliveryDataCommunityService.save(deliveryDataCommunityDto);
            commonResult.setData(result);
        }catch(Exception e){
            commonResult.doErrorHandle("保存小区派送数据失败，请刷新后重试");
            logger.error("保存小区派送数据失败,save:", e);
        }

        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateDeliveryData(DeliveryDataCommunityDto deliveryDataCommunityDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try{
            int result = deliveryDataCommunityService.updateDeliveryData(deliveryDataCommunityDto);
            commonResult.setData(result);
        }catch(Exception e){
            commonResult.doErrorHandle("更新小区派送数据失败，请刷新后重试");
            logger.error("更新小区派送数据失败,updateDeliveryData:", e);
        }
        return null;
    }

    @Override
    public CommonResult<Integer> finishedCommunity(DeliveryDataCommunityDto deliveryDataCommunityDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try{
            int result = deliveryDataCommunityService.finishedCommunity(deliveryDataCommunityDto);
            commonResult.setData(result);
        }catch(Exception e){
            commonResult.doErrorHandle("更新小区派送数据失败，请刷新后重试");
            logger.error("更新小区派送数据失败,finishedCommunity:", e);
        }

        return null;
    }


    @Override
    public CommonResult<DeliveryDataCommunityDto> queryDeliveryData(int id) {
        CommonResult<DeliveryDataCommunityDto> commonResult = new CommonResult<>();
        try{
            DeliveryDataCommunityDto deliveryDataCommunityDto = deliveryDataCommunityService.queryDeliveryData(id);
            commonResult.setData(deliveryDataCommunityDto);
        }catch(Exception e){
            commonResult.doErrorHandle("查询小区派送数据失败，请刷新后重试");
            logger.error("查询小区派送数据失败,finishedCommunity:", e);
        }
        return commonResult;
    }

    @Override
    public List<DeliveryDataCommunityDto> search(DeliveryDataCommunitySearchDto searchDto) {
        try{
            return  deliveryDataCommunityService.search(searchDto);
        }catch(Exception e ){
            logger.error("查询小区派送数据失败,search:", e);
        }

        return null;
    }

    @Override
    public Integer count(DeliveryDataCommunitySearchDto searchDto) {
       try{
           return deliveryDataCommunityService.count(searchDto);
       }catch(Exception e){
           logger.error("查询小区派送数据失败,search:", e);
       }

        return null;
    }

    @Override
    public List<DeliveryDataCommunityDto> searchAllCommunityWithTask(DeliveryDataCommunitySearchDto searchDto) {
        try{
            List<DeliveryDataCommunityDto> list = deliveryDataCommunityService.searchAllCommunityWithTask(searchDto);
            return list;
        }catch(Exception e){
            logger.error("查询小区派送数据失败,searchAllCommunityWithTask:", e);
        }

        return null;
    }



}
