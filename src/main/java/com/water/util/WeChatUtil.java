package com.water.util;

import com.water.config.Config;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kimone.
 */
public class WeChatUtil {

//    private static String appID = "wx680e86c4068403dc";
//    private static String appsecret = "8a13754af11c26d539e1a0b83d2078bd";

//    public static void main(String[] args) throws Exception{
//        System.out.println(getAccessToken());
//        System.out.println(getJsApiTicket(getAccessToken()));
//    };

    private static Logger logger = Logger.getLogger(WeChatUtil.class);

    public static String getAccessToken() throws Exception{
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ Config.appID+"&secret="+Config.appSecret;
        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");
        logger.error("**accessToken**"+message);
        //获取access_token
        JSONObject jsonObject = new JSONObject(message);
        return jsonObject.getString("access_token");
    }

    /**
     * 根据accessToken获取jsapi_ticket
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static String getJsApiTicket(String accessToken) throws Exception{
        String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
        URL url = new URL(ticketUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs,"UTF-8");
        logger.error("**ticket**"+message);

        JSONObject jsonObject = new JSONObject(message);
        return jsonObject.getString("ticket");
    }
}
