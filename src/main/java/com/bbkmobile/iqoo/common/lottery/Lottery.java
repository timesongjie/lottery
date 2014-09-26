package com.bbkmobile.iqoo.common.lottery;

import java.util.Date;

/**
 * 奖卷对象
 * 
 * @Title:
 * @Description:
 * @Author:time
 * @Since:2014年9月24日
 * @Modified By:
 * @Modified Date:
 * @Why & What is modified:
 * @Version:1.0
 */
// public enum Lottery {
//
// FIRSTLOTTERY(1),SECONDLOTTERY(2),THIRDLOTTERY(3),FOURTHLOTTERY(4),NONLOTTERY(0);
// private Lottery(Integer grand){
// this.grand = grand;
// }
// private String sn;
// private Integer grand;
// public String getSn() {
// return sn;
// }
// public void setSn(String sn) {
// this.sn = sn;
// }
// public Integer getGrand() {
// return grand;
// }
// public void setGrand(Integer grand) {
// this.grand = grand;
// }
// }
public class Lottery {
    private int id;
    private String code;
    private String grade;
    private String userId;
    private char status;
    private Date start_time;
    private Date lottery_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getLottery_time() {
        return lottery_time;
    }

    public void setLottery_time(Date lottery_time) {
        this.lottery_time = lottery_time;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
