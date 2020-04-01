package com.bin.xiang.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 *
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
public class URLConnectionHelper {

    public static String sendRequest(String urlParam){
        URLConnection connection;
        BufferedReader buffer;
        StringBuffer resultBuffer;

        try{
            URL url = new URL(urlParam);
            connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            InputStream inputStream = connection.getInputStream();
            resultBuffer = new StringBuffer();
            String line;
            buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            while((line = buffer.readLine()) != null){
                resultBuffer.append(line);
            }
            return resultBuffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        String url ="https://www.baidu.com";
        System.out.println(sendRequest(url));
    }
}
