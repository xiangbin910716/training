package com.bin.xiang.reflex;

/**
 * <p>
 *
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @since 1.0
 */
public class Test {

    private int age;
    private String name;
    private String desc;

    public Test(){}

    public Test(int age, String name){
        this.age = age;
        this.name = name;
        System.out.println("My Name is " + name + " age is " + age);
    }

    private Test(String name){
        this.name = name;
        System.out.println("My Name is " + name);
    }

    public void welcome(String tipes){
        System.out.println("Welcome " + tipes );
    }

}
