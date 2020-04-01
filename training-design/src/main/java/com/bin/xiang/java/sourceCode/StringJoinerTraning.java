package com.bin.xiang.java.sourceCode;

import java.util.StringJoiner;

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
public class StringJoinerTraning {

//    public static void main(String[] args) {
////        StringJoiner sj = new StringJoiner("-","<",">");
//        StringJoiner sj = new StringJoiner("-");
////        System.out.println(sj.length());
////        System.out.println(sj.toString());
////        sj.add("ni");
////        sj.add("hao");
//        sj.setEmptyValue("nihao");
//        sj.add("");//只要这里设置了值，setEmptyValue就会失效
//        System.out.println(sj.length());
//        System.out.println(sj.toString());
//        System.out.println(sj.toString().length());
//    }
public static void main(String[] args) {
    StringJoiner sj = new StringJoiner("-","<",">");
    sj.add("ni");
    sj.add("hao");

    StringJoiner sj2 = new StringJoiner("_","o","c");
    sj2.add("ni");
    sj2.add("hao");

    sj.merge(sj2);

    System.out.println(sj.toString());
    System.out.println(sj2.toString());

    StringBuilder builder = new StringBuilder("ni_hao-");
    String other = "<nice-to-meet>";
    builder.append(other, 1, 13);
    System.out.println(builder.toString());
    other = other.substring(1,13);
    System.out.println(other);

}
}
