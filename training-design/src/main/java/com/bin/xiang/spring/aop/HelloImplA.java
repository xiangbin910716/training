package com.bin.xiang.spring.aop;

import com.bin.xiang.dubbo.HelloInterface;

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
 * @Date Created in 2018年08月28日 14:22
 * @since 1.0
 */
public class HelloImplA implements Hello {

    @Override
    public void print() {
        System.out.println("A print Hello....");
    }
}
