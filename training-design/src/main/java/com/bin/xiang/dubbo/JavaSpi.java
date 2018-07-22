package com.bin.xiang.dubbo;

import java.util.Iterator;
import java.util.ServiceLoader;

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
 * @Date Created in 2018年07月22日 14:28
 * @since 1.0
 */
public class JavaSpi {

    public static void main(String[] args) {
        ServiceLoader ss = ServiceLoader.load(HelloInterface.class);
        Iterator iterators = ss.iterator();
        while(iterators.hasNext()){
            HelloInterface helloInterface = (HelloInterface) iterators.next();
            helloInterface.sayHello();
        }
    }
}
