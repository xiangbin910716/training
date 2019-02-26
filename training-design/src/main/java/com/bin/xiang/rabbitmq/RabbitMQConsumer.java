package com.bin.xiang.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

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
 * @Date Created in 2018年11月11日 15:22
 * @since 1.0
 */
public class RabbitMQConsumer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("xiangbin");
        connectionFactory.setPassword("123456");
        connectionFactory.setHost("140.143.154.137");
        connectionFactory.setPort(5672);
        Connection conn = connectionFactory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "exchange_priority";
        channel.exchangeDeclare(exchangeName,"direct",true);
        String queueName = channel.queueDeclare().getQueue();
        String routingName = "queue_priority";

        channel.queueBind(queueName,exchangeName,routingName);
        while (true){
            boolean autoAck = false;
            String consumerTag = "";

            channel.basicConsume(queueName,autoAck,consumerTag,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);
                }
            });
        }

    }
}
