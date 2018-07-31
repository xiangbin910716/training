package com.bin.xiang.design.pattern.reactor;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月23日 15:15
 * @since 1.0
 */
public class SocketReadHandler implements Runnable {

    private SocketChannel socketChannel;

    public SocketReadHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        SelectionKey selectionKey = socketChannel.register(selector, 0);
        //将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。
        //参看dispatch(SelectionKey key)
        selectionKey.attach(this);
        //同时将SelectionKey标记为可读，以便读取。
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    @Override
    public void run() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        try {
            socketChannel.read(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
