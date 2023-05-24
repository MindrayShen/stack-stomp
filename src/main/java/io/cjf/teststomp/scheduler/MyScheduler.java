package io.cjf.teststomp.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Integer count = 0;

    @Scheduled(fixedRate = 3000)
    public void sendData() throws InterruptedException {
        System.out.println("开始处理定时任务");
        Thread.sleep(10000);
        count++;
        logger.info("begin to send data {}", count);
        simpMessagingTemplate.convertAndSend("/mytopic/greetings", count);
    }

}
