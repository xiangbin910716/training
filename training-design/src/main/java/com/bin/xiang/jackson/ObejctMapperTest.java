package com.bin.xiang.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * @See https://blog.csdn.net/wangmx1993328/article/details/88598625
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @since 1.0
 */
public class ObejctMapperTest {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue("", String.class);
    }
}
