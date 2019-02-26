package com.bin.xiang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
public class BlockSelectorClient {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            //1.获取通道
            socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 6666));
            //2.发送一张图片给服务端
            fileChannel = FileChannel.open(Paths.get("D:\\Users\\1个亿.jpg"), StandardOpenOption.READ);
            // 3.要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的呢
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 4.读取本地文件(图片)，发送到服务器
            while (fileChannel.read(buffer) != -1) {
                // 在读之前都要切换成读模式
                buffer.flip();
                socketChannel.write(buffer);
                // 读完切换成写模式，能让管道继续读取文件的数据
                buffer.clear();
            }
            //告诉服务器已经写完了
            socketChannel.shutdownOutput();
            //知道服务端要返回响应的数据给客户端，客户端在这里接收
            int len = 0;
            while ((len = socketChannel.read(buffer)) != -1){
                buffer.flip();
                System.out.println(new String(buffer.array(),0,len));
                buffer.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //fileChannel要在socketChannel之后关闭？？？
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
