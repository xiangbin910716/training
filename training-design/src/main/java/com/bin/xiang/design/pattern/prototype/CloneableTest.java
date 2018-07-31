package com.bin.xiang.design.pattern.prototype;

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
 * @Date Created in 2018年07月31日 11:03
 * @since 1.0
 */
public class CloneableTest {

    static class CloneableA implements Cloneable {
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

        public CloneableA(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public CloneableA cloneA() {
            try {
                return (CloneableA) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public static void main(String[] args) {
        CloneableA A = new CloneableTest.CloneableA("nihao","15");
        CloneableA B = A.cloneA();
        System.out.println(A.equals(B));
        System.out.println(A.getName().equals(B.getName()));
    }





}
