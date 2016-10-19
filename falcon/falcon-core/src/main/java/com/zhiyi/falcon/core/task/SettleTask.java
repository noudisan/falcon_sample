package com.zhiyi.falcon.core.task;

import com.zhiyi.falcon.api.dto.SettleDto;
import com.zhiyi.falcon.api.service.ISettleService;
import com.zhiyi.falcon.core.service.SettleProxyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Adminstrator on 2015/7/1.
 */
@Component
public class SettleTask {

    private Logger logger = Logger.getLogger(SettleTask.class);

    @Autowired
    private ISettleService settleService;

    /**
     * 每天晚上00：05执行结算
     */
    @Scheduled(cron = "0 5 0 * * ?")  //每天晚上00:05执行
    //@Scheduled(cron = "0 0/1 * * * ?")  //每隔1分钟执行
    public void settleTask(){
        logger.info("结算");
        settleService.settle();
        logger.info("结算定时任务完成");
    }



}
