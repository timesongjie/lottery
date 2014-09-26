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
public class LotteryClickRecord {

    private Integer id;
    private String userId;
    private String grade;
    private Date clickDate;
    
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
    public Date getClickDate() {
        return clickDate;
    }
    public void setClickDate(Date clickDate) {
        this.clickDate = clickDate;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
