package com.bin.xiang.kafka.consumer;

import com.bin.xiang.spring.schema.User;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * @see https://www.jb51.net/article/142164.htm
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
public class KafkaConsume2 {

    private final static String TOPIC = "test";

    private static Properties properties;

    static {
        properties = new Properties();
        String path = KafkaConsumer.class.getResource("/").getFile().toString() + "kafka.properties";
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /**
         * 获取消息
         *
         * @throws Exception
         */
        public void getMsg() throws Exception {
            ConsumerConfig config = new ConsumerConfig(properties);

            ConsumerConnector consumer = kafka.consumer.Consumer
                    .createJavaConsumerConnector(config);

            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

            topicCountMap.put(TOPIC, new Integer(1));

            StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());

            StringDecoder valueDecoder = new StringDecoder(
                    new VerifiableProperties());

            Map<String, List<KafkaStream<String, String>>> consumerMap = consumer
                    .createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

            KafkaStream<String, String> stream = consumerMap.get(TOPIC).get(0);

            ConsumerIterator<String, String> it = stream.iterator();

            while (it.hasNext()) {
                String json = it.next().message();
                System.out.println(json);
            }
        }


    public static void main(String[] args) throws Exception {
        KafkaConsume2 kafkaConsume2 = new KafkaConsume2();
        kafkaConsume2.getMsg();
    }


}
