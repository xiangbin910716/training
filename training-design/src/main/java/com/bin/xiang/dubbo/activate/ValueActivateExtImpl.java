package com.bin.xiang.dubbo.activate;

import com.alibaba.dubbo.common.extension.Activate;

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
 * @Date Created in 2019年03月12日 17:52
 * @since 1.0
 */
@Activate(value = {"value1"}, group = {"value"})
public class ValueActivateExtImpl implements ActivateExt {
    @Override
    public String echo(String msg) {
        System.out.println("ValueActivateExtImpl is print...");
        return msg;
    }
}
