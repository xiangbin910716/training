package com.bin.xiang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * <p>NIO非阻塞形态</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangb
 * @version 1.0
 * @Date Created in 2019年02月26日 15:47
 * @since 1.0
 */
public class NOBlockServer {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        FileChannel outChannel = null;
        Selector selector = null;

        try {
            // 1.获取通道
            serverSocketChannel = ServerSocketChannel.open();
            // 2.得到文件通道，将客户端传递过来的图片写到本地项目下(写模式、没有则创建)
            outChannel = FileChannel.open(Paths.get("D:\\Users\\2个亿.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);
            // 2.切换成非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 3. 绑定链接
            serverSocketChannel.bind(new InetSocketAddress(6666));
            // 4. 获取选择器
            selector = Selector.open();
            // 4.1将通道注册到选择器上，指定接收“监听通道”事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 5. 轮训地获取选择器上已“就绪”的事件--->只要select()>0，说明已就绪
            while(selector.select() > 0){
                // 6. 获取当前选择器所有注册的“选择键”(已就绪的监听事件)
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                // 7. 获取已“就绪”的事件，(不同的事件做不同的事)
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    // 接收事件就绪
                    if(selectionKey.isAcceptable()){
                        // 8. 获取客户端的链接
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 8.1 切换成非阻塞状态
                        socketChannel.configureBlocking(false);
                        // 8.2 注册到选择器上-->拿到客户端的连接为了读取通道的数据(监听读就绪事件)
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()){
                        // 读事件就绪
                        // 9. 获取当前选择器读就绪状态的通道
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while(client.read(buffer) > 0){
                            buffer.flip();
                            outChannel.write(buffer);
                            buffer.clear();
                        }
                        //服务端保存图片后，告诉客户端已经收到图片了
                        buffer.put("pic is receive by nio".getBytes());
                        buffer.flip();
                        client.write(buffer);
                        buffer.clear();

                    }
                    // 10. 取消选择键(已经处理过的事件，就应该取消掉了)
                    iterator.remove();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
