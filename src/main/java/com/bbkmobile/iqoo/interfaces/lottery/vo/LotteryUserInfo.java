package com.bbkmobile.iqoo.interfaces.lottery.vo;

public class LotteryUserInfo {

    private String id;
    private String name;
    private String email;
    private String phonenum;

    public LotteryUserInfo() {
    }

    public LotteryUserInfo(String id, String name, String email,
            String phonenum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "name:" + this.name + "-email:" + email + "-phonenum:"
                + phonenum;
    }

//    public String getUserName() {
//        String name = null;
//        if (this.getName() != null && !"".equals(this.getName().trim())) {
//            name = this.getName();
//        } else if (this.getEmail() != null
//                && !"".equals(this.getEmail().trim())) {
//            name = this.getEmail();
//        } else if (this.getPhonenum() != null
//                && !"".equals(this.getPhonenum().trim())) {
//            name = this.getPhonenum();
//        }
//        return name;
//    }
}
