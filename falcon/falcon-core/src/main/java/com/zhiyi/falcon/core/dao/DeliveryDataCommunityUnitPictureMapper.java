package com.zhiyi.falcon.core.dao;

import java.util.List;

import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnitPicture;

public interface DeliveryDataCommunityUnitPictureMapper {
    //获取上传图片
    DeliveryDataCommunityUnitPicture getPicture( int deliveryDataCommunityUnitId ,int sequence );
    //获取所有4张单元派送数据的图片
    List<DeliveryDataCommunityUnitPicture> listPictures( int deliveryDataCommunityUnitId );
    //更新上传图片信息
    int updatePicture( DeliveryDataCommunityUnitPicture picture );
    //新建上传图片信息
    void insertPicture( DeliveryDataCommunityUnitPicture picture );
    //移除某单元派发的一张上传图片
    void remmovePicture( int deliveryDataCommunityUnitId ,int sequence );
    
}