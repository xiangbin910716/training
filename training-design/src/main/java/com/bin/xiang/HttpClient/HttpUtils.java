package com.bin.xiang.HttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

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
 * @Date Created in 2018年12月20日 16:34
 * @since 1.0
 */
public class HttpUtils {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            URI uri = new URIBuilder().setScheme("http")
                    .setHost("zyuapi.q.ziroom.com")
//                    .setPort(8080)
                    .setPath("/vr/houseinfo")
                    .setParameter("shellHouseId","1115030517682")
                    .build();
            HttpGet httpGet = new HttpGet(uri);
            String result = httpClient.execute(httpGet, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    String res = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                    return res;
                }
            });
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
