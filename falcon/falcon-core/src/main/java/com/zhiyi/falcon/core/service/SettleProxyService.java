package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.service.ISettleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 结算
 * Created by Adminstrator on 2015/6/24.
 */
@Service("settleProxyService")
public class SettleProxyService implements ISettleService {

    private Logger logger = Logger.getLogger(SettleProxyService.class);

    @Autowired
    private SettleService settleService;

    @Autowired
    private DeliveryDataCommunityUnitService deliveryDataCommunityUnitService;

    @Autowired
    private SettlePriceService settlePriceService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Override
    public  SettleDto getById(Integer settleId){
        return  settleService.getByid(settleId);
    }

    /**
     * 新增
     * @param settleDto
     * @return
     */
    @Override
    public CommonResult<Integer> save(SettleDto settleDto) {
        logger.info("新增结算信息");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settleService.save(settleDto);

            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("新增结算信息失败, methodName:" , e);
            commonResult.doErrorHandle("新增结算信息失败");
        }
        return commonResult;
    }

    /**
     *  删除
     * @param id
     * @return
     */
    @Override
    public CommonResult<Integer> delete(int id){
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settleService.delete(id);

            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("删除结算信息失败, methodName:" , e);
            commonResult.doErrorHandle("删除结算信息失败");
        }
        return commonResult;
    }

    /**
     * 更新
     * @param settleDto
     * @return
     */
    @Override
    public CommonResult<Integer> update(SettleDto settleDto) {
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settleService.update(settleDto);

            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("更新结算信息失败, methodName:" , e);
            commonResult.doErrorHandle("更新结算信息失败");
        }
        return commonResult;
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public CommonResult<SettleDto> detail(int id) {
        CommonResult<SettleDto> commonResult = new CommonResult<SettleDto>();
        try {
            SettleDto settleDto = settleService.detail(id);

            commonResult.setData(settleDto);
        } catch (Exception e) {
            logger.error("查看结算信息详情失败, methodName:" , e);
            commonResult.doErrorHandle("查看结算信息详情失败");
        }
        return commonResult;

    }

    /**
     * 统计总的数据条数
     * @param settleDto
     * @return
     */
    @Override
    public Integer count(SettleDto settleDto) {
        Integer count = 0;
        try{
            count = settleService.count(settleDto);
        }catch (Exception e){
            logger.error("查看结算信息总条数失败, methodName:" , e);
        }
        return count;
    }

    /**
     * 根据条件查询
     * @param settleDto
     * @return
     */
    @Override
    public List<SettleDto> search(SettleDto settleDto) {
        List<SettleDto> settleDtoList = null;
        try{
            settleDtoList = settleService.search(settleDto);
        }catch (Exception e){
            logger.error("查询结算信息失败, methodName:" , e);
        }
        return settleDtoList;
    }


    /**
     * 清算
     *  @return
     */
    public Integer settle(){
        Integer result = 0;
        try{
            //result = settleService.settleAmount();
            settleService.settleAmountTask();
        }catch (Exception e){
            result = 1;
            logger.error("清算错误：",e);
        }
        return result;

    }

    /**
     * 添加补贴信息
     * @param settleIds
     * @return
     */
    @Override
    public Map<String, String> addAllowance(String settleIds) {
        Map<String, String> map = null;
        try{
            map = settleService.addAllowance(settleIds);
        }catch (Exception e){
            logger.error("添加补贴信息错误", e);
        }
        return map;
    }

}
