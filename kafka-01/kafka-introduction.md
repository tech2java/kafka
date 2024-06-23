Kafka Topics:
-------------
- Topics : A particular stream of data.
- Like a Table in database (without all constraints)
- We can have as many topics we want
- A topic is identified by its name.
- Any kind of message format
- The sequence of message is called **data stream**
- We cant query topics , instead , use kafka producers to produce to send data and kafka consumers to receive data.

Partitions & Offsets:
---------------------
- Topics are split in partitions(example: 100 partitions)
- Messages within each partition are **ordered**.
- Each message within a partition get an incremental id , called **offset**.
- Kafka topics are **immutable**: Once data is written to a partition , it can not be changed.
- ![img.png](img.png)
- Example: Truck GPS
- Each truck will send the message to Kafka for every 2o seconds, each message will contain TruckId and Position 


Producers:
----------
- Producer writes data to topics ( which are made of partitions)

`

    /home/srinivas/kafka_2.13-3.0.0/bin 

    zookeeper-server-start.sh ~/kafka_2.13-3.0.0/config/zookeeper.properties 

    kafka-server-start.sh ~/kafka_2.13-3.0.0/config/server.properties 

    kafka-storage.sh format -t 0OxwujilTm6TDCSPhAjqDQ -c ~/kafka_2.13-3.0.0/config/kraft/server.properties 

`