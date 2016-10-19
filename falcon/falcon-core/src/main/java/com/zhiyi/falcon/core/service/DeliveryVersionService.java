package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionSearchDto;
import com.zhiyi.falcon.core.dao.DeliveryVersionMapper;
import com.zhiyi.falcon.core.model.DeliveryVersion;
import com.zhiyi.falcon.core.transfer.DeliveryVersionTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/29.
 */
@Service
public class DeliveryVersionService {
    @Autowired
    DeliveryVersionMapper deliveryVersionMapper;

    @Transactional
    public Integer saveDeliveryVersion(DeliveryVersionDto deliveryVersionDto) {
        DeliveryVersion deliveryVersion=DeliveryVersionTransfer.transferForModel(deliveryVersionDto);
        return deliveryVersionMapper.insert(deliveryVersion);
    }

    @Transactional
    public Integer deleteDeliveryVersion(int versionId) {
        return null;
    }

    @Transactional
    public Integer updateDeliveryVersion(DeliveryVersionDto deliveryVersionDto) {
        DeliveryVersion deliveryVersion=DeliveryVersionTransfer.transferForModel(deliveryVersionDto);
        return deliveryVersionMapper.updateByPrimaryKeySelective(deliveryVersion);
    }

    @Transactional
    public DeliveryVersionDto queryOneDeliveryVersion(int versionId) {
        DeliveryVersion deliveryVersion = deliveryVersionMapper.selectByPrimaryKey(versionId);
        return DeliveryVersionTransfer.transferForDto(deliveryVersion);
    }

    @Transactional
    public List<DeliveryVersionDto> search(DeliveryVersionSearchDto deliveryVersionDto) {
        List<DeliveryVersionDto> versionDtoList = new ArrayList<>();
        List<DeliveryVersion> versionList = deliveryVersionMapper.search(deliveryVersionDto);
        for (DeliveryVersion version : versionList) {
            versionDtoList.add(DeliveryVersionTransfer.transferForDto(version));
        }
        return versionDtoList;
    }

    @Transactional
    public int count(DeliveryVersionSearchDto deliveryVersionDto) {
        return deliveryVersionMapper.count(deliveryVersionDto);
    }
}
