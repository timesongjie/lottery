package com.bbkmobile.iqoo.common.lottery;  

/**
 * 奖卷对象
 *@Title:  
 *@Description:  
 *@Author:time 
 *@Since:2014年9月24日  
 *@Modified By:
 *@Modified Date:
 *@Why & What is modified:
 *@Version:1.0
 */
public enum Lottery {
    
    FIRSTLOTTERY(1),SECONDLOTTERY(2),THIRDLOTTERY(3),FOURTHLOTTERY(4),NONLOTTERY(0);
    private Lottery(Integer grand){
        this.grand = grand;
    }
    private String sn;
    private Integer grand;
    public String getSn() {
        return sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public Integer getGrand() {
        return grand;
    }
    public void setGrand(Integer grand) {
        this.grand = grand;
    }
}
