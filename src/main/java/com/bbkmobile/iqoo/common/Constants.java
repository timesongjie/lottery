package com.bbkmobile.iqoo.common;

import com.bbkmobile.iqoo.common.util.cfgfile.PropertiesFileManager;

public class Constants {

    private  static String COMMON_URL = "http://219.130.55.42:8400";
    
    public  static String GETINFO_URL = COMMON_URL
            + "/v3/web/login/getUserInfo";
    
    public  static String GETACCESSTOKEN_URL = COMMON_URL
            + "/v3/web/login/getAccessToken";
    
    public  static String LOGIN_URL = null;//&redirect_uri=http://192.168.16.87:8080/lottery/mvc/doLogin";
    
    public static String LOTTERY_URL = "/mvc/doLogin";
    
    public static final String LOGIN_TAG = "loginTag";
    
    public static String CLIENT_ID = null;
    public static String CLIENT_SECRET = null;
    
    public static Integer totoal = 3;
    
    private static PropertiesFileManager manager = PropertiesFileManager.getInstance();
    
    static {
        init();
    }
    private static void init() {
        COMMON_URL = manager.getProperty("usercenter.url");
        LOGIN_URL =  COMMON_URL
                + "/v3/web/login/authorize?response_type=code&page_type=1&common=0&client_id=";
        
        CLIENT_ID = manager.getProperty("usercenter.client_id");
        CLIENT_SECRET = manager.getProperty("usercenter.client_secret");
        
        LOGIN_URL = LOGIN_URL + CLIENT_ID + "&redirect_uri=" + manager.getProperty("lottery.url")+LOTTERY_URL;
        
        String prize_count = manager.getValueFromPropFile("prize_count");
        if(prize_count != null && !"".equals(prize_count.trim())){
            totoal = Integer.valueOf(prize_count);
        }
    }
    
}
