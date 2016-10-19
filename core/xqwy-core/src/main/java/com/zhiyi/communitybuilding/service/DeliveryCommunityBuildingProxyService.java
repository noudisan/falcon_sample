package com.zhiyi.communitybuilding.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingSearchDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deliveryCommunityBuildingPorxyService")
public class DeliveryCommunityBuildingProxyService implements IDeliveryCommunityBuildingService {
    private static Logger logger = Logger.getLogger(DeliveryCommunityBuildingProxyService.class);

    @Autowired
    private DeliveryCommunityBuildingService deliveryCommunityBuildingService;

    @Override
    public CommonResult<DeliveryCommunityBuildingDto> getById(Integer id) {
        CommonResult<DeliveryCommunityBuildingDto>  commonResult =new CommonResult<DeliveryCommunityBuildingDto>();
        try{
            DeliveryCommunityBuildingDto result = deliveryCommunityBuildingService.getById(id);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("查询楼栋信息出错,id:"+id);
            logger.error("查询楼栋信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> saveDeliveryCommunityBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto) {
        CommonResult<Integer>  commonResult =new CommonResult<Integer>();
        try{
            Integer result = deliveryCommunityBuildingService.saveDeliveryCommunityBuilding(deliveryCommunityBuildingDto);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("保存楼栋信息出错,id:"+deliveryCommunityBuildingDto);
            logger.error("保存楼栋信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateDeliveryCommunityBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto) {
        CommonResult<Integer>  commonResult =new CommonResult<Integer>();
        try{
            Integer result = deliveryCommunityBuildingService.updateDeliveryCommunityBuilding(deliveryCommunityBuildingDto);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("更新楼栋信息出错,id:"+deliveryCommunityBuildingDto);
            logger.error("更新楼栋信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public List<DeliveryCommunityBuildingDto> search(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto) {
        try{
            List<DeliveryCommunityBuildingDto> result = deliveryCommunityBuildingService.search(deliveryCommunityBuildingSearchDto);
            return result;
        }catch (Exception e){
            logger.error("查询楼栋信息出错:",e);
        }
        return null;
    }

    @Override
    public Integer count(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto) {
        try{
            Integer result = deliveryCommunityBuildingService.count(deliveryCommunityBuildingSearchDto);
            return result;
        }catch (Exception e){
            logger.error("查询楼栋信息出错:",e);
        }
        return null;
    }

    @Override
    public CommonResult<Integer> saveDeliveryCommunityBuildingList(List<DeliveryCommunityBuildingDto> list) {
        CommonResult<Integer> commonResult =new CommonResult<Integer>();
        try{
            Integer result = deliveryCommunityBuildingService.saveDeliveryCommunityBuildingList(list);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("保存信息失败");
            logger.error("查询楼栋信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> deleteById(List<Integer> list) {
        CommonResult<Integer> commonResult =new CommonResult<Integer>();
        try{
            Integer result = deliveryCommunityBuildingService.deleteById(list);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("删除失败");
            logger.error("删除失败:",e);
        }
        return commonResult;
    }
}
