package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.api.service.ISettlePriceService;
import com.zhiyi.falcon.core.model.SettlePrice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设置商品单价
 * Created by Adminstrator on 2015/6/24.
 */
@Service("settlePriceProxyService")
public class SettlePriceProxyService implements ISettlePriceService {

    private Logger logger = Logger.getLogger(SettlePriceProxyService.class);

    @Autowired
    private SettlePriceService settlePriceService;


    /**
     * 新增
     * @param settlePriceDto
     * @return
     */
    @Override
    public CommonResult<Integer> save(SettlePriceDto settlePriceDto) {
        logger.info("添加结算单价");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settlePriceService.save(settlePriceDto);

            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("新增结算单价信息失败, methodName:" , e);
            commonResult.doErrorHandle("新增结算单价信息失败");
        }
        return commonResult;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public CommonResult<Integer> delete(int id) {
        logger.info("删除结算单价");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settlePriceService.delete(id);

            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("删除结算单价信息失败, methodName:" , e);
            commonResult.doErrorHandle("删除结算单价信息失败");
        }
        return commonResult;
    }

    /**
     * 更新
     * @param settlePriceDto
     * @return
     */
    @Override
    public CommonResult<Integer> update(SettlePriceDto settlePriceDto) {
        logger.info("更新结算单价");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settlePriceService.update(settlePriceDto);

            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("更新结算单价信息失败, methodName:" , e);
            commonResult.doErrorHandle("更新结算单价信息失败");
        }
        return commonResult;
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public CommonResult<SettlePriceDto> detail(int id) {
        logger.info("结算详细信息");
        CommonResult<SettlePriceDto> commonResult = new CommonResult<SettlePriceDto>();
        try {
            SettlePriceDto settlePriceDto = settlePriceService.detail(id);

            commonResult.setData(settlePriceDto);
        } catch (Exception e) {
            logger.error("查询结算单价信息详情失败, methodName:" , e);
            commonResult.doErrorHandle("查询结算单价信息详情失败");
        }
        return commonResult;
    }

    /**
     * 统计数据总条数
     * @param settlePriceDto
     * @return
     */
    @Override
    public Integer count(SettlePriceDto settlePriceDto) {
        logger.info("查询数据条数");
        Integer count = 0;
        try{
            count = settlePriceService.count(settlePriceDto);
        }catch (Exception e){
            logger.error("查询结算信息单价总条数失败, methodName:" , e);
        }
        return count;
    }

    /**
     * 根据条件查询数据
     * @param settlePriceDto
     * @return
     */
    @Override
    public List<SettlePriceDto> search(SettlePriceDto settlePriceDto) {
        logger.info("查询数据");
        List<SettlePriceDto> settlePriceDtoList = null;
        try{
            settlePriceDtoList = settlePriceService.search(settlePriceDto);
        }catch (Exception e){
            logger.error("查询结算单价信息失败, methodName:" , e);
        }

        return settlePriceDtoList;
    }
}
