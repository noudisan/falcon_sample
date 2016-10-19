package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.core.dao.DeliveryTaskEmployeeMapper;
import com.zhiyi.falcon.core.model.DeliveryTaskEmployee;
import com.zhiyi.falcon.core.transfer.DeliveryTaskEmployeeTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DeliveryTaskEmployeeService {
    @Autowired
    private DeliveryTaskEmployeeMapper deliveryTaskEmployeeMapper;


    public List<DeliveryTaskEmployeeDto> queryByUserId(int userId) {
        List<DeliveryTaskEmployee> deliveryTaskEmployeeList = deliveryTaskEmployeeMapper.selectByEmployeeId(userId);
        List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = new ArrayList<DeliveryTaskEmployeeDto>();
        for(DeliveryTaskEmployee deliveryTaskEmployee :deliveryTaskEmployeeList){
            DeliveryTaskEmployeeDto deliveryTaskEmployeeDto = DeliveryTaskEmployeeTransfer.transForToDto(deliveryTaskEmployee);
            deliveryTaskEmployeeDtos.add(deliveryTaskEmployeeDto);
        }

        return deliveryTaskEmployeeDtos;
    }

    public List<DeliveryTaskEmployeeDto> queryBySendTaskId(int sendTaskId) {
        List<DeliveryTaskEmployee> deliveryTaskEmployeeList = deliveryTaskEmployeeMapper.selectBySendTaskId(sendTaskId);
        List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = new ArrayList<DeliveryTaskEmployeeDto>();
        for(DeliveryTaskEmployee deliveryTaskEmployee:deliveryTaskEmployeeList){
            DeliveryTaskEmployeeDto deliveryTaskEmployeeDto =DeliveryTaskEmployeeTransfer.transForToDto(deliveryTaskEmployee);
            deliveryTaskEmployeeDtos.add(deliveryTaskEmployeeDto);
        }

        return deliveryTaskEmployeeDtos;
    }

    public List<DeliveryTaskEmployeeDto> search(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto){
        List<DeliveryTaskEmployee> deliveryTaskEmployeeList = deliveryTaskEmployeeMapper.search(deliveryTaskEmployeeDto);
        List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = new ArrayList<>();
        for(DeliveryTaskEmployee deliveryTaskEmployee:deliveryTaskEmployeeList){
            DeliveryTaskEmployeeDto dto = DeliveryTaskEmployeeTransfer.transForToDto(deliveryTaskEmployee);
            deliveryTaskEmployeeDtos.add(dto);
        }
        return deliveryTaskEmployeeDtos;
    }

    @Transactional
    public void save(Integer employeeId, Integer taskId) {
        DeliveryTaskEmployee deliveryTaskEmployee =new DeliveryTaskEmployee();
        deliveryTaskEmployee.setEmployeeId(employeeId);
        deliveryTaskEmployee.setSendTaskId(taskId);
        deliveryTaskEmployee.setTaskStatus(TaskStatus.TASKTOBEGIN.name());
        deliveryTaskEmployeeMapper.insert(deliveryTaskEmployee);

    }

    /**
     * 暂时不添加search dto
     * @param deliveryTaskEmployeeDto
     */
    @Transactional
    public String updateTaskUserStatus(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto) {
        DeliveryTaskEmployeeDto searchDto =new DeliveryTaskEmployeeDto();
        searchDto.setEmployeeId(deliveryTaskEmployeeDto.getEmployeeId());
        searchDto.setSendTaskId(deliveryTaskEmployeeDto.getSendTaskId());

        List<DeliveryTaskEmployee> searchResult = deliveryTaskEmployeeMapper.search(searchDto);
        if(searchResult == null || searchResult.isEmpty()){
            return "查询任务失败，请刷新";
        }
        DeliveryTaskEmployee employee = searchResult.get(0);
        employee.setTaskStatus(deliveryTaskEmployeeDto.getTaskStatus().name());
        deliveryTaskEmployeeMapper.updateByPrimaryKeySelective(employee);
        return null;
    }

    public void deleteByTaskId(Integer sendTaskId) {
        deliveryTaskEmployeeMapper.deleteByTaskId(sendTaskId);
    }
}
