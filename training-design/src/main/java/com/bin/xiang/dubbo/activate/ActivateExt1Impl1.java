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
 * @Date Created in 2019年03月12日 17:46
 * @since 1.0
 */
@Activate(group = {"default_group"})
public class ActivateExt1Impl1 implements ActivateExt {

    @Override
    public String echo(String msg) {
        System.out.println("ActivateExt1Impl1 is print....");
        return msg;
    }
}
