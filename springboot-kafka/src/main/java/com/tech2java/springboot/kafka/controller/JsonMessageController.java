package com.tech2java.springboot.kafka.controller;


import com.tech2java.springboot.kafka.payload.Employee;
import com.tech2java.springboot.kafka.producer.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private JsonKafkaProducer jsonKafkaProducer;

    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/postEmployee")
    public ResponseEntity<String> publish(@RequestBody Employee employee){

        jsonKafkaProducer.send(employee);
        return ResponseEntity.ok("Json Message sent to the Topic.");
    }
}
