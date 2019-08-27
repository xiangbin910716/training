package com.bin.xiang.java.util;

import java.util.HashMap;
import java.util.Map;

public class NullTest {

    public static void main(String[] args) {
//        Integer i = null;//任何含有null值的包装类在Java拆箱生成基本数据类型时候都会抛出一个空指针异常
//        int j = i;
//        System.out.println(j);


//        Map<Integer, Integer> numberAndCount = new HashMap<>();
//        int[] numbers = {3, 5, 7,9, 11, 13, 17, 19, 2, 3, 5, 33, 12, 5};
//
//        for(int i : numbers) {
//            int count = numberAndCount.get(i);// NullPointerException here
//            numberAndCount.put(i, count++);
//        }


//        Integer iAmNull = null;
//        if(iAmNull instanceof Integer){
//            System.out.println("iAmNull is instance of Integer");
//
//        }else{//输出iAmNull is NOT an instance of Integer
//            System.out.println("iAmNull is NOT an instance of Integer");
//        }

        //你可能知道不能调用非静态方法来使用一个值为null的引用类型变量。它将会抛出空指针异常，但是你可能不知道，你可以使用静态方法来使用一个值为null的引用类型变量。因为静态方法使用静态绑定，不会抛出空指针异常
//        NullTest myObject = null;
//        myObject.iAmStaticMethod();
//        myObject.iAmNonStaticMethod();
//    }
//    private static void iAmStaticMethod() {
//        System.out.println("I am static method, can be called by null reference");
//    }
//
//    private void iAmNonStaticMethod() {
//        System.out.println("I am NON static method, don't date to call me by null");
//    }


        //你可以使用==或者!=操作来比较null值，但是不能使用其他算法或者逻辑操作，例如小于或者大于。跟SQL不一样，在Java中null==null将返回true
        String abc = null;
        String cde = null;

        if (abc == cde) {
            System.out.println("null == null is true in Java");
        }

        if (null != null) {
            System.out.println("null != null is false in Java");
        }

        // classical null check
        if (abc == null) {
            // do something
        }

        // not ok, compile time error
//        if (abc > null) {
//
//        }
    }

}
