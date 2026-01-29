package com.example.spring_test_containers.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Producer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    public Producer(KafkaTemplate<String, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendMessage(String topic, Object message) {
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topic, message);
        return completableFuture.thenApply(result -> {
            logger.atInfo()
                    .addKeyValue("topic", topic)
                    .addKeyValue("message", message)
                    .log("Message successfully sent to Kafka!");
            return true;
        }).exceptionally(ex -> {
            logger.atError().setCause(ex).log("Send failed");
            return false;
        }).join();
    }
}
