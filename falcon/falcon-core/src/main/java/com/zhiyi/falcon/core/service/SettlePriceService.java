package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.core.dao.SettlePriceMapper;
import com.zhiyi.falcon.core.model.SettlePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 设置商品单价
 * Created by Adminstrator on 2015/6/24.
 */
@Transactional
@Service("settlePriceService")
public class SettlePriceService {

    @Autowired
    private SettlePriceMapper settlePriceMapper;


    /**
     * 保存
     * @param settlePriceDto
     * @return
     */
    public Integer save(SettlePriceDto settlePriceDto) {
        SettlePrice settlePrice = SettlePrice.dtoToModel(settlePriceDto);
        settlePrice.setModifyDt(new Date());
        settlePrice.setCreateDt(new Date());
        return settlePriceMapper.insert(settlePrice);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer delete(int id) {
        return settlePriceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新
     * @param settlePriceDto
     * @return
     */
    public Integer update(SettlePriceDto settlePriceDto) {
        SettlePrice settlePrice = SettlePrice.dtoToModel(settlePriceDto);

        settlePrice.setModifyDt(new Date());
        return settlePriceMapper.updateByPrimaryKeySelective(settlePrice);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public SettlePriceDto detail(int id) {
        SettlePrice settlePrice = settlePriceMapper.selectByPrimaryKey(id);
        return SettlePrice.modelToDto(settlePrice);
    }

    /**
     * 统计总条数
     * @param settlePriceDto
     * @return
     */
    public Integer count(SettlePriceDto settlePriceDto) {
        return settlePriceMapper.count(settlePriceDto);
    }

    /**
     * 根据条件查询
     * @param settlePriceDto
     * @return
     */
    public List<SettlePriceDto> search(SettlePriceDto settlePriceDto) {
        List<SettlePrice> settlePriceList = settlePriceMapper.search(settlePriceDto);
        return SettlePrice.modelListToDtoList(settlePriceList);
    }

    /**
     * 根据条件查询出结算价格信息
     * @param settlePriceDto
     * @return
     */
    public SettlePriceDto querySettlePriceByCity(SettlePriceDto settlePriceDto) {
        SettlePrice settlePrice = settlePriceMapper.querySettlePrice(settlePriceDto);
        return SettlePrice.modelToDto(settlePrice);
    }
}
