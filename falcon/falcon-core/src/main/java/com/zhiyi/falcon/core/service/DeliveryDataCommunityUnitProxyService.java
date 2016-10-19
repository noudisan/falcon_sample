package com.zhiyi.falcon.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto.Sequence;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitResultDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryPictureInfoDto;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityUnitService;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnitPicture;


@Service
public class DeliveryDataCommunityUnitProxyService implements IDeliveryDataCommunityUnitService{
    private static Logger logger = Logger.getLogger(DeliveryDataCommunityUnitProxyService.class);

    @Autowired
    private DeliveryDataCommunityUnitService deliveryDataCommunityUnitService;
    
    @Override
    public CommonResult<Integer> save(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto) {
        CommonResult<Integer> commonResult =  new CommonResult<>();
        try{
            int result = deliveryDataCommunityUnitService.save(deliveryDataCommunityUnitDto);
            commonResult.setData(result);
        }catch(Exception e){
            commonResult.doErrorHandle("保存楼栋单元派送数据失败，请刷新后重试");
            logger.error("保存楼栋单元派送数据失败,save:", e);
        }

        return commonResult;
    }

    @Override
//    public CommonResult<Integer> finishedUnit(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto){
//        CommonResult<Integer> commonResult = new CommonResult<>();
//        try{
//             String insertResult = deliveryDataCommunityUnitService.finishedUnit(deliveryDataCommunityUnitDto);
//            if(StringUtils.isNotBlank(insertResult)){
//                commonResult.doErrorHandle(insertResult);
//            }
//        }catch(Exception e){
//            commonResult.doErrorHandle("完成楼栋单元派送数据失败，请刷新后重试");
//            logger.error("完成楼栋单元派送数据失败,finishedUnit:", e);
//        }
//        return commonResult;
//    }

    public CommonResult<Integer> finishedUnit(
    	 DeliveryDataCommunityUnitDto unitDataDto
    	,Map<Sequence,DeliveryPictureInfoDto> imageSavePaths
    ){
        CommonResult<Integer> commonResult = new CommonResult<>();
        try{
        	
            String insertResult = deliveryDataCommunityUnitService.finishedUnit(unitDataDto);
            
            if( MapUtils.isNotEmpty(imageSavePaths) ){
           	 	
            	DeliveryPictureInfoDto info;
            	
				for( Sequence seq : imageSavePaths.keySet() ){
					info = imageSavePaths.get(seq);
					if( info.getPath() == null ){
						if(!info.isSkipNull() ){
							deliveryDataCommunityUnitService.deletePicture(unitDataDto.getId(),seq.getValue());
						}else{
							//skip
						}
					}else{
						//保存
						DeliveryDataCommunityUnitPicture picture = new DeliveryDataCommunityUnitPicture();				
						picture.setDeliveryDataCommunityUnitId(unitDataDto.getId());
						picture.setPath(info.getPath());//可能为null
						picture.setSequence(seq.getValue());
						picture.setSaveDate(new Date());
						deliveryDataCommunityUnitService.savePicture(picture);
					}
				}				
           }
            
            
            if(StringUtils.isNotBlank(insertResult)){
                commonResult.doErrorHandle(insertResult);
            }
        }catch(Exception e){
            commonResult.doErrorHandle("完成楼栋单元派送数据失败，请刷新后重试");
            logger.error("完成楼栋单元派送数据失败,finishedUnit:", e);
        }
        return commonResult;
    }
    
    @Override
    public CommonResult<Integer> updateDeliveryData(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try{
            int result = deliveryDataCommunityUnitService.updateDeliveryData(deliveryDataCommunityUnitDto);
            commonResult.setData(result);
        }catch(Exception e){
            commonResult.doErrorHandle("更新楼栋单元派送数据失败，请刷新后重试");
            logger.error("更新楼栋单元派送数据失败,updateDeliveryData:", e);
        }
        return null;
    }

