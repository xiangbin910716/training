package com.bin.xiang;

import java.util.*;

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
 * @Date Created in 2018年07月16日 15:54
 * @since 1.0
 */
public class ListTest {

    public static void main(String[] args) {
        String[] staffs = new String[]{"Tom", "Bob", "Jane"};
        String[] staffs1 = new String[]{"Tom1", "Bob1", "Jane1"};
        List<String> list = Arrays.asList(staffs);
        List<String> list1 = Arrays.asList(staffs1);
        Set<String> ss = new HashSet<>();
        list = new ArrayList<>(list);
        list.addAll(list1);
        list = list.subList(0,6);
        for(String s : list){
            System.out.println(s);
        }

    }
}
