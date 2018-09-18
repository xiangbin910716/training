package com.bin.xiang.spring.aop;

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
 * @Date Created in 2018年08月28日 14:23
 * @since 1.0
 */
public class HelloImplB implements Hello {

    @Override
    public void print() {
        System.out.println("B print Hello ....");
    }
}
