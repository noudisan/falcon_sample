package com.zhiyi.communityunit.api;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;

import java.util.List;

public interface IDeliveryCommunityUnitService {

    CommonResult<DeliveryCommunityUnitDto> getById(Long id);

    CommonResult<Long> saveDeliveryCommunityUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto);

    CommonResult<Long> updateDeliveryCommunityUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto);

    List<DeliveryCommunityUnitDto> search(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto);

    Integer count(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto);

    /**
     * 删除单元
     * @param idList
     * @return
     */
    CommonResult<Integer> deleteById(List<Long> idList);

}
