package com.bin.xiang.dubbo;

import com.sun.org.apache.xpath.internal.SourceTree;
import javassist.*;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * <p></p>
 * <p> @Link https://www.jianshu.com/p/cec3e3e0627f
 * <P> @Link https://blog.csdn.net/mingxin95/article/details/51810499</P>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangb
 * @version 1.0
 * @Date Created in 2018年10月02日 11:25
 * @since 1.0
 */
public class JavassistProxyFactory {

    public static void main(String[] args) throws Exception {
        testJavassistDefineClass();
        testJavassistFactoryProxy();
        RayTest rayTest = (RayTest) testJavassistFactoryProxy2(RayTest.class);
        rayTest.exe();
    }

    private static void testJavassistFactoryProxy() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.setSuperclass(RayTest.class);

        Class<ProxyObject> proxyClass = proxyFactory.createClass();

        RayTest proxyObject = (RayTest) proxyClass.newInstance();

        ((ProxyObject) proxyObject).setHandler(new MethodHandler() {
            RayTest rayTest = new RayTest();

            @Override
            public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
                String before = "before";
                Object str2 = method1.invoke(o,objects);
                Object str = method.invoke(rayTest, objects);
                String after = "after";
                return before + str + str2 + after;
            }
        });

        String exe =proxyObject.exe();
        System.out.println(exe);


    }

    /**
     * @Decriptioin
     * @Author xiangb
     * @Date 2018/10/2 11:29
     * @Param []
     * @Return void
     */
    private static void testJavassistDefineClass() throws Exception {
        ClassPool classPool = new ClassPool(true);
        String className = RayTest.class.getName();

        CtClass ctClass = classPool.makeClass(className + "JavassistProxy");

        ctClass.setSuperclass(classPool.get(RayTest.class.getName()));

        ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));

        ctClass.addField(CtField.make("public " + className + " real = new " + className + "();", ctClass));

        ctClass.addMethod(CtNewMethod.make("public String exe() { return \"before\" +real.exe() + \"after\";}", ctClass));

        Class<RayTest> testClass = ctClass.toClass();

        RayTest rayTest = testClass.newInstance();

        String exe = rayTest.exe();

        System.out.println(exe);
        System.out.println(rayTest.getClass().getName());
    }
    @SuppressWarnings("deprecation")
    private static Object testJavassistFactoryProxy2(Class clazz) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.setSuperclass(clazz);

        proxyFactory.setHandler(new MethodHandler() {
            /*
             * self为由Javassist动态生成的代理类实例，
             *  thismethod为 当前要调用的方法
             *  proceed 为生成的代理类对方法的代理引用。
             *  Object[]为参数值列表，
             * 返回：从代理实例的方法调用返回的值。
             *
             * 其中，proceed.invoke(self, args);
             *
             * 调用代理类实例上的代理方法的父类方法（即实体类ConcreteClassNoInterface中对应的方法）
             */
            @Override
            public Object invoke(Object self, Method thismethod, Method proceed, Object[] args) throws Throwable {
                System.out.println("--------------------------------");
                System.out.println(self.getClass());
                //class com.javassist.demo.A_$$_javassist_0
                System.out.println("代理类对方法的代理引用:"+thismethod.getName());
                System.out.println("开启事务 -------");

                Object result = proceed.invoke(self, args);

                System.out.println("提交事务 -------");
                return result;
            }
        });
        return proxyFactory.createClass().newInstance();

    }
}
