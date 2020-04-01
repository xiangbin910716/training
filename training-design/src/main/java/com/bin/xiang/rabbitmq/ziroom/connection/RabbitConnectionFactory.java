/**
 * @FileName: RabbitConnectionFactory.java
 * @Package com.asure.framework.rabbitmq
 * @author zhangshaobin
 * @created 2016年3月1日 上午10:05:54
 * <p/>
 * Copyright 2011-2015 asura
 */
package com.bin.xiang.rabbitmq.ziroom.connection;

import com.bin.xiang.rabbitmq.ziroom.exception.AsuraRabbitMqException;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 连接工厂创建
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>	2018-12-10	jixd add: 获取连接失败定时重试，异常原因不能恢复关闭连接
 * </PRE>
 *
 * @author zhangshaobin
 * @version 1.0
 * @since 1.0
 */
public class RabbitConnectionFactory {

    private final static Logger logger = LoggerFactory.getLogger(RabbitConnectionFactory.class);

    public static final String rabbit_server = "rabbit.server";
    public static final String rabbit_server_port = "rabbit.server.port";
    public static final String rabbit_server_username = "rabbit.server.username";
    public static final String rabbit_server_password = "rabbit.server.password";
    public static final String rabbit_server_automatic_recovery_enabled = "rabbit.server.automatic_recovery_enabled";
    public static final String rabbit_server_env = "rabbit.server.env";
    public static final String rabbit_virtual_host = "rabbit.virtual.host";

