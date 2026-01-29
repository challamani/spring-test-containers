
## Prerequisites 

```shell
#install spring cli
brew tap spring-io/tap
brew install spring-boot

#create a web application structure with dependencies
spring init --dependencies=web,security --java-version=17 spring-test-containers
```


### Kafka 

https://www.instaclustr.com/education/apache-spark/running-apache-kafka-kraft-on-docker-tutorial-and-best-practices/

```shell
#launch kafka
docker-compose up -d

#terminal into kafka process
docker exec -it kafka bash

/usr/bin/kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
/usr/bin/kafka-console-producer --broker-list --bootstrap-server --topic test-topic
/usr/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --from-beginning
/usr/bin/kafka-topics --list --bootstrap-server localhost:9092
```

```shell
curl -X POST http://localhost:8080/api/events/payment/test-payments \
     -H "Content-Type: application/json" \
     -d '{"transactionId": "TXN-123", "amount": 150.00, "currency": "USD", "status": "PENDING"}'

docker exec kafka kafka-console-consumer --bootstrap-server localhost:9092 \
  --topic test-payments --from-beginning
```