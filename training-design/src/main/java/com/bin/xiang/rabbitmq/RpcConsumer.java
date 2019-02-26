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
 * @Date Created in 2018年11月18日 15:20
 * @since 1.0
 */
public class RpcConsumer {
    private static final String QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("xiangbin");
        factory.setPassword("123456");
        factory.setHost("140.143.154.137");
        factory.setPort(5672);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);
        System.out.println("rpc consumer is ready....");

        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            AMQP.BasicProperties props = delivery.getProperties();
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(props.getCorrelationId()).build();
            String message = new String(delivery.getBody());
            int n = Integer.parseInt(message);
            System.out.println("[.]fib("+message+")");
            String response = ""+fib(n);
            channel.basicPublish("",props.getReplyTo(),replyProps,response.getBytes());
            System.out.println("response : " + response);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }

    }

    private static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}
