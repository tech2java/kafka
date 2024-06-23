package com.tech2java.springboot.kafka.consumer;

import com.tech2java.springboot.kafka.payload.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger log= LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "third_topic",groupId = "testGroup")
    public void consume(Employee employee){

        log.info(String.format("Message Received->%s",employee.toString()));

    }
}
