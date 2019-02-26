package com.bin.xiang.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
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
 * @Date Created in 2018年11月17日 11:31
 * @since 1.0
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("140.143.154.137");
        connectionFactory.setUsername("xiangbin");
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("receive = "+message);
            }
        };

        while(true){
            channel.basicConsume(QUEUE_NAME,true,consumer);
        }


    }
}
