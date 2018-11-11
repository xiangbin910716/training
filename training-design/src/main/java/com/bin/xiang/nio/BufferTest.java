package com.bin.xiang.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
 * @Date Created in 2018年10月03日 18:13
 * @since 1.0
 */
@Slf4j
public class BufferTest {
    /**  
     * @Decriptioin  
     * 
     * @Author xiangb
     * @Date 2018/10/3 18:51
     * @Param [args]
     * @Return void
     */  
    public static void main(String[] args) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("D:\\123.txt", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int byteRead = fileChannel.read(byteBuffer);

            while(byteRead != -1){
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.println((char)byteBuffer.get());
                }
                byteBuffer.clear();
                byteRead = fileChannel.read(byteBuffer);
            }
            randomAccessFile.close();
        } catch (Exception e) {
            log.error("0.0.0.0");
        } finally {
            if(randomAccessFile != null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
