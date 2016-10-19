package com.zhiyi.falcon.gateway.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto.Sequence;
import com.zhiyi.falcon.api.dto.DeliveryPictureInfoDto;
import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.dto.DeliveryStepsSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryType;
import com.zhiyi.falcon.api.enumType.SettleStatus;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import com.zhiyi.falcon.api.service.IDeliveryDataBuildingService;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityUnitService;
import com.zhiyi.falcon.api.service.IDeliveryStepsService;
import com.zhiyi.falcon.api.service.IDeliveryTaskEmployeeService;
import com.zhiyi.falcon.api.service.IDeliveryTaskService;
import com.zhiyi.utils.DateUtils;
import com.zhiyi.utils.Utils;

/**
 * 派发任务提交接口
 */
@Controller
@RequestMapping("/task")
public class DeliveryDataController {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(DeliveryDataController.class);
	
	public final static int UPLOAD_IMAGE_NUM_MAX = 4;//上传文件最大数量
	
    @Autowired
    private IDeliveryDataCommunityUnitService iDeliveryDataCommunityUnitService;

    @Autowired
    private IDeliveryDataBuildingService iDeliveryDataBuildingService;

    @Autowired
    private IBaseEmployeeService iBaseEmployeeService;

    @Autowired
    private IDeliveryCommunityUnitService iDeliveryCommunityUnitService;

    @Autowired
    private IDeliveryCommunityBuildingService iDeliveryCommunityBuildingService;

    @Autowired
    private IDeliveryTaskEmployeeService deliveryTaskEmployeeService;

    @Autowired
    private IDeliveryStepsService iDeliveryStepsService;

    @Autowired
    private IDeliveryTaskService deliveryTaskService;
    
    @Value(value="${falcon.upload.image.folderUrl}")
    private String uploadImageFolder;//上传图片地址根目录
    @Value(value="${falcon.upload.image.type}")
    private String uploadImageType;//上传图片类型    jpg,gif,png...
    private String[] uploadImageTypes;
    
    @Value("${falcon.upload.image.visitUrl}")
    private String uploadImageVisitUrl;//图片访问根路径    
    
    @PostConstruct
    public void $init$(){
    	//上传图片类型    jpg,gif,png...
    	uploadImageTypes = StringUtils.split(uploadImageType.toLowerCase(),",");
    }

