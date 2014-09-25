package com.bbkmobile.iqoo.common;

public class Constants {

    private final static String COMMON_URL = "http://219.130.55.42:8400";
    public final static String GETINFO_URL = COMMON_URL
            + "/v3/web/login/getUserInfo";
    public final static String GETACCESSTOKEN_URL = COMMON_URL
            + "/v3/web/login/getAccessToken";
    public final static String LOGIN_URL = COMMON_URL
            + "/v3/web/login/authorize?client_id=4&redirect_uri=http://192.168.16.87:8080/lottery/mvc/doLogin&response_type=code&page_type=1&common=0";
    public static final String LOGIN_TAG = "loginTag";
    
    public static Integer totoal = 3;
}
