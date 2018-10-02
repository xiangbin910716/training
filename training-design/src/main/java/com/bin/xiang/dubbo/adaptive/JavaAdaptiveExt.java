package com.bin.xiang.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;

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
 * @Date Created in 2018年09月23日 15:34
 * @since 1.0
 */
public class JavaAdaptiveExt implements AdaptiveExt {
    @Override
    public String echo(String msg, URL url) {
        return "java echo";
    }
}
