package com.bin.xiang.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;
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
        factory.setUsername("sms_rabbit_write");
        factory.setPassword("sms_rabbit_write");
        factory.setHost("10.16.9.34");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "exchange_priority";
        String routingKey = "rk_priority";
        String queueName = "d.zyu_model_test";
        Map<String,Object> map = new HashMap<>();
        map.put("x-max-priority",10);
        //create exchange

        //create queue with priority
        channel.exchangeDeclare(exchangeName,"direct",true);
        channel.queueBind(queueName,exchangeName,routingKey);
        channel.queueDeclare(queueName,true,false,false,null);

        //send message with priority
        try{
            for (int i = 0; i < 10; i++) {
                AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();

                if(i%2 != 0) { builder.priority(5); }
                AMQP.BasicProperties properties = new AMQP.BasicProperties("text/plain", (String)null, (Map)null, 2, 0, (String)null, (String)null, (String)null, (String)null, (Date)null, (String)null, (String)null, (String)null, (String)null);
                channel.basicPublish("",queueName,properties,("message_"+i).getBytes());
            }
        }catch (Exception e){
            System.out.println("Exception ==================");
        }

        channel.queueDeclare("",true,false,false,null);
        try{
            for (int i = 0; i < 10; i++) {
                AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();

                if(i%2 != 0) { builder.priority(5); }
                AMQP.BasicProperties properties = new AMQP.BasicProperties("text/plain", (String)null, (Map)null, 2, 0, (String)null, (String)null, (String)null, (String)null, (Date)null, (String)null, (String)null, (String)null, (String)null);
                channel.basicPublish("","",properties,("message_"+i).getBytes());
            }
        }catch (Exception e){
            System.out.println("Exception ==================");
        }
        channel.close();
        conn.close();

    }
}
