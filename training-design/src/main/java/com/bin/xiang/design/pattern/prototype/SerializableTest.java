package com.bin.xiang.design.pattern.prototype;

import java.io.*;

/**
 * <p></p>
 * <p> 实现Serializable接口，实现深拷贝
 * <p> 实现Cloneable接口，实现浅拷贝
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月31日 11:21
 * @since 1.0
 */
public class SerializableTest implements Serializable {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public SerializableTest cloneTest(){
        SerializableTest B = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            B = (SerializableTest) ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //close stream;
        }
        return B;
    }

    public static void main(String[] args) {
        SerializableTest A = new SerializableTest();
        A.setName("zhangsan");
        A.setAge("15");
        SerializableTest B = A.cloneTest();
        System.out.println(B.equals(A));
        System.out.println(B.getName().equals(A.getName()));
    }
}
