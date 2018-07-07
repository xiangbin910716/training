package com.bin.xiang;

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
 * @Date Created in 2018年07月07日 17:06
 * @since 1.0
 */
public class Singleton {

    private Singleton() {};

    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
