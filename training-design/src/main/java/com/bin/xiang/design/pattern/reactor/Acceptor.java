package com.bin.xiang.design.pattern.reactor;

import java.io.IOException;
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
 * @Date Created in 2018年07月23日 14:56
 * @since 1.0
 */
public class Acceptor implements Runnable {
    private Reactor reactor;

    public Acceptor(Reactor reactor) {
        this.reactor = reactor;
    }


    @Override
    public void run() {
        SocketChannel socketChannel = null;
        try {
            socketChannel = reactor.serverSocketChannel.accept();
            if (socketChannel != null) {
                new SocketReadHandler(reactor.selector, socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
