package com.bin.xiang.reflex;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

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
public class ReflexDemo {

    public static void main5(String[] args) {
        try {
            Test test = new Test();
            Class c = test.getClass();
//            Constructor[] declaredConstructors = c.getConstructors();//获取所有public构造方法
            Class[] p = {int.class, String.class};

            Constructor[] declaredConstructors = c.getDeclaredConstructors();
            for (Constructor declaredConstructor : declaredConstructors) {
                System.out.print(Modifier.toString(declaredConstructor.getModifiers()) + "参数类型：");
                Class[] parameterTypes = declaredConstructor.getParameterTypes();

                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getName());
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main1(String[] args) {
        String a = "com.bin.xiang.reflex.Test";
        try {
            Class clazz = Class.forName(a);
            System.out.println(clazz.toGenericString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main3(String[] args) {
        try {
            Test test = new Test();
            Class c = test.getClass();
            Class[] p = {int.class, String.class};

            Constructor declaredConstructor = c.getDeclaredConstructor(p);
            System.out.print(Modifier.toString(declaredConstructor.getModifiers()) + "参数类型：");
            Class[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.print(parameterType.getName());
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main6(String[] args) {
        try {
            Test test = new Test();
            Class c = test.getClass();
            Class[] p = {int.class, String.class};
            Class[] p1 = {String.class};

            Constructor declaredConstructor = c.getDeclaredConstructor(p);
            Constructor declaredConstructor1 = c.getDeclaredConstructor(p1);
            declaredConstructor.newInstance(18,"小明");
            declaredConstructor1.setAccessible(true);
            declaredConstructor1.newInstance("大明");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main7(String[] args) {
        try {
//            Test test = new Test();
            Class c = Class.forName("com.bin.xiang.reflex.Test");
            Class[] p1 = {String.class};
            Constructor declaredConstructor1 = c.getDeclaredConstructor(p1);
            declaredConstructor1.setAccessible(true);
            Object a = declaredConstructor1.newInstance("大明");


            String[] params = {"男的"};
            Method method = c.getDeclaredMethod("welcome", String.class);
            method.setAccessible(true);
            method.invoke(a, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> aa = null;
        for (String s : aa) {
            System.out.println(s);
        }
    }

}
