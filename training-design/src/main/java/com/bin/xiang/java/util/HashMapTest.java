package com.bin.xiang.java.util;

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
 * @Date Created in 2018年07月20日 09:34
 * @since 1.0
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        int n=5;
        System.out.println(n >>> 1);
        n |= n >>> 1;
        System.out.println(n);
        n = 5;
        n = n|n>>>1;
        System.out.println(n);
    }
}
