package com.bbkmobile.iqoo.interfaces.lottery.vo;  

import java.util.Date;
/**
 * 抽奖记录  
 *@Title:  
 *@Description:  
 *@Author:time 
 *@Since:2014年9月23日  
 *@Modified By:
 *@Modified Date:
 *@Why & What is modified:
 *@Version:1.0
 */
public class LotteryDownloadRecord {

    private Integer id;
    private String userId;
    private String appId;
    private Date downloadDate;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public Date getDownloadDate() {
        return downloadDate;
    }
    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }
}
