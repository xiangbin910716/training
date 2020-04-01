package com.bin.xiang.kafka.consumer;

import com.bin.xiang.kafka.KafkaProperties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @since 1.0
 */
public class KafkaConsumer extends Thread {
    private String topic;

    public KafkaConsumer(String topic) {
        this.topic = topic;
    }

    private ConsumerConnector createConsumer() {
        Properties properties = new Properties();
        properties.setProperty("zookeeper.connect", KafkaProperties.ZK);
        properties.setProperty("group.id", "test");

        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    @Override
    public void run() {
        // 创建Consumer
        ConsumerConnector consumer = createConsumer();

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);

        // 获取每次接受到的数据
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);

        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();

        // 不停地从stream中读取最新接收到的数据
        while(iterator.hasNext()){
            String message = String.valueOf(iterator.next().message());

            System.out.println("message：" + message);
        }
    }

    public static void main(String[] args) {
        new KafkaConsumer(KafkaProperties.TOPIC).start();
    }

}
