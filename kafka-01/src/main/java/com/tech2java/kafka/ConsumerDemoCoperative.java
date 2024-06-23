package com.tech2java.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemoCoperative {

    private static final Logger log= LoggerFactory.getLogger(ConsumerDemoCoperative.class.getSimpleName());

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


        properties.setProperty("partition.assignment.strategy", CooperativeStickyAssignor.class.getName());

        KafkaConsumer<String,String> consumer=new KafkaConsumer<>(properties);


        final Thread mainThread =Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                log.info("Detected a shutdown. Lets exit by calling consumer.wakeup()..");
                consumer.wakeup();

                //join the main thread to complete the work in Main Thread.
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        try{
            consumer.subscribe(Arrays.asList(topic));
            while(true) {

                //log.info("Polling...");
                ConsumerRecords<String,String> records =consumer.poll(Duration.ofMillis(1000));

                for(ConsumerRecord<String, String> record:records){

                    log.info("Key="+record.key() +"=====Value="+record.value());
                    log.info("Partition="+record.partition() +"=====Offset="+record.offset());
                }
            }
        }catch(WakeupException exception){
            log.info("consumer is started to shutdown.");
        }catch (Exception e){
           log.error("Unexpected exception in Consumer..",e);
        }finally {
            consumer.close();//closing the consumer.This will also autocommit the offsets
            log.info("The consumer is now gracefully shutdown.");
        }



    }
}
