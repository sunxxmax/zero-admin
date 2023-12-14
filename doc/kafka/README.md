# kafka 部署使用

## 启动

```shell
docker-compose up -d
```

## 使用

### 常见操作

#### 主题

> 替换 `your_topic_name` 为您要查看的实际主题名称。

**创建主题**

```shell
kafka-topics.sh --create --topic your_topic_name --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092
```

**删除主题**

```shell
kafka-topics.sh --delete --topic your_topic_name --bootstrap-server localhost:9092
```

**查看所有主题**

```shell
kafka-topics.sh --list --bootstrap-server localhost:9092
```

**查看特定主题的详细信息**

```shell
kafka-topics.sh --describe --topic your_topic_name --bootstrap-server localhost:9092
```

#### 生产者/消费者

> 替换 `your_topic_name` 为您要查看的实际主题名称。
> 替换 `your_group_name` 为您要查看的实际消费者组。
> 替换 `your_consumer_group_name` 为您要查看的实际消费者组。


**启动生产者**

```shell
kafka-console-producer.sh --topic your_topic_name --bootstrap-server localhost:9092
```

**启动消费者**

```shell
kafka-console-consumer.sh --topic your_topic_name --from-beginning --bootstrap-server localhost:9092
```

**查看消费者组列表**

```shell
kafka-consumer-groups.sh --list --bootstrap-server localhost:9092
```

**查看特定消费者组的详细信息**

```shell
kafka-consumer-groups.sh --describe --group your_group_name --bootstrap-server localhost:9092
```

删除消费者组

```shell
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete --group your_consumer_group_name
```

#### Kafka 配置

**查看Kafka配置信息**

```shell
kafka-configs.sh --describe --entity-type brokers --entity-name your_broker_id --bootstrap-server localhost:9092
```



