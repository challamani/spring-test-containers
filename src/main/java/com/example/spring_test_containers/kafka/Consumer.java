package com.example.spring_test_containers.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(id = "example-01",
            topics = "test-payments",
            autoStartup = "${listen.auto.start:true}",
            groupId = "test")
    public void listen(Object message) {

        logger.atInfo()
                .addKeyValue("groupId", "test")
                .addKeyValue("message", message)
                .log("message through listener!");

        try {
            Thread.sleep(3000);
            logger.atInfo().log("Processing finished!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.atError().setCause(e).log("Thread was interrupted");
        }
    }
}
