package com.bin.xiang.kafka.producer;

import com.bin.xiang.kafka.KafkaProperties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * <p>
 * Kafka 生产者
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
public class KafkaProducer extends Thread{
    private String topic;

    private Producer<Integer, String> producer;

    public KafkaProducer(String topic) {
        this.topic = topic;

        Properties properties = new Properties();
        properties.put("metadata.broker.list", KafkaProperties.BLOCK_LIST);
        properties.put("serializer.class", "kafka.serializer.StringEncoder");

        producer = new Producer<Integer, String>(new ProducerConfig(properties));
    }

    @Override
    public void run() {
        int messageNo = 1;

        while(true) {
            String message = "message_" + messageNo;

            System.out.println("Send：" + message);

            producer.send(new KeyedMessage<Integer, String>(topic, message));

            messageNo ++;

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new KafkaProducer(KafkaProperties.TOPIC).start();
    }
}
