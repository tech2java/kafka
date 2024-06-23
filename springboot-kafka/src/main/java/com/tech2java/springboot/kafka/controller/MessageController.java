package com.tech2java.springboot.kafka.controller;


import com.tech2java.springboot.kafka.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    //private KafkaProducer kafkaProducer;

    //public MessageController(KafkaProducer kafkaProducer) {
        //this.kafkaProducer = kafkaProducer;
    //}

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("msg") String message){

        //kafkaProducer.send(message);
        return ResponseEntity.ok("Message sent to the Topic.");
    }
}
