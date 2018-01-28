package com.water.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.water.config.Config;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kimone.
 */
public class WeatherUtil {
    private static String WEATHER_URL = "https://api.seniverse.com/v3/weather/now.json";

    /**
     * Generate HmacSHA1 signature with given data string and key
     * @param data
     * @param key
     * @return
     * @throws SignatureException
     */
    private static String generateSignature(String data, String key) throws SignatureException {
        String result;
        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
            result = new sun.misc.BASE64Encoder().encode(rawHmac);
        }
        catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    /**
     * Generate the URL to get diary weather
     * @param location
     * @param language
     * @param unit
     * @return
     */
    private static String generateGetDiaryWeatherURL(
            String location,
            String language,
            String unit
    )  throws SignatureException, UnsupportedEncodingException {
        String timestamp = String.valueOf(new Date().getTime());
        String params = "ts=" + timestamp + "&ttl=30&uid=" + Config.TIANQI_API_USER_ID;
        String signature = URLEncoder.encode(generateSignature(params, Config.TIANQI_API_SECRET_KEY), "UTF-8");
        return WEATHER_URL + "?" + params + "&sig=" + signature + "&location=" + location + "&language=" + language + "&unit=" + unit;
    }

    public static Map<String, String> getWeather(String location) throws Exception {
        String url = generateGetDiaryWeatherURL(location, "zh-Hans", "c");
        URL weatherURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)weatherURL.openConnection();
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
        JSONObject jsonObject = JSON.parseObject(message);
        JSONObject results = (JSONObject) jsonObject.getJSONArray("results").get(0);
        JSONObject now = results.getJSONObject("now");
        Map<String, String> map = new HashMap<>();
        map.put("temperature", now.getString("temperature"));
        map.put("weather", now.getString("text"));
        return map;
    }
}