    @Override
    public CommonResult<DeliveryDataCommunityUnitDto> queryDeliveryData(int id) {
        CommonResult<DeliveryDataCommunityUnitDto> commonResult = new CommonResult<>();
        try{
            DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = deliveryDataCommunityUnitService.queryDeliveryData(id);

            commonResult.setData(deliveryDataCommunityUnitDto);
        }catch(Exception e){
            commonResult.doErrorHandle("查询楼栋单元派送数据失败，请刷新后重试");
            logger.error("查询楼栋单元派送数据失败,queryDeliveryData:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<DeliveryDataCommunityUnitDto> queryCommunityUnitData(Integer unitId,Integer taskId){
        CommonResult<DeliveryDataCommunityUnitDto> commonResult = new CommonResult<>();
        try{
            DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = deliveryDataCommunityUnitService.queryCommunityUnitData(unitId,taskId);
            commonResult.setData(deliveryDataCommunityUnitDto);
        }catch(Exception e){
            commonResult.doErrorHandle("查询楼栋单元派送数据失败，请刷新后重试");
            logger.error("查询楼栋单元派送数据失败,queryCommunityUnitData:",e);
        }
        return commonResult;
    }

    @Override
    public List<DeliveryDataCommunityUnitDto> search(DeliveryDataCommunityUnitSearchDto searchDto) {
        try{
           return deliveryDataCommunityUnitService.search(searchDto);
        }catch(Exception e){
            logger.error("查询楼栋单元派送数据失败,search:", e);
        }

        return null;
    }

    @Override
    public Integer count(DeliveryDataCommunityUnitSearchDto searchDto) {
       try{
           return deliveryDataCommunityUnitService.count(searchDto);
       }catch(Exception e){
           logger.error("计算楼栋单元派送数据失败,count:",e);
       }

        return null;
    }

    @Override
    public CommonResult<List<DeliveryDataCommunityUnitDto>> searchAllCommunityWithTask(Integer buildingId, Integer taskId) {
        CommonResult<List<DeliveryDataCommunityUnitDto>> commonResult =new  CommonResult<List<DeliveryDataCommunityUnitDto>>();
        try{
            List<DeliveryDataCommunityUnitDto> list = deliveryDataCommunityUnitService.searchAllCommunityWithTask(buildingId, taskId);
            commonResult.setData(list);
        }catch(Exception e){
            commonResult.doErrorHandle("查询楼栋单元派送数据失败，请刷新后重试");
            logger.error("查询楼栋单元派送数据失败,searchAllCommunityBuildingWithTask:",e);
        }

        return commonResult;
    }

    /**
     * 查询派送的总的册子数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    @Override
    public DeliveryDataCommunityUnitResultDto queryTotalSendNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        DeliveryDataCommunityUnitResultDto deliveryDataCommunityUnitResultDto = null;
        try{
           deliveryDataCommunityUnitResultDto = deliveryDataCommunityUnitService.queryTotalSendNum(deliveryDataCommunitySearchDto);
        }catch (Exception e){
            logger.error("查询派送的总册子数量错误,queryTotalSendNum:",e);
        }
        return deliveryDataCommunityUnitResultDto;
    }

    /**
     * 查询派送的小区数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    @Override
    public DeliveryDataCommunityUnitResultDto queryCommunityDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        DeliveryDataCommunityUnitResultDto deliveryDataCommunityUnitResultDto = null;
        try{
            deliveryDataCommunityUnitResultDto = deliveryDataCommunityUnitService.queryCommunityDeliveryNum(deliveryDataCommunitySearchDto);
        }catch (Exception e){
            logger.error("查询楼栋派送的小区数量失败,queryCommunityDeliveryNum:",e);
        }
        return deliveryDataCommunityUnitResultDto;

    }

    /**
     * 查询出派送楼栋数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    @Override
    public DeliveryDataCommunityUnitResultDto queryBuildDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        DeliveryDataCommunityUnitResultDto deliveryDataCommunityUnitResultDto = null;
        try{
            deliveryDataCommunityUnitResultDto = deliveryDataCommunityUnitService.queryBuildDeliveryNum(deliveryDataCommunitySearchDto);
        }catch (Exception e){
            logger.error("查询派送楼栋数量失败,queryBuildDeliveryNum:",e);
        }
        return deliveryDataCommunityUnitResultDto;
    }

    /**
     * 查询出派送的单元数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    @Override
    public DeliveryDataCommunityUnitResultDto queryUnitDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        DeliveryDataCommunityUnitResultDto deliveryDataCommunityUnitResultDto = null;
        try{
            deliveryDataCommunityUnitResultDto = deliveryDataCommunityUnitService.queryUnitDeliveryNum(deliveryDataCommunitySearchDto);
        }catch (Exception e){
            logger.error("查询派送的单元数量错误,queryUnitDeliveryNum:",e);
        }
        return deliveryDataCommunityUnitResultDto;
    }

    /**
     * 查询出不同的派送方式派送的总数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    @Override
    public List<DeliveryDataCommunityUnitResultDto> queryDeliveryTypeNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        List<DeliveryDataCommunityUnitResultDto> deliveryDataCommunityUnitResultDtoList = null;
        try {
            deliveryDataCommunityUnitResultDtoList = deliveryDataCommunityUnitService.queryDeliveryTypeNum(deliveryDataCommunitySearchDto);
        } catch (Exception e) {
            logger.error("查询出不同的派送方式派送的总数量出错：",e);
        }
        return deliveryDataCommunityUnitResultDtoList;
    }

    @Override
    public CommonResult<Integer> sampling(List<Integer> idList) {
        CommonResult<Integer> commonResult =new  CommonResult<Integer>();
        try {
           Integer count = deliveryDataCommunityUnitService.sampling(idList);
            commonResult.setData(count);
        } catch (Exception e) {
            commonResult.doErrorHandle("批量抽样失败，请刷新后重试");
            logger.error("抽样失败：",e);
        }
        return commonResult;
    }


}
