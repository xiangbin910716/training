package com.bin.xiang.kafka;

/**
 * Kafka 相关属性配置类
 */
public interface KafkaProperties {
    // zookeeper连接，与server.properties中的zookeeper.connect属性一致，多个用逗号隔开，例如：zk01:2181,zk02:2181
    public static final String ZK = "140.143.154.137:2181";

    // 如果是多个blocker，用逗号分隔即可，例如：kafka01::9092,kafka02:9093
    public static final String BLOCK_LIST = "140.143.154.137:9092";

    // 主题
    public static final String TOPIC = "test";
}
