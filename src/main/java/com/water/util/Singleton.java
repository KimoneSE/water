package com.water.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kimone.
 */
public class Singleton {

    private static Singleton singleton = null;

    private String access_token;

    private String jsApiTicket;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public static Singleton getSingleton() {
        return singleton;
    }

    public static void setSingleton(Singleton singleton) {
        Singleton.singleton = singleton;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getJsApiTicket() {
        return jsApiTicket;
    }

    public void setJsApiTicket(String jsApiTicket) {
        this.jsApiTicket = jsApiTicket;
    }

}
