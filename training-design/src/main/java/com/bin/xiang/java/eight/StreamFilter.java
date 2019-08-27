package com.bin.xiang.java.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangb
 * @version 1.0
 * @Date Created in 2019年03月18日 19:38
 * @since 1.0
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();
        Student student0 = Student.builder().age(20).name("zhangsan1").email("1234").build();
        Student student1 = Student.builder().age(20).name("zhangsan2").email("1234").build();
        Student student2 = Student.builder().age(20).name("zhangsan").email("1234").build();
        Student student3 = Student.builder().age(19).name("zhangsan1").email("123").build();
        Student student4 = Student.builder().age(18).name("zhangsan").email("123").build();
        Student student5 = Student.builder().age(17).name("zhangsan").email("123").build();
        Student student6 = Student.builder().age(16).name("zhangsan").email("123").build();
        Student student7 = Student.builder().age(20).name("zhangsan").email("123").build();
        students.add(student0);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students = students.stream()
                .filter(v -> v.getAge() == 20)
                .filter(v -> v.getEmail().equals("123"))
                .filter(v -> v.getName().equals("zhangsan"))
                .collect(Collectors.toList());
        students.forEach(v ->{
            System.out.println(v);
        });

    }

}
