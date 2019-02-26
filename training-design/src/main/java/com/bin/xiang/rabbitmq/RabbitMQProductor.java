package com.bin.xiang.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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
 * @Date Created in 2018年11月11日 15:14
 * @since 1.0
 */
public class RabbitMQProductor {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("xiangbin");
        factory.setPassword("123456");
        factory.setHost("140.143.154.137");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "exchange_priority";
        String routingKey = "queue_priority";
        Map<String,Object> map = new HashMap<>();
        map.put("x-max-priority",10);
        //create exchange
        channel.exchangeDeclare(exchangeName,"direct",true);
        //create queue with priority
        channel.queueDeclare(routingKey,true,false,false,map);
        channel.queueBind(routingKey,exchangeName,"rk_priority");

        //send message with priority
        for (int i = 0; i < 10; i++) {
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            if(i%2 != 0) { builder.priority(5); }
            AMQP.BasicProperties properties = builder.build();
            channel.basicPublish(exchangeName,routingKey,properties,("message_"+i).getBytes());
        }
        channel.close();
        conn.close();

    }
}