    /**
     * 获取单元派发信息
     * @param unitId
     * @param taskId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUnitDeliveryData", produces = "application/json;charset=UTF-8")
    public CommonResult<DeliveryDataCommunityUnitDto> getUnitDeliveryData(
    	 @RequestParam("unitId") Integer unitId
    	,@RequestParam("taskId") Integer taskId
    ){
    	CommonResult<DeliveryDataCommunityUnitDto> result = iDeliveryDataCommunityUnitService.queryCommunityUnitData(unitId, taskId);
    	
    	if( CommonResult.RESULT_STATUS_FAILURE == result.getCode() ){
    		return result;
    	}
    	Map<Integer,DeliveryDataCommunityUnitPictureDto> pictureMap = Maps.newTreeMap();
    	
    	if( result.getData() == null || CollectionUtils.isEmpty(result.getData().getPictures())  ){
    		return result;
    	}
    	for( DeliveryDataCommunityUnitPictureDto picture : result.getData().getPictures() ){
    		picture.setPath(uploadImageVisitUrl+picture.getPath());
    		pictureMap.put(picture.getSequence(), picture);
    	}
    	
    	List<DeliveryDataCommunityUnitPictureDto> list = Lists.newArrayList();
    	for( int i=0;i<4;i++ ){
    		list.add(pictureMap.get(i));    		
    	}
    	
    	result.getData().setPictures(list);
    	
    	return result;

    }
    
    // 完成单元派发，同步判断是否存在楼栋，小区以及更新对应数据
    @ResponseBody
    @RequestMapping(value = "/finishedUnit", produces = "application/json;charset=UTF-8")
    public CommonResult<Integer> finishedCommunityUnit(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                       @RequestParam(value = "version", required = false) String version,
                                                       @RequestParam(value = "userId", required = false) Integer userId,
                                                       @RequestParam(value = "unitId", required = false) Integer unitId,
                                                       @RequestParam(value = "taskId", required = false) Integer taskId,
                                                       @RequestParam(value = "deliveryAmount", required = false) Integer deliveryAmount,
                                                       @RequestParam(value = "noDeliveryReason", required = false) String noDeliveryReason,
                                                       @RequestParam(value = "deliveryType", required = false) String deliveryType,
                                                       @RequestParam(value = "remark", required = false) String remark) {

        CommonResult<DeliveryCommunityUnitDto> communityUnitDtoCommonResult = iDeliveryCommunityUnitService.getById(Long.valueOf(unitId));

        int building = Integer.parseInt(String.valueOf(communityUnitDtoCommonResult.getData().getBuildingId()));
        CommonResult<DeliveryCommunityBuildingDto> communityBuildingDtoCommonResult = iDeliveryCommunityBuildingService.getById(building);

        CommonResult<BaseEmployeeDto> baseEmployeeDtocommonResult = iBaseEmployeeService.queryOneEmployee(userId);

        DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = new DeliveryDataCommunityUnitDto();
        deliveryDataCommunityUnitDto.setCommunityUnitId(unitId);
        deliveryDataCommunityUnitDto.setDeliveryTaskId(taskId);
        deliveryDataCommunityUnitDto.setDeliveryNum(deliveryAmount);
        deliveryDataCommunityUnitDto.setRemark(remark);

        //是否投递
        if (StringUtils.isNotBlank(noDeliveryReason)) {
            deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.NOTDELIVERY);
            String noDelivery = noDeliveryReason;
            if(StringUtils.isNotBlank(remark)){
                noDelivery = noDeliveryReason+" "+remark;
            }
            deliveryDataCommunityUnitDto.setRemark(noDelivery);
        } else {
            deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.CANDELIVERY);
            deliveryDataCommunityUnitDto.setDeliveryType(DeliveryType.valueOf(deliveryType));
        }
        deliveryDataCommunityUnitDto.setCommunityUnitName(communityUnitDtoCommonResult.getData().getName());

        deliveryDataCommunityUnitDto.setDeliveryEmployeeId(userId);
        if(baseEmployeeDtocommonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
            deliveryDataCommunityUnitDto.setUserName(baseEmployeeDtocommonResult.getData().getUserName());
        }

        deliveryDataCommunityUnitDto.setBuildId(building);
        deliveryDataCommunityUnitDto.setBuildingName(communityUnitDtoCommonResult.getData().getBuildingName());

        deliveryDataCommunityUnitDto.setCommunityId(communityBuildingDtoCommonResult.getData().getCommunityId());
        deliveryDataCommunityUnitDto.setCommunityName(communityBuildingDtoCommonResult.getData().getCommunityName());

        deliveryDataCommunityUnitDto.setDeliveryDt(DateUtils.format(new Date()));
        deliveryDataCommunityUnitDto.setSettleStatus(SettleStatus.UNBALANCED);
        deliveryDataCommunityUnitDto.setTaskSampling(TaskSampling.NOSAMPLING);

        CommonResult<Integer> result = iDeliveryDataCommunityUnitService.finishedUnit(deliveryDataCommunityUnitDto, null);
        return result;
    }
    

    /**
     * 2015-11-30修改可反复更新提交
	 * 完成单元派发，同步判断是否存在楼栋，小区以及更新对应数据
	 * 为了满足这样的需求：使用者拍照时必须在派发现场，前端进行定位操作，图片上传时不一定在现场。
	 * 而且允许使用者随意移除图片（最后以用户端总保存触发进行保存）。
     * @param deviceId
     * @param version
     * @param userId
     * @param unitId
     * @param taskId
     * @param deliveryAmount
     * @param noDeliveryReason
     * @param deliveryType
     * @param remark
     * @param multipartFile0
     * @param flag0  //默认为非0
     * @param multipartFile1
     * @param flag1  //默认为非0
     * @param multipartFile2
     * @param flag2  //默认为非0
     * @param multipartFile3
     * @param flag3  //默认为非0
     * @return
     */
    @ResponseBody
    @RequestMapping(
		 value = "/finishedUnit"
		,method=RequestMethod.POST
		,produces="application/json;charset=UTF-8"
    	,params="_ACTION=SAVE"
    	//,consumes="Content-Type=multipart/form-data"
    )
    public CommonResult<Integer> finishedCommunityUnit(
    	 @RequestParam(value = "deviceId", required = false) String deviceId
    	,@RequestParam(value = "version", required = false) String version
    	,@RequestParam(value = "userId", required = false) Integer userId
    	,@RequestParam(value = "unitId", required = false) Integer unitId
    	,@RequestParam(value = "taskId", required = false) Integer taskId
    	,@RequestParam(value = "deliveryAmount", required = false) Integer deliveryAmount
    	,@RequestParam(value = "noDeliveryReason", required = false) String noDeliveryReason
    	,@RequestParam(value = "deliveryType", required = false) String deliveryType
    	,@RequestParam(value = "remark", required = false) String remark
    	,@RequestParam(value="UPLOAD_IMAGE_0", required = false) CommonsMultipartFile multipartFile0
    	,@RequestParam(value="UPLOAD_FLAG_0", required = false ,defaultValue="1") String flag0
    	,@RequestParam(value="UPLOAD_IMAGE_1", required = false) CommonsMultipartFile multipartFile1
    	,@RequestParam(value="UPLOAD_FLAG_1", required = false,defaultValue="1") String flag1
    	,@RequestParam(value="UPLOAD_IMAGE_2", required = false) CommonsMultipartFile multipartFile2
    	,@RequestParam(value="UPLOAD_FLAG_2", required = false,defaultValue="1") String flag2
    	,@RequestParam(value="UPLOAD_IMAGE_3", required = false) CommonsMultipartFile multipartFile3
    	,@RequestParam(value="UPLOAD_FLAG_3", required = false,defaultValue="1") String flag3
    ) {

        CommonResult<DeliveryCommunityUnitDto> communityUnitDtoCommonResult = iDeliveryCommunityUnitService.getById(Long.valueOf(unitId));

        int building = Integer.parseInt(String.valueOf(communityUnitDtoCommonResult.getData().getBuildingId()));
        CommonResult<DeliveryCommunityBuildingDto> communityBuildingDtoCommonResult = iDeliveryCommunityBuildingService.getById(building);

        CommonResult<BaseEmployeeDto> baseEmployeeDtocommonResult = iBaseEmployeeService.queryOneEmployee(userId);

        DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = new DeliveryDataCommunityUnitDto();
        deliveryDataCommunityUnitDto.setCommunityUnitId(unitId);
        deliveryDataCommunityUnitDto.setDeliveryTaskId(taskId);
        deliveryDataCommunityUnitDto.setDeliveryNum(deliveryAmount);
        deliveryDataCommunityUnitDto.setRemark(remark);

        //是否投递
        if (StringUtils.isNotBlank(noDeliveryReason)) {
            deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.NOTDELIVERY);
            String noDelivery = noDeliveryReason;
            if(StringUtils.isNotBlank(remark)){
                noDelivery = noDeliveryReason==null?"":noDeliveryReason+" "+remark==null?"":remark;
            }
            deliveryDataCommunityUnitDto.setRemark(noDelivery);
        } else {
            deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.CANDELIVERY);
            deliveryDataCommunityUnitDto.setDeliveryType(DeliveryType.valueOf(deliveryType));
        }
        deliveryDataCommunityUnitDto.setCommunityUnitName(communityUnitDtoCommonResult.getData().getName());

        deliveryDataCommunityUnitDto.setDeliveryEmployeeId(userId);
        if(baseEmployeeDtocommonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
            deliveryDataCommunityUnitDto.setUserName(baseEmployeeDtocommonResult.getData().getUserName());
        }

        deliveryDataCommunityUnitDto.setBuildId(building);
        deliveryDataCommunityUnitDto.setBuildingName(communityUnitDtoCommonResult.getData().getBuildingName());

        deliveryDataCommunityUnitDto.setCommunityId(communityBuildingDtoCommonResult.getData().getCommunityId());
        deliveryDataCommunityUnitDto.setCommunityName(communityBuildingDtoCommonResult.getData().getCommunityName());

        deliveryDataCommunityUnitDto.setDeliveryDt(DateUtils.format(new Date()));
        deliveryDataCommunityUnitDto.setSettleStatus(SettleStatus.UNBALANCED);
        deliveryDataCommunityUnitDto.setTaskSampling(TaskSampling.NOSAMPLING);

        CommonResult<Integer> result = new CommonResult<Integer>();
        try {
        	
        	CommonResult<Map<Sequence,DeliveryPictureInfoDto>> resultSavePictures = savePictures(
        			  new MultipartFile[]{multipartFile0 ,multipartFile1 ,multipartFile2 ,multipartFile3}
        			, new boolean[]{
        					   !StringUtils.equals("0", StringUtils.trim(flag0))
        					  ,!StringUtils.equals("0", StringUtils.trim(flag1))
        					  ,!StringUtils.equals("0", StringUtils.trim(flag2))
        					  ,!StringUtils.equals("0", StringUtils.trim(flag3))
        			  }
        			, taskId
        			, userId
        			, unitId
        	);
        	
        	if( resultSavePictures.isSuccess() ){
        		result = iDeliveryDataCommunityUnitService.finishedUnit(deliveryDataCommunityUnitDto,resultSavePictures.getData());
        	}
	
		} catch (Exception e) {
			logger.error("单元派发数据异常",e);
		}
		
		return result;
		
        
    }

    // 完成楼栋派发 同步判断小区完成
    @ResponseBody
    @RequestMapping(value = "/finishedBuilding", produces = "application/json;charset=UTF-8")
    public CommonResult<String> finishedBuilding(@RequestParam(value = "deviceId",required = false)String deviceId,
                                   @RequestParam(value = "version",required = false)String version ,
                                   @RequestParam(value = "userId",required = false)Integer userId,
                                   @RequestParam(value = "taskId",required = false)Integer taskId,
                                   @RequestParam(value = "communityId",required = false)Integer communityId,
                                   @RequestParam(value = "buildingId",required = false)Integer buildingId){

        DeliveryDataBuildingDto deliveryDataBuildingDto =new DeliveryDataBuildingDto();
        deliveryDataBuildingDto.setDeliveryEmployeeId(userId);
        deliveryDataBuildingDto.setDeliveryTaskId(taskId);
        deliveryDataBuildingDto.setCommunityId(communityId);
        deliveryDataBuildingDto.setBuildId(buildingId);
        CommonResult<String> commonResult =iDeliveryDataBuildingService.finishedBuilding(deliveryDataBuildingDto);

        return commonResult;
    }

    //完成任务
    @ResponseBody
    @RequestMapping(value = "/finished", produces = "application/json;charset=UTF-8")
    public  CommonResult<String>  finishedCommunity(@RequestParam(value = "deviceId",required = false)String deviceId,
                                    @RequestParam(value = "version",required = false)String version ,
                                    @RequestParam(value = "deviceType",required = false)String deviceType ,
                                    @RequestParam(value = "userId",required = false)Integer userId,
                                    @RequestParam(value = "taskId",required = false)Integer taskId,
                                    @RequestParam(value = "stepNumber",required = false)Integer stepNumber){
        //更新步数
        DeliveryStepsSearchDto deliveryStepsSearchDto = new DeliveryStepsSearchDto();
        deliveryStepsSearchDto.setUserId(userId);
        deliveryStepsSearchDto.setTaskId(taskId);
        List<DeliveryStepsDto> deliveryStepsDtoList = iDeliveryStepsService.search(deliveryStepsSearchDto);
        if(deliveryStepsDtoList!=null && !deliveryStepsDtoList.isEmpty()){
            DeliveryStepsDto deliveryStepsDto =deliveryStepsDtoList.get(0);
            if(stepNumber == null){
                deliveryStepsDto.setSteps("0");
            }else {
                deliveryStepsDto.setSteps(String.valueOf(stepNumber));
            }
            deliveryStepsDto.setEndTime(new Date());
            iDeliveryStepsService.updateSteps(deliveryStepsDto);
        }

        //更新状态
        DeliveryTaskEmployeeDto deliveryTaskEmployeeDto =new DeliveryTaskEmployeeDto();
        deliveryTaskEmployeeDto.setEmployeeId(userId);
        deliveryTaskEmployeeDto.setSendTaskId(taskId);
        deliveryTaskEmployeeDto.setTaskStatus(TaskStatus.TASKEND);
        CommonResult<String> commonResult = deliveryTaskEmployeeService.updateTaskUserStatus(deliveryTaskEmployeeDto);

       //任务状态变更
        DeliveryTaskEmployeeDto searchDto = new DeliveryTaskEmployeeDto();
        searchDto.setSendTaskId(taskId);
        List<DeliveryTaskEmployeeDto> list =deliveryTaskEmployeeService.search(searchDto);
        boolean notEndTask = false;
        for(DeliveryTaskEmployeeDto dto :list){
            if(TaskStatus.TASKEND != dto.getTaskStatus()){
                notEndTask =true;
            }
        }
        if(!notEndTask){
            CommonResult<DeliveryTaskDto> taskDtoCommonResult = deliveryTaskService.queryTaskData(taskId);
            if(taskDtoCommonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
                DeliveryTaskDto taskDto = taskDtoCommonResult.getData();
                taskDto.setStatus(TaskStatus.TASKEND);
                deliveryTaskService.updateStatus(taskDto);
            }
        }


        return commonResult;
    }
    
    /**
     * 保存上传的文件 在本地服务器 返回保存地址
     * @param multipartFiles
     * @param skipNullFlags 是否跳过 
     * @param taskId
     * @param userId
     * @param unitId
     * @return
     * @throws Exception
     */
	private CommonResult<Map<Sequence,DeliveryPictureInfoDto>> savePictures(
		 MultipartFile[] multipartFiles
		,boolean[] skipNullFlags
		,int taskId
		,int userId
		,int unitId
	) throws Exception{
    	
        String[] fileTypes = new String[4];
        
        //校验文件格式
        for( int i=0;i<multipartFiles.length;i++ ){
        	
        	if( multipartFiles[i] == null){
        		continue;
        	}
        	
        	String originalFilename = multipartFiles[i].getOriginalFilename();
        	
        	String[] names = originalFilename.split("\\.");
        	
        	String fileType = null;
        	
        	if( ArrayUtils.getLength(names) == 1 ){
        		fileType = "jpg";
        	}else{
        		fileType = names[names.length-1];
        	}
        	
        	fileType = fileType.toLowerCase();
        	
        	if( !ArrayUtils.contains(uploadImageTypes, fileType) ){
        		
    			CommonResult<Map<Sequence,DeliveryPictureInfoDto>> result = new CommonResult<Map<Sequence,DeliveryPictureInfoDto>>();
    			result.setCode(CommonResult.RESULT_STATUS_FAILURE);
    			result.setMsg("单元派发-保存上传图片错误:不允许的图片格式:" + fileType);
    			logger.error ("单元派发-保存上传图片错误:不允许的图片格式:" + fileType);
    			return result;
        	}
        	
        	fileTypes[i] = fileType;
        }
        
        Map<Sequence,DeliveryPictureInfoDto> paths = Maps.newHashMap();
        
		try{
			
        	StringBuffer path = new StringBuffer();
        	path.append("/").append("TASK_").append(taskId);
        	path.append("/").append("USER_").append(userId);
        	path.append("/").append("UNIT_").append(unitId);
        	String parentPath = path.toString();

        	path = null;
        	
        	File dir = new File(uploadImageFolder + parentPath);//定义一个文件夹
        	if( !dir.exists() ){
        		//未生成则生成
        		dir.mkdirs();
        	}
        	
    		logger.info("clean : "+dir.getPath());
    		dir = null;

			//保存文件
			for( int i=0;i<multipartFiles.length;i++ ){				
				
	        	if( multipartFiles[i] == null ){
	        		//当该上传文件为null时	        		
	        		logger.info(uploadImageFolder + parentPath + " | ["+i+"] [skipNull="+skipNullFlags[i]+"] null");
	        		paths.put(Sequence.get(i),new DeliveryPictureInfoDto(null,skipNullFlags[i]));	        		
	        	
	        	}else{
	        		//当该上传文件不为空，无视'标记'，强制覆盖原文件
	        		
	        		logger.info(uploadImageFolder + parentPath + " | ["+i+"] size :" + multipartFiles[i].getSize());
	        		String imagePath = "/" + Utils.generateCode() + "." + fileTypes[i];

					multipartFiles[i].transferTo(new File( uploadImageFolder + parentPath + imagePath ));										

					paths.put(Sequence.get(i),new DeliveryPictureInfoDto(parentPath + imagePath,skipNullFlags[i]));
					
	        	}


			}
			
		} catch (Exception e) {
			CommonResult<List<String>> result = new CommonResult<List<String>>();
			result.setCode(CommonResult.RESULT_STATUS_FAILURE);
			result.setMsg("保存上传图片错误");
			logger.error("单元派发-保存上传图片",e);
		}
		CommonResult<Map<Sequence,DeliveryPictureInfoDto>> result = new CommonResult<Map<Sequence,DeliveryPictureInfoDto>>();
		
		result.setData(paths);
		
		return result;
    }
	
    
}
