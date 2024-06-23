package com.tech2java.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithKey {

    private static final Logger log= LoggerFactory.getLogger(ProducerDemoWithKey.class.getSimpleName());

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



        for(int j=1;j<=2;j++) {
            for (int i = 1; i <= 10; i++) {

                String topic = "third_topic";
                String key = "id_" + i;
                String value = "Hello World!" + i;

                //Create Producer Record
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

                //send data
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {

                        if (exception == null) {
                            log.info("key:: " + key + "Partition:: " + metadata.partition() + " Offset::" + metadata.offset() + " Timestamp::" + metadata.timestamp());
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
