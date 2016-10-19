package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSectionDto;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.core.dao.BaseEmployeeMapper;
import com.zhiyi.falcon.core.dao.DeliveryTaskMapper;
import com.zhiyi.falcon.core.model.BaseEmployee;
import com.zhiyi.falcon.core.model.DeliveryTask;
import com.zhiyi.falcon.core.transfer.DeliveryTaskTransfer;
import com.zhiyi.section.api.IDeliverySectionService;
import com.zhiyi.section.dto.DeliverySectionDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务管理
 */
@Service
public class DeliveryTaskService {

    @Autowired
    private DeliveryTaskMapper deliveryTaskMapper;
    @Autowired
    BaseEmployeeMapper baseEmployeeMapper;

    @Autowired
    private DeliveryTaskEmployeeService deliveryTaskEmployeeService;

    @Autowired
    private DeliveryTaskSectionService deliveryTaskSectionService;

    @Autowired
    private IDeliverySectionService sectionService;

    @Transactional
    public int save(DeliveryTaskDto deliveryTaskDto) {
        deliveryTaskDto.setIsSampling(TaskSampling.NOSAMPLING);
        deliveryTaskDto.setStatus(TaskStatus.TASKTOBEGIN);
        DeliveryTask deliveryTask = DeliveryTaskTransfer.transForToModel(deliveryTaskDto);
        deliveryTaskMapper.insert(deliveryTask);

        Integer taskId = deliveryTask.getId();
        //级联保存员工
        String employeeIdStr = deliveryTaskDto.getEmployeeIdStr();
        if(StringUtils.isNotBlank(employeeIdStr)){
            String[] employeeIdArray = employeeIdStr.split(",");
            for(String employeeId:employeeIdArray){
                deliveryTaskEmployeeService.save(Integer.valueOf(employeeId),taskId);
            }
        }

        //级联保存板块
        String sectionIdStr =deliveryTaskDto.getSectionIdStr();
        if(StringUtils.isNotBlank(sectionIdStr)){
            String [] sectionIdArray =sectionIdStr.split(",");
            for(String sectionId : sectionIdArray){
                deliveryTaskSectionService.save(Integer.valueOf(sectionId),taskId);
            }
        }

        return taskId;
    }

    @Transactional
    public int delete(int taskId) {
        int result = deliveryTaskMapper.deleteByPrimaryKey(taskId);
        return result;
    }

    @Transactional
    public int update(DeliveryTaskDto deliveryTaskDto) {
        DeliveryTask deliveryTask = DeliveryTaskTransfer.transForToModel(deliveryTaskDto);
        int result = deliveryTaskMapper.updateByPrimaryKeySelective(deliveryTask);

        //级联更新员工
        String employeeIdStr = deliveryTaskDto.getEmployeeIdStr();
        Integer taskId = deliveryTask.getId();
        if(StringUtils.isNotBlank(employeeIdStr)){
            //删除
            deliveryTaskEmployeeService.deleteByTaskId(taskId);
            //保存
            String[] employeeIdArray = employeeIdStr.split(",");
            for(String employeeId:employeeIdArray){
                deliveryTaskEmployeeService.save(Integer.valueOf(employeeId), taskId);
            }
        }

        //级联跟新板块
        String sectionIdStr =deliveryTaskDto.getSectionIdStr();
        if(StringUtils.isNotBlank(sectionIdStr)){
            //删除
            deliveryTaskSectionService.deleteByTaskId(taskId);
            //保存
            String [] sectionIdArray =sectionIdStr.split(",");
            for(String sectionId : sectionIdArray){
                deliveryTaskSectionService.save(Integer.valueOf(sectionId), taskId);
            }
        }

        return result;
    }

    /**
     * 获取任务详情，包含板块，人员
     * @param taskId
     * @return
     */
    public DeliveryTaskDto queryOneTask(int taskId) {
        DeliveryTask deliveryTask = deliveryTaskMapper.selectByPrimaryKey(taskId);
        DeliveryTaskDto deliveryTaskDto = DeliveryTaskTransfer.transForToDto(deliveryTask);

        List<DeliveryTaskEmployeeDto> employeeList = deliveryTaskEmployeeService.queryBySendTaskId(taskId);
        deliveryTaskDto.setEmployeeList(employeeList);
        if(employeeList != null && !employeeList.isEmpty()){
            StringBuffer idBuffer =new StringBuffer();
            StringBuffer nameBuffer =new StringBuffer();
            for(DeliveryTaskEmployeeDto dto :employeeList){
                idBuffer.append(dto.getEmployeeId()).append(",");
                //FIXME 任务内人员多时，不查询人员姓名  后面切换成employeeService
                BaseEmployee employee = baseEmployeeMapper.selectByPrimaryKey(dto.getEmployeeId());
                if(employee != null){
                    nameBuffer.append(employee.getUserName()).append(",");
                }
            }
            if(idBuffer.length() > 1){
                deliveryTaskDto.setEmployeeIdStr(idBuffer.subSequence(0, idBuffer.length()-1).toString());
            }
            if(nameBuffer.length() > 1){
                deliveryTaskDto.setEmployeeNameStr(nameBuffer.subSequence(0, nameBuffer.length()-1).toString());
            }
        }

        List<DeliveryTaskSectionDto> sectionList = deliveryTaskSectionService.queryByTaskId(taskId);
        deliveryTaskDto.setSectionList(sectionList);
        if(sectionList != null && !sectionList.isEmpty()){
            StringBuffer idBuffer =new StringBuffer();
            StringBuffer nameBuffer =new StringBuffer();
            for(DeliveryTaskSectionDto dto :sectionList){
                idBuffer.append(dto.getSectionId()).append(",");
                CommonResult<DeliverySectionDto> sectionResult = sectionService.queryOneDeliverySection(dto.getSectionId());
                if(sectionResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
                    nameBuffer.append(sectionResult.getData().getSectionName()).append(",");
                }
            }
            if(idBuffer.length() > 1){
                deliveryTaskDto.setSectionIdStr(idBuffer.subSequence(0, idBuffer.length() - 1).toString());
            }
            if(nameBuffer.length() > 1){
                deliveryTaskDto.setSectionNameStr(nameBuffer.subSequence(0, nameBuffer.length()-1).toString());
            }
        }

        return  deliveryTaskDto;
    }

    public List<DeliveryTaskDto> search(DeliveryTaskSearchDto deliveryTaskSearchDto) {
        List<DeliveryTask> taskList = deliveryTaskMapper.search(deliveryTaskSearchDto);

        List<DeliveryTaskDto> taskDtoList = new ArrayList<>();
        for (DeliveryTask deliveryTask : taskList) {
            taskDtoList.add(DeliveryTaskTransfer.transForToDto(deliveryTask));
        }
        return taskDtoList;
    }

    public Integer count(DeliveryTaskSearchDto deliveryTaskSearchDto) {
        return  deliveryTaskMapper.count(deliveryTaskSearchDto);
    }

    public int updateStatus(DeliveryTaskDto taskDto) {
        DeliveryTask taskModel = deliveryTaskMapper.selectByPrimaryKey(taskDto.getId());
        if(taskDto.getStatus() ==null){
            return -1;
        }
        taskModel.setStatus(taskDto.getStatus().name());
        deliveryTaskMapper.updateByPrimaryKeySelective(taskModel);
        return 0;
    }
}
