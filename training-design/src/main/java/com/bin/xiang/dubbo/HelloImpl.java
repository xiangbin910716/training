package com.bin.xiang.dubbo;

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
 * @Date Created in 2018年07月22日 14:29
 * @since 1.0
 */
public class HelloImpl implements  HelloInterface{

    @Override
    public void sayHello() {
        System.out.println("Hello Impl say .....");
    }
}
