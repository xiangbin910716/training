package com.bin.xiang.dubbo.activate;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * <p>https://www.jianshu.com/p/bc523348f519</p>
 * <p>从上面的几个测试用例，可以得到下面的结论：
 * 1. 根据loader.getActivateExtension中的group和搜索到此类型的实例进行比较，
 * 如果group能匹配到，就是我们选择的，也就是在此条件下需要激活的。
 * 2. @Activate中的value是参数是第二层过滤参数（第一层是通过group），
 * 在group校验通过的前提下，如果URL中的参数（k）与值（v）中的参数名同@Activate中的value值一致或者包含，
 * 那么才会被选中。相当于加入了value后，条件更为苛刻点，需要URL中有此参数并且，参数必须有值。
 * 3.@Activate的order参数对于同一个类型的多个扩展来说，order值越小，优先级越高。
 *
 * getActivateExtension方法主要获取当前扩展的所有可自动激活的实现

 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangb
 * @version 1.0
 * @Date Created in 2019年03月12日 17:44
 * @since 1.0
 */
@SPI
public interface ActivateExt {
    String echo(String msg);
}
