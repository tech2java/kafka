package com.tech2java.springboot.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class KafkaConsumer {

    private static final Logger log= LoggerFactory.getLogger(KafkaConsumer.class);

    //@KafkaListener(topics = "third_topic",groupId = "testGroup")
    public void consume(String message){

        log.info(String.format("Message Received->%s",message));

    }
}
