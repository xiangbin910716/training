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
 * @Date Created in 2019年03月12日 17:48
 * @since 1.0
 */
@Activate(group = {"group1", "group2"})
public class GroupActivateExtImpl implements ActivateExt {

    @Override
    public String echo(String msg) {
        System.out.println("GroupActivateExtImpl is print...");
        return msg;
    }
}
