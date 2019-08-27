package com.bin.xiang.netty;

import io.netty.channel.*;
import io.netty.util.concurrent.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
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
 * @Date Created in 2019年03月13日 09:15
 * @since 1.0
 */
public class EventLoopTest {

    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop() {
            @Override
            public EventLoopGroup parent() {
                return null;
            }

            @Override
            public EventLoop next() {
                return null;
            }

            @Override
            public ChannelFuture register(Channel channel) {
                return null;
            }

            @Override
            public ChannelFuture register(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture register(Channel channel, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public boolean inEventLoop() {
                return false;
            }

            @Override
            public boolean inEventLoop(Thread thread) {
                return false;
            }

            @Override
            public <V> Promise<V> newPromise() {
                return null;
            }

            @Override
            public <V> ProgressivePromise<V> newProgressivePromise() {
                return null;
            }

            @Override
            public <V> Future<V> newSucceededFuture(V v) {
                return null;
            }

            @Override
            public <V> Future<V> newFailedFuture(Throwable throwable) {
                return null;
            }

            @Override
            public boolean isShuttingDown() {
                return false;
            }

            @Override
            public Future<?> shutdownGracefully() {
                return null;
            }

            @Override
            public Future<?> shutdownGracefully(long l, long l1, TimeUnit timeUnit) {
                return null;
            }

            @Override
            public Future<?> terminationFuture() {
                return null;
            }

            @Override
            public void shutdown() {

            }

            @Override
            public List<Runnable> shutdownNow() {
                return null;
            }

            @Override
            public Iterator<EventExecutor> iterator() {
                return null;
            }

            @Override
            public Future<?> submit(Runnable runnable) {
                return null;
            }

            @Override
            public <T> Future<T> submit(Runnable runnable, T t) {
                return null;
            }

            @Override
            public <T> Future<T> submit(Callable<T> callable) {
                return null;
            }

            @Override
            public ScheduledFuture<?> schedule(Runnable runnable, long l, TimeUnit timeUnit) {
                return null;
            }

            @Override
            public <V> ScheduledFuture<V> schedule(Callable<V> callable, long l, TimeUnit timeUnit) {
                return null;
            }

            @Override
            public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeUnit) {
                return null;
            }

            @Override
            public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeUnit) {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isTerminated() {
                return false;
            }

            @Override
            public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
                return null;
            }

            @Override
            public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }

            @Override
            public void execute(Runnable command) {

            }
        };
        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("============");
            }
        },5,TimeUnit.SECONDS);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("22222222222");
        eventLoop.shutdownGracefully();
    }

}
