package com.bin.xiang.dubbo;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p></p>
 * <p> @link {https://blog.csdn.net/sadfishsc/article/details/9999169}
 *  @link https://blog.csdn.net/u012410733/article/details/77787194
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年09月23日 16:43
 * @since 1.0
 */
public class javassistTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException,
            ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //创建类
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.bin.xiang.dubbo.User");
        //添加私有成员name及其getter，setter方法
        CtField param = new CtField(classPool.get("java.lang.String"),"name",ctClass);
        param.setModifiers(Modifier.PRIVATE);
        ctClass.addMethod(CtNewMethod.setter("setName",param));
        ctClass.addMethod(CtNewMethod.getter("getName",param));
        ctClass.addField(param, CtField.Initializer.constant(""));
        //添加无参构造体
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {} , ctClass);
        ctConstructor.setBody("{name = \"Brant\";}");
        ctClass.addConstructor(ctConstructor);
        //添加有参构造体
        ctConstructor = new CtConstructor(new CtClass[]{classPool.get("java.lang.String")},ctClass);
        ctConstructor.setBody("{$0.name = $1;}");
        ctClass.addConstructor(ctConstructor);
        //打印创建类的类名
        System.out.println(ctClass.toClass());
        //通过反射创建无参的实例，并调用getName方法
        Object o = Class.forName("com.bin.xiang.dubbo.User").newInstance();
        Method getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));


        // 调用其setName方法
        Method setter = o.getClass().getMethod("setName", new Class[] {String.class});
        setter.invoke(o, "Adam");
        System.out.println(getter.invoke(o));

        // 通过反射创建有参的实例，并调用getName方法
        o = Class.forName("com.bin.xiang.dubbo.User").getConstructor(String.class).newInstance("Liu Jian");
        getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));

    }


}
