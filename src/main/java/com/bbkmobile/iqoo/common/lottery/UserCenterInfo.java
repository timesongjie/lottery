package com.bbkmobile.iqoo.common.lottery;

public class UserCenterInfo {

    String code = null;
    String openid = null;
    String openkey = null;
    String client_id = null;
    String client_secret = null;
    String token = null;
    public UserCenterInfo(){
        
    }
    public UserCenterInfo(String code, String openid, String openkey,
            String client_id, String client_secret) {
        super();
        this.code = code;
        this.openid = openid;
        this.openkey = openkey;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenkey() {
        return openkey;
    }

    public void setOpenkey(String openkey) {
        this.openkey = openkey;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
