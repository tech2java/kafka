################################
##                            ##
##       WITH ZOOKEEPER       ##
##                            ##
################################

# make a zookeeper data directory
mkdir data
mkdir data/zookeeper

# Edit config/zookeeper.properties
# change line to 
# dataDir=/your/path/to/data/zookeeper
# example
# dataDir=/home/stephane/kafka_2.13-3.1.0/data/zookeeper

# start zookeeper (make sure nothing is running on port 2181)
zookeeper-server-start E:\Learning\softwares\kafka\config\zookeeper.properties
zookeeper-server-start.sh ~/kafka_2.13-3.1.0/config/zookeeper.properties

# Open a new terminal (we leave zookeeper running in previous terminal)

# create Kafka data directory
mkdir data/kafka
# Edit config/server.properties
# change line to 
# log.dirs=/your/path/to/data/kafka
# example
# log.dirs=/home/stephane/kafka_2.13-3.1.0/data/kafka

# start Kafka
kafka-server-start  E:\Learning\softwares\kafka\config\server.properties
kafka-server-start.sh ~/kafka_2.13-3.1.0/config/server.properties

# Kafka is running! 
# Keep the two terminal windows opened
