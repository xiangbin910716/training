package com.bin.xiang.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
 * @Date Created in 2018年08月28日 14:31
 * @since 1.0
 */
public class AopTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        Hello helloA = (Hello) ctx.getBean("helloWorldImplA");
        Hello helloB = (Hello) ctx.getBean("helloWorldImplB");

        helloA.print();
        helloB.print();


    }
}
