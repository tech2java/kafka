package com.tech2java.springboot.kafka.producer;

import com.tech2java.springboot.kafka.payload.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private static final Logger log= LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String,Employee> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(Employee employee){

        log.info(String.format("Message sent to topic->%s",employee.toString()));
        Message<Employee> message= MessageBuilder
                    .withPayload(employee)
                    .setHeader(KafkaHeaders.TOPIC,"third_topic")
                    .build();
        kafkaTemplate.send(message);
    }
}
