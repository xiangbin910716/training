package com.bin.xiang.Thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.HashMap;

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
 * @Date Created in 2018年07月17日 09:14
 * @since 1.0
 */
public class PipedTesst {

    public static void main(String[] args) {
        PipedWriter pw = new PipedWriter();
        PipedReader pr = new PipedReader();
        try {
            pw.connect(pr);
            Thread printThread = new Thread(new Print(pr),"printThread");
            printThread.start();
            int receive = 0;
            try{
                while((receive = System.in.read()) != -1){
                    pw.write(receive);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Print implements Runnable {
        private PipedReader pw;
        public Print(PipedReader pw) {
            this.pw = pw;
        }
        @Override
        public void run(){
            int receive = 0;
            try {
                while ((receive = pw.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
