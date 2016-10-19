package com.zhiyi.communityunit.service;

import com.zhiyi.communityunit.dao.DeliveryCommunityUnitMapper;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;
import com.zhiyi.communityunit.model.DeliveryCommunityUnit;
import com.zhiyi.communityunit.transfer.DeliveryCommunityUnitTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryCommunityUnitService {

    @Autowired
    private DeliveryCommunityUnitMapper deliveryCommunityUnitMapper;

    public DeliveryCommunityUnitDto getById(Long id) {
        DeliveryCommunityUnit model = deliveryCommunityUnitMapper.selectByPrimaryKey(id);
        DeliveryCommunityUnitDto dto = DeliveryCommunityUnitTransfer.toDto(model);

        return dto;
    }

    @Transactional
    public Long saveDeliveryCommunityUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto) {
        DeliveryCommunityUnit model = DeliveryCommunityUnitTransfer.toModel(deliveryCommunityUnitDto);

        deliveryCommunityUnitMapper.insertSelective(model);
        return model.getId();
    }

    @Transactional
    public Long updateDeliveryCommunityUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto) {
        DeliveryCommunityUnit model = DeliveryCommunityUnitTransfer.toModel(deliveryCommunityUnitDto);

        deliveryCommunityUnitMapper.updateByPrimaryKeySelective(model);
        return model.getId();
    }


    public Integer count(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto) {

        return deliveryCommunityUnitMapper.count(deliveryCommunityUnitSearchDto);
    }

    public List<DeliveryCommunityUnitDto> search(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto) {
        List<DeliveryCommunityUnit> result = deliveryCommunityUnitMapper.search(deliveryCommunityUnitSearchDto);

        List<DeliveryCommunityUnitDto> list = new ArrayList<>();
        for (DeliveryCommunityUnit deliveryCommunityUnit : result) {
            DeliveryCommunityUnitDto dto = DeliveryCommunityUnitTransfer.toDto(deliveryCommunityUnit);
            list.add(dto);
        }
        return list;
    }

    @Transactional
    public Integer deleteById(List<Long> idList) {
        for (Long id : idList) {
            deliveryCommunityUnitMapper.deleteByPrimaryKey(id);
        }
        return idList.size();
    }
}
