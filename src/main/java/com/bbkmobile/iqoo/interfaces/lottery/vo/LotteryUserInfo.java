package com.bbkmobile.iqoo.interfaces.lottery.vo;  
  
public class LotteryUserInfo {

    private Integer id;
    private String name;
    private String email;
    private String phonenum;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhonenum() {
        return phonenum;
    }
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    @Override
    public String toString() {
        return "name:"+this.name+"-email:"+email+"-phonenum:"+phonenum;
    }
    
}
