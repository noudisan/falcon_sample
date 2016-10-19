package com.zhiyi.falcon.api.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/24.
 */
public class DeliveryTaskSectionDto implements Serializable{
    private Integer id;

    private Integer sectionId;

    private Integer sendTaskId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSendTaskId() {
        return sendTaskId;
    }

    public void setSendTaskId(Integer sendTaskId) {
        this.sendTaskId = sendTaskId;
    }
}
