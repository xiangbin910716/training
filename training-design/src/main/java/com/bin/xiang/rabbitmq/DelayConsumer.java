package com.bin.xiang.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangb
 * @version 1.0
 * @Date Created in 2018年11月18日 10:19
 * @since 1.0
 */
public class DelayConsumer {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("xiangbin");
        factory.setPassword("123456");
        factory.setHost("140.143.154.137");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("exchange_delay_begin","direct",true);
        channel.exchangeDeclare("exchange_delay_done","direct",true);

        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","exchange_delay_done");

        channel.queueDeclare("queue_delay_begin",true,false,false,map);
        channel.queueDeclare("queue_delay_done",true,false,false,null);

        channel.queueBind("queue_delay_begin","exchange_delay_begin","delay");
        channel.queueBind("queue_delay_done","exchange_delay_done","delay");
        System.out.println("prepare is ok!");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume("queue_delay_done",false,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody(),"UTF-8");
            System.out.println("receive msg is :" + msg);
            System.out.println("receive msg date:" + new Date());
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }


    }
}
