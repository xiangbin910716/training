package com.bin.xiang.dubbo.activate;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

import java.security.cert.Extension;
import java.util.List;

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
 * @Date Created in 2019年03月12日 18:11
 * @since 1.0
 */
public class ActivateExtTest {

    public static void main(String[] args) {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(ActivateExt.class);
        URL url = URL.valueOf("test://localhost/test");
        //根据   key = value1,group =  value
        //@Activate(value = {"value1"}, group = {"value"})来激活扩展
//        url = url.addParameter("value1", "value");
//        List<ActivateExt> activateExts = extensionLoader.getActivateExtension(url,new String[]{},"default_group");
        List<ActivateExt> activateExts = extensionLoader.getActivateExtension(url,new String[]{},"order");
        System.out.println(activateExts.size());
        System.out.println(activateExts.get(0).getClass());
    }
}
