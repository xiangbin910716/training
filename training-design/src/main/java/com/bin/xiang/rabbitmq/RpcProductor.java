package com.bin.xiang.rabbitmq;



import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
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
public class RpcProductor {
    private static final String QUEUE_NAME = "rpc_queue";


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("xiangbin");
        factory.setPassword("123456");
        factory.setHost("140.143.154.137");
        factory.setPort(5672);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String replyConsume = channel.queueDeclare().getQueue();

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyConsume,true,consumer);

        String response = null;
        String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyConsume)
                .build();
        String message = "30";
        channel.basicPublish("",QUEUE_NAME,properties,message.getBytes());
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if(delivery.getProperties().getCorrelationId().equals(corrId)){
                response = new String(delivery.getBody());
                System.out.println(response);
                break;
            }
        }

    }

}
