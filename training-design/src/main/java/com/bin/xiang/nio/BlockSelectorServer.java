package com.bin.xiang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * <p>NIO阻塞形态</p>
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
public class BlockSelectorServer {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        FileChannel outChannel = null;

        try {
            // 1.获取通道
            serverSocketChannel = ServerSocketChannel.open();
            // 2.得到文件通道，将客户端传递过来的图片写到本地项目下(写模式、没有则创建)
            outChannel = FileChannel.open(Paths.get("D:\\Users\\2个亿.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);
            // 3. 绑定链接
            serverSocketChannel.bind(new InetSocketAddress(6666));
            // 4. 获取客户端的连接(阻塞的)
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 5. 要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的呢
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(socketChannel.read(buffer) != -1){
                //在读之前都要切换成读模式
                buffer.flip();
                outChannel.write(buffer);
                // 读完切换成写模式，能让管道继续读取文件的数据
                buffer.clear();
            }

            buffer.put("img is received".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

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
        }
    }
}
