package com.bin.xiang.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
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
 * @Date Created in 2018年11月17日 11:21
 * @since 1.0
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("140.143.154.137");
        connectionFactory.setUsername("xiangbin");
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();


        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("send messge:"+message);

        channel.close();
        connection.close();

    }
}
