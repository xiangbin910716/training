package com.bin.xiang.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

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
 * @Date Created in 2018年09月23日 15:39
 * @since 1.0
 */
public class AdaptiveExtTest {
    //spi 排序规则  @SPI 《 URL 《 t 《 @Adaptive
    public static void main(String[] args) {
        ExtensionLoader<AdaptiveExt> extensionLoader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt adaptiveExt = extensionLoader.getAdaptiveExtension();
        // 1在接口的spi注解上写了dubbo，输出dubbo echo  测试一：SPI注解中有value值
        //URL url = URL.valueOf("test/localhost/test?adaptive.ext=java");
        //2在接口的spi注解上写了dubbo,url增加了类名=java，输出java echo  测试二：SPI注解中有value值，URL中也有具体的值
        //URL url = URL.valueOf("test/localhost/test?adaptive.ext=java");
        //3.测试三：SPI注解中有value值，URL中也有具体的值,springAdaptiveExt实现类上有@Adaptive注解 输出spring echo
        //URL url = URL.valueOf("test/localhost/test?adaptive.ext=java");
        //4.SPI注解中有value值,实现类上没有@Adaptive注解，在方法上打上@Adaptive注解，注解中的value与链接中的参数的key一致，链接中的key对应的value就是spi中的name,获取相应的实现类。
        URL url = URL.valueOf("test/localhost/test?t=java");
        System.out.println(adaptiveExt.echo("d",url));

    }
}
