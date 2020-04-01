package com.bin.xiang.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
public class HttpURLConnectionHelper {

    public static String sendRequest(String urlParam,String requestType){
        HttpURLConnection urlConnection;
        BufferedReader buffer;
        StringBuffer resultBuffer;
        try{
            URL url = new URL(urlParam);
            //得到连接对象
            urlConnection = (HttpURLConnection) url.openConnection();
            //设置请求类型
            urlConnection.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=GBK");
            //允许写出
            urlConnection.setDoOutput(true);
            //允许写入
            urlConnection.setDoInput(true);
            //不使用缓存
            urlConnection.setUseCaches(false);
            //得到响应码
            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = urlConnection.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                while ((line = buffer.readLine()) != null){
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String url = "https://www.baidu.com/";
        System.out.println(sendRequest(url,"GET"));
    }
}
