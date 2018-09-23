package com.bin.xiang.spring.schema;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p></p>
 * <p>  @Link {https://www.cnblogs.com/eric-lin/p/4968985.html}
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年09月23日 11:13
 * @since 1.0
 */
public class TestSpringSchema {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User)context.getBean("eric");
        System.out.println(user);
    }
}
