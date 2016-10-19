package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lirenguan on 7/3/15.
 */
public class TaskListResult {


    private List<TaskResult> tasks;

    public List<TaskResult> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResult> tasks) {
        this.tasks = tasks;
    }



}
