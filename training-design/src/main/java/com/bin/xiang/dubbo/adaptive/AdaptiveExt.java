package com.bin.xiang.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * <p>通过getAdaptiveExtensionClass获取到SPI扩展对象Class的实例，然后通过反射方法newInstance()创建这个对象</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年09月23日 15:32
 * @since 1.0
 */
@SPI("dubbo")
public interface AdaptiveExt {
    @Adaptive
//    @Adaptive({"t"})
    String echo(String msg, URL url);
}
