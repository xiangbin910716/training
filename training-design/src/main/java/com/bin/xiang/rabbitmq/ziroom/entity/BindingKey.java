/**
 * @FileName: BindKey.java
 * @Package: com.asura.framework.rabbitmq.entity
 * @author sence
 * @created 3/12/2016 11:15 AM
 * <p/>
 * Copyright 2015 ziroom
 */
package com.bin.xiang.rabbitmq.ziroom.entity;

import com.bin.xiang.rabbitmq.ziroom.exception.AsuraRabbitMqException;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sence
 * @version 1.0
 * @since 1.0
 */
public class BindingKey extends NameKey {

    public BindingKey() {

    }

    public BindingKey(String system, String module, String function) {
        super(system, module, function);
    }


    /**
     * 获取到routingKey
     */
    public String getKey() throws AsuraRabbitMqException {
        if (this.getSystem() == null || "".equals(this.getSystem())) {
            this.setSystem("*");
        }
        if (this.getModule() == null || "".equals(this.getModule())) {
            this.setModule("*");
        }
        if (this.getFunction() == null || "".equals(this.getFunction())) {
            this.setFunction("*");
        }
        return getSystem() + "." + getModule() + "." + getFunction();
    }

}
