package com.tech2java.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log= LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {

        log.info("Hello World!");

        //Create Producer Properties
        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        //Crete Kafka Producer
        KafkaProducer<String,String> producer=new KafkaProducer<>(properties);

        //Create Producer Record
        ProducerRecord<String,String> producerRecord=new ProducerRecord<>("third_topic","Hello World2!");

        //send data
        producer.send(producerRecord);

        //Tell the producer to send all data and block until done!
        producer.flush();

    }
}
