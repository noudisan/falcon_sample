package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by renfj on 2015/6/30.
 */
public class DeliveryStepsSearchDto extends PageSearchDto implements Serializable {

    public static final int SORT_COLUMN_FOR_VISIBLE = 1;
    public static final int SORT_COLUMN_FOR_INVISIBLE = 2;

    private Integer id;

    private Integer userId;

    private Integer taskId;

    private String steps;

    private String startTime;

    private String endTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {
        if(iSortCol_0 ==0){
            return  "USER_ID";
        } else if(iSortCol_0 ==1){
            return  "TASK_ID";
        } else if(iSortCol_0 ==2){
            return  "STEPS";
        } else if(iSortCol_0 ==3){
            return  "START_TIME";
        }else if(iSortCol_0 ==4){
            return  "END_TIME";
        }
        return "ID";
    }

}
