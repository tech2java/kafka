package com.tech2java.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    private static final Logger log= LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());

    public static void main(String[] args) {

        log.info("I am a KAFKA Consumer!!");
        String groupId="my-java-application";

        String topic="third_topic";
        //Create Producer Properties
        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");

        //set producer properties
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());


        properties.setProperty("group.id",groupId);
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());
        properties.setProperty("auto.offset.reset","earliest");

        KafkaConsumer<String,String> consumer=new KafkaConsumer<>(properties);


        consumer.subscribe(Arrays.asList(topic));

        while(true) {

            log.info("Polling...");
            ConsumerRecords<String,String> records =consumer.poll(Duration.ofMillis(1000));

            for(ConsumerRecord<String, String> record:records){

                log.info("Key="+record.key() +"=====Value="+record.value());
                log.info("Partition="+record.partition() +"=====Offset="+record.offset());
            }
        }



    }
}
