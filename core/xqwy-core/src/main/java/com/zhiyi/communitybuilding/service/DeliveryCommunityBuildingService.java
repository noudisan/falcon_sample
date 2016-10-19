package com.zhiyi.communitybuilding.service;

import com.zhiyi.communitybuilding.dao.DeliveryCommunityBuildingMapper;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingSearchDto;
import com.zhiyi.communitybuilding.model.DeliveryCommunityBuilding;
import com.zhiyi.communitybuilding.transfer.DeliveryCommunityBuildingTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryCommunityBuildingService {

    @Autowired
    private DeliveryCommunityBuildingMapper deliveryCommunityBuildingMapper;


    public DeliveryCommunityBuildingDto getById(Integer id) {

        DeliveryCommunityBuilding deliveryCommunityBuilding = deliveryCommunityBuildingMapper.selectByPrimaryKey(id);
        return DeliveryCommunityBuildingTransfer.toDto(deliveryCommunityBuilding);
    }

    @Transactional
    public Integer saveDeliveryCommunityBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto) {
        DeliveryCommunityBuilding model = DeliveryCommunityBuildingTransfer.toModel(deliveryCommunityBuildingDto);

        return deliveryCommunityBuildingMapper.insertSelective(model);
    }

    @Transactional
    public Integer updateDeliveryCommunityBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto) {
        DeliveryCommunityBuilding model = DeliveryCommunityBuildingTransfer.toModel(deliveryCommunityBuildingDto);

        return deliveryCommunityBuildingMapper.updateByPrimaryKeySelective(model);
    }

    public List<DeliveryCommunityBuildingDto> search(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto) {
        List<DeliveryCommunityBuildingDto> communityBuildingDtoList =new ArrayList<>();

        List<DeliveryCommunityBuilding> deliveryCommunityBuildingList = deliveryCommunityBuildingMapper.search(deliveryCommunityBuildingSearchDto);
        if(deliveryCommunityBuildingList == null || deliveryCommunityBuildingList.isEmpty()){
            return communityBuildingDtoList;
        }

        for(DeliveryCommunityBuilding building :deliveryCommunityBuildingList){
            communityBuildingDtoList.add(DeliveryCommunityBuildingTransfer.toDto(building));
        }

        return communityBuildingDtoList;
    }

    public Integer count(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto) {
        Integer count =deliveryCommunityBuildingMapper.count(deliveryCommunityBuildingSearchDto);

        return count;
    }

    @Transactional
    public Integer saveDeliveryCommunityBuildingList(List<DeliveryCommunityBuildingDto> list) {
        for(DeliveryCommunityBuildingDto dto:list){
            this.saveDeliveryCommunityBuilding(dto);
        }
        return list.size();
    }

    @Transactional
    public Integer deleteById(List<Integer> list) {
        for(Integer id :list){
            deliveryCommunityBuildingMapper.deleteByPrimaryKey(id);
        }

        return list.size();
    }
}
