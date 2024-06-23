package com.tech2java.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {

    private static final Logger log= LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());

    public static void main(String[] args) {

        log.info("Hello World!");

        //Create Producer Properties
        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        properties.setProperty("batch.size","400");
        properties.setProperty("practitioner.class", RoundRobinPartitioner.class.getName());

        //Crete Kafka Producer
        KafkaProducer<String,String> producer=new KafkaProducer<>(properties);


        for(int j=1;j<=10;j++) {
            for (int i = 1; i <= 30; i++) {
                //Create Producer Record
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("third_topic", "Hello World!" + i);

                //send data
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {

                        if (exception == null) {

                            log.info("Topic::" + metadata.topic());
                            log.info("Partition::" + metadata.partition());
                            log.info("Offset::" + metadata.offset());
                            log.info("Timestamp::" + metadata.timestamp());
                        } else {
                            log.error("Error while producing..", exception);
                        }
                    }
                });
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //Tell the producer to send all data and block until done!
        producer.flush();

    }
}
