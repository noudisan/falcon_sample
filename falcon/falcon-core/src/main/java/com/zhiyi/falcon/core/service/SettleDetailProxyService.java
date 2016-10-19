package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettleDetailResultDto;
import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.api.service.ISettleDetailService;
import com.zhiyi.falcon.core.model.SettleDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 清算明细
 * Created by Adminstrator on 2015/6/24.
 */
@Service("settleDetailProxyService")
public class SettleDetailProxyService implements ISettleDetailService{

    Logger logger = Logger.getLogger(SettleDetailProxyService.class);

    @Autowired
    private SettleDetailService settleDetailService;


    /**
     * 新增
     * @param settleDetailDto
     * @return
     */
    @Override
    public CommonResult<Integer> save(SettleDetailDto settleDetailDto) {
        logger.info("新增");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try {
            Integer result = settleDetailService.save(settleDetailDto);
            commonResult.setData(result);
        } catch (Exception e) {
            logger.error("新增结算明细失败, methodName:", e);
            commonResult.doErrorHandle("新增结算明细失败");
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> delete(int id) {
        return null;
    }

    public CommonResult<Integer> update(SettleDetailDto settleDetailDto) {
        return settleDetailService.update(settleDetailDto);
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public CommonResult<SettleDetailDto> detail(int id) {
        logger.info("查看详情");
        CommonResult<SettleDetailDto> commonResult = new CommonResult<SettleDetailDto>();
        try {
            SettleDetailDto settleDetailDto = settleDetailService.detail(id);
            commonResult.setData(settleDetailDto);
        } catch (Exception e) {
            logger.error("新增结算明细失败, methodName:" , e);
            commonResult.doErrorHandle("新增结算明细失败");
        }
        return commonResult;
    }

    /**
     * 根据参数查询
     * @param settleDetailDto
     * @return
     */
    @Override
    public List<SettleDetailDto> query(SettleDetailDto settleDetailDto) {
        logger.info("根据条件查询");
        List<SettleDetailDto> settleDetailDtoList = null;
        try{
            settleDetailDtoList = settleDetailService.query(settleDetailDto);
        }catch (Exception e){
            logger.error("查询结算明细失败, methodName:" , e);
        }
        return settleDetailDtoList;
    }

    /**
     * 查询结算详细信息
     * @param settleDetailDto
     * @return
     */
    @Override
    public List<SettleDetailDto> querySettleDetail(SettleDetailDto settleDetailDto) {
        logger.info("查询结算详细信息");
        List<SettleDetailDto> settleDetailDtoList = null;
        try {
            settleDetailDtoList = settleDetailService.querySettleDetail(settleDetailDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settleDetailDtoList;
    }

    /**
     * 查询小区信息
     * @param settleDetailDto
     * @return
     */
    @Override
    public List<SettleDetailDto> queryCommunity(SettleDetailDto settleDetailDto) {
        logger.info("查询结算详细小区信息");
        List<SettleDetailDto> settleDetailDtoList = null;
        try {
            settleDetailDtoList = settleDetailService.queryCommunity(settleDetailDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settleDetailDtoList;
    }

    /**
     * 查询板块信息
     * @param settleDetailDtoSectionQuery
     * @return
     */
    @Override
    public SettleDetailDto querySection(SettleDetailDto settleDetailDtoSectionQuery) {
        SettleDetailDto settleDetailDto = null;
        try {
            settleDetailDto = settleDetailService.querySection(settleDetailDtoSectionQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settleDetailDto;
    }

    @Override
    public List<SettleDetailResultDto> querySettleDetailResultBySettleId(Integer settleId) {
        try {
            List<SettleDetailResultDto> settleDetailDto = settleDetailService.querySettleDetailResultBySettleId(settleId);
            return settleDetailDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
