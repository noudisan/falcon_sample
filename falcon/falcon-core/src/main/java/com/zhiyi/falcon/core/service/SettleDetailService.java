package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettleDetailResultDto;
import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.core.dao.SettleDetailMapper;
import com.zhiyi.falcon.core.model.SettleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 清算明细
 * Created by Adminstrator on 2015/6/24.
 */
@Transactional
@Service("settleDetailService")
public class SettleDetailService {

    @Autowired
    private SettleDetailMapper settleDetailMapper;


    /**
     * 保存结算明细
     * @param settleDetailDto
     * @return
     */
    public Integer save(SettleDetailDto settleDetailDto) {
        SettleDetail settleDetail = SettleDetail.dtoToModel(settleDetailDto);
        return settleDetailMapper.insert(settleDetail);
    }

    public CommonResult<Integer> delete(int id) {
        return null;
    }

    public CommonResult<Integer> update(SettleDetailDto settleDetailDto) {
        return null;
    }

    public SettleDetailDto detail(int id) {
        SettleDetail settleDetail = settleDetailMapper.selectByPrimaryKey(id);
        return SettleDetail.modelToDto(settleDetail);
    }

    public List<SettleDetailDto> query(SettleDetailDto settleDetailDto) {
        List<SettleDetail> settleDetailList = settleDetailMapper.query(settleDetailDto);
        return SettleDetail.modelListToDtoList(settleDetailList);
    }

    /**
     * 查询结算详细信息
     * @param settleDetailDto
     * @return
     */
    public List<SettleDetailDto> querySettleDetail(SettleDetailDto settleDetailDto) {
        List<SettleDetail> settleDetailList = settleDetailMapper.querySettleDetail(settleDetailDto);
        return SettleDetail.modelListToDtoList(settleDetailList);
    }

    /**
     * 查询小区的结算详情
     * @param settleDetailDto
     * @return
     */
    public List<SettleDetailDto> queryCommunity(SettleDetailDto settleDetailDto) {
        List<SettleDetail> settleDetailList = settleDetailMapper.queryCommunity(settleDetailDto);
        return SettleDetail.modelListToDtoList(settleDetailList);
    }

    /**
     * 查询板块信息
     * @param settleDetailDtoSectionQuery
     * @return
     */
    public SettleDetailDto querySection(SettleDetailDto settleDetailDtoSectionQuery) {
        return settleDetailMapper.querySection(settleDetailDtoSectionQuery);
    }

    public List<SettleDetailResultDto> querySettleDetailResultBySettleId(Integer settleId) {
        return settleDetailMapper.querySettleDetailResultBySettleId(settleId);
    }
}
