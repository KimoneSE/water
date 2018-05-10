package com.water.util;

import java.util.Date;

/**
 * Created by Kimone.
 */
public class TimeTask {
    public void execute(){
        Singleton singleton = Singleton.getInstance();
        String accessToken = null;
        String jsapi_ticket = null;
        try {
            accessToken = WeChatUtil.getAccessToken();
            jsapi_ticket = WeChatUtil.getJsApiTicket(accessToken);
            singleton.setAccess_token(accessToken);
            singleton.setJsApiTicket(jsapi_ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("***access:"+singleton.getAccess_token());
        System.out.println("***jsapi:"+singleton.getJsApiTicket());
        System.out.println(new Date());
    }
}