    private volatile AtomicBoolean isRecovery = new AtomicBoolean(false);
    //是否显示关闭连接
    public volatile AtomicBoolean isClose = new AtomicBoolean(false);

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,"rabbit_connection_recovery_thread");
            t.setDaemon(true);
            return t;
        }
    });

    private ScheduledFuture<?> sendFuture;

    private final ShutdownListener shutdownListener = new ShutdownListener() {
        @Override
        public void shutdownCompleted(ShutdownSignalException cause) {
            logger.info("listener shutdownsignal:",cause);
            //connection异常
            Throwable throwable = cause.getCause();
            if (throwable instanceof EOFException){
                //需要恢复 connection 和 channel
                try{
                    if (cause.isHardError()){
                        //connection异常 重新连接 ，如果不能连接一直重试
                        Connection connection = (Connection)cause.getReference();
                        connection.abort();
                        isClose.set(true);
                        isRecovery.set(true);
                        recovery();
                    }
                }catch (Exception e){
                    logger.info("e",e);
                }
            }
        }
    };

    private PropertiesParser cfg;

    private String propSrc = null;

    private ConnectionFactory connectionFactory = null;

    private Connection connection;

    /**
     * 初始化
     *
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:34:19
     */
    public void init() {
        initialize("rabbit.properties");
    }

    /**
     * 初始化-解析配置文件
     *
     * @param filename
     * @throws AsuraRabbitMqException
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:34:42
     */
    public void initialize(String filename) throws AsuraRabbitMqException {
        if (cfg != null) {
            return;
        }
        InputStream is = null;
        Properties props = new Properties();

        is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(filename);

        try {
            if (is != null) {
                is = new BufferedInputStream(is);
                propSrc = "the specified file : '" + filename
                        + "' from the class resource path.";
            } else {
                is = new BufferedInputStream(new FileInputStream(filename));
                propSrc = "the specified file : '" + filename + "'";
            }
            props.load(is);
        } catch (IOException ioe) {
            AsuraRabbitMqException initException = new AsuraRabbitMqException(
                    "Properties file: '" + filename + "' could not be read.",
                    ioe);
            throw initException;
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException ignore) {
                }
        }

        initialize(props);
    }

    /**
     * 初始化-解析配置文件
     *
     * @param props
     * @throws AsuraRabbitMqException
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:35:53
     */
    private void initialize(Properties props) throws AsuraRabbitMqException {
        if (propSrc == null) {
            propSrc = "an externally provided properties instance.";
        }

        this.cfg = new PropertiesParser(props);
    }

    public ConnectionFactory getConnectFactory() {
        if (connectionFactory != null) {
            return connectionFactory;
        }
        if (initCheck()) {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(cfg.getStringProperty(rabbit_server));
            connectionFactory.setPort(cfg.getIntProperty(rabbit_server_port));
            connectionFactory.setUsername(cfg
                    .getStringProperty(rabbit_server_username));
            connectionFactory.setPassword(cfg
                    .getStringProperty(rabbit_server_password));
            connectionFactory
                    .setAutomaticRecoveryEnabled(cfg
                            .getBooleanProperty(rabbit_server_automatic_recovery_enabled));
            connectionFactory.setTopologyRecoveryEnabled(false);
            String virtualHost = cfg.getStringProperty(rabbit_virtual_host);
            if (!(virtualHost == null || "".equals(virtualHost))){
                connectionFactory.setVirtualHost(virtualHost);
            }
            connectionFactory.setTopologyRecoveryEnabled(false);
        }
        return connectionFactory;
    }

    /**
     * 校验配置参数
     *
     * @return
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:36:04
     */
    private boolean initCheck() {
        boolean flag = true;
        if (cfg.getStringProperty(rabbit_server) == null) {
            flag = false;
            String msg_error = "rabbit.server配置不能为空值!!!!";
            //	System.out.println(msg_error);
            logger.error(msg_error);
            return flag;
        }

        if (cfg.getIntProperty(rabbit_server_port) == 0) {
            flag = false;
            String msg_error = "rabbit.server.port配置不能为空值!!!!";
            //	System.out.println(msg_error);
            logger.error(msg_error);
            return flag;
        }

        if (cfg.getStringProperty(rabbit_server_username) == null) {
            flag = false;
            String msg_error = "rabbit.server.username配置不能为空值!!!!";
            //	System.out.println(msg_error);
            logger.error(msg_error);
            return flag;
        }

        if (cfg.getStringProperty(rabbit_server_password) == null) {
            flag = false;
            String msg_error = "rabbit.server.password配置不能为空值!!!!";
            //	System.out.println(msg_error);
            logger.error(msg_error);
            return flag;
        }
        return flag;
    }

    /**
     * 获取系统所处的环境
     *
     * @return
     */
    public String getEnvironment() {
        if (cfg.getStringProperty(rabbit_server_env) == null) {
            return null;
        }
        return cfg.getStringProperty(rabbit_server_env);
    }

    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     * @throws TimeoutException
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:36:28
     */
    public Connection getConnection() {
        try{
            if (connection == null || !connection.isOpen()) {
                synchronized (this) {
                    if (connection == null || !connection.isOpen()) {
                        connection = getConnectFactory().newConnection();
                        connection.addShutdownListener(shutdownListener);
                        isClose.set(false);
                    }
                }
            }
        }catch (Exception e){
            //异常后说明获取连接失败，可能是服务器连接失败，开启定时器定时重试获取连接
            isRecovery.set(true);
            recovery();
            throw new AsuraRabbitMqException("fail get connection,retry...",e);
        }
        return connection;
    }



    //恢复连接
    private void recovery(){
        logger.info("enter recovery process");
        if (isRecovery.get() && (sendFuture == null || sendFuture.isCancelled())){
            sendFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                   logger.info("schedule task retry recovery connection");
                   try {
                       getConnection();
                   }catch (AsuraRabbitMqException e){
                       //防御性容错
                   }
                    if (connection != null && connection.isOpen()){
                        logger.info("retry recovery connection success,close scheduletask");
                        sendFuture.cancel(true);
                    }
                }
            }, 5000, 30000, TimeUnit.MILLISECONDS);
        }

    }


    /**
     * 获取通道
     *
     * @param connection
     * @return
     * @throws IOException
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:36:40
     */
    public Channel getChannel(Connection connection) throws IOException {
        return connection.createChannel();
    }

    /**
     * 关闭连接、关闭通道
     *
     * @param channel
     * @throws IOException
     * @throws TimeoutException
     * @author zhangshaobin
     * @created 2016年3月1日 下午4:36:57
     */
    public void closeChannel(Channel channel) throws IOException, TimeoutException {
        if (channel != null) {
            channel.close();
        }

    }

    /**
     * @param
     * @throws IOException
     */
    public void closeConnection() throws IOException {
        if (connection != null) {
            //	System.out.println("mq connection factory close connection");
            connection.close();
        }
    }
}
