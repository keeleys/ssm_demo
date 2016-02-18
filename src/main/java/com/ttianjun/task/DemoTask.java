package com.ttianjun.task;

import com.ttianjun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @user keeley
 * @date 16/1/30
 */
@Component
public class DemoTask {
    private static final Logger log = LoggerFactory.getLogger(DemoTask.class);
    @Resource
    private UserService userService;


    @Scheduled(cron="0/10 * * * * *")
    public void executeTask(){
        log.info("DemoTask running");
    }


}
