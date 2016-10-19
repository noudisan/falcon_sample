package com.zhiyi.communityunit.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小区单元
 */
@Service("deliveryCommunityUnitProxyService")
public class DeliveryCommunityUnitProxyService implements IDeliveryCommunityUnitService {
    private static Logger logger = Logger.getLogger(DeliveryCommunityUnitProxyService.class);

    @Autowired
    private DeliveryCommunityUnitService deliveryCommunityUnitService;
    
    @Override
    public CommonResult<DeliveryCommunityUnitDto> getById(Long id) {
        CommonResult<DeliveryCommunityUnitDto>  commonResult =new CommonResult<DeliveryCommunityUnitDto>();
        try{
            DeliveryCommunityUnitDto result = deliveryCommunityUnitService.getById(id);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("查询单元信息出错");
            logger.error("查询单元信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Long> saveDeliveryCommunityUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto) {
        CommonResult<Long>  commonResult =new CommonResult<Long>();
        try{
            Long result = deliveryCommunityUnitService.saveDeliveryCommunityUnit(deliveryCommunityUnitDto);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("保存单元信息出错");
            logger.error("保存单元信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Long> updateDeliveryCommunityUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto) {
        CommonResult<Long>  commonResult =new CommonResult<Long>();
        try{
            Long result = deliveryCommunityUnitService.updateDeliveryCommunityUnit(deliveryCommunityUnitDto);
            commonResult.setData(result);
        }catch (Exception e){
            commonResult.doErrorHandle("更新单元信息出错");
            logger.error("更新单元信息出错:",e);
        }
        return commonResult;
    }

    @Override
    public List<DeliveryCommunityUnitDto> search(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto) {
        try{
            List<DeliveryCommunityUnitDto> result = deliveryCommunityUnitService.search(deliveryCommunityUnitSearchDto);
            return result;
        }catch (Exception e){
            logger.error("保存单元信息出错:",e);
        }
        return null;
    }

    @Override
    public Integer count(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto) {
        try{
             Integer count = deliveryCommunityUnitService.count(deliveryCommunityUnitSearchDto);
            return count;
        }catch (Exception e){
            logger.error("查询单元信息出错:",e);
        }
        return null;
    }

    @Override
    public CommonResult<Integer> deleteById(List<Long> idList) {
        CommonResult<Integer> result =new CommonResult<>();
        try{
            Integer count = deliveryCommunityUnitService.deleteById(idList);
            result.setData(count);
        }catch (Exception e){
            result.doErrorHandle("删除单元失败");
            logger.error("查询单元信息出错:",e);
        }
        return result;
    }


}
