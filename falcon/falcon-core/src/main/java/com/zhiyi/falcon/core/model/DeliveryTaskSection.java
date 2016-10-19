package com.zhiyi.falcon.core.model;

import java.io.Serializable;

public class DeliveryTaskSection implements Serializable {
    private Integer id;

    private Integer sendTaskId;

    private Integer sectionId;

    public DeliveryTaskSection(Integer id, Integer sendTaskId, Integer sectionId) {
        this.id = id;
        this.sendTaskId = sendTaskId;
        this.sectionId = sectionId;
    }

    public DeliveryTaskSection() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendTaskId() {
        return sendTaskId;
    }

    public void setSendTaskId(Integer sendTaskId) {
        this.sendTaskId = sendTaskId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}