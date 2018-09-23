package com.bin.xiang.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 * @Link {https://www.cnblogs.com/eric-lin/p/4968985.html}
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年09月23日 10:57
 * @since 1.0
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());

    }
}
