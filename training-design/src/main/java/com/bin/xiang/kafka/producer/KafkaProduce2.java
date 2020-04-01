package com.bin.xiang.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.File;
import java.io.FileInputStream;
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
public class KafkaProduce2 {

    private static Properties properties;
    private static KafkaProducer<byte[], byte[]> producer;

    static {
        properties = new Properties();
        String path = KafkaProducer.class.getResource("/").getFile().toString() + "kafka.properties";
        try {
            FileInputStream fin = new FileInputStream(new File(path));
            properties.load(fin);
            //实例化produce
            producer = new KafkaProducer<byte[], byte[]>(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String topic, byte[] key, byte[] value){

        //消息封装
        ProducerRecord<byte[], byte[]> record = new ProducerRecord<>(topic, key, value);

        //发送数据
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(null != e){
                    System.out.println("记录的offset在:" + recordMetadata.offset());
                    System.out.println(e.getMessage() + e);
                } else {
                    System.out.println("发送成功！");
                }
            }
        });

//        // 关闭produce
//        producer.close();
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaProduce2 kafkaProduce2 = new KafkaProduce2();
        for (int i = 0; i < 10; i++) {
            kafkaProduce2.sendMsg("mytest", "mytest".getBytes(), "123".getBytes());
        }
        Thread.sleep(10000);
    }
}
