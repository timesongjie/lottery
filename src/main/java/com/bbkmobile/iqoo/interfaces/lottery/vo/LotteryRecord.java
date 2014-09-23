package com.bbkmobile.iqoo.interfaces.lottery.vo;

import java.util.Date;

/**
 * 中奖记录
 * 
 * @Title:
 * @Description:
 * @Author:time
 * @Since:2014年9月23日
 * @Modified By:
 * @Modified Date:
 * @Why & What is modified:
 * @Version:1.0
 */
public class LotteryRecord {

    private Integer id;
    private String userId;
    private Date lottery_date;
    private String sn;   // 中奖编号
    private Integer lottery_grade;// 几等奖

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

    public Date getLottery_date() {
        return lottery_date;
    }

    public void setLottery_date(Date lottery_date) {
        this.lottery_date = lottery_date;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getLottery_grade() {
        return lottery_grade;
    }

    public void setLottery_grade(Integer lottery_grade) {
        this.lottery_grade = lottery_grade;
    }
}
