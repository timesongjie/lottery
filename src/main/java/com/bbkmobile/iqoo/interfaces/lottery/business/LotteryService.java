package com.bbkmobile.iqoo.interfaces.lottery.business;

import java.util.List;

import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryClickRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

public interface LotteryService {

    public List<LotteryRecord> getTop10Records() throws Exception;
    public List<LotteryRecord> getOwnsRecords(LotteryUserInfo info) throws Exception;

    public void addLotteryDownloadRecord(LotteryDownloadRecord record)
            throws Exception;
//    public void addLotteryRecord(LotteryRecord record)throws Exception;
    /**
     * 根据用户性质参与抽奖
     * @Description:  
     * @param userInfo 1：svip 2. vip 3.vistor
     * @return
     * @throws Exception  
     * @Author:time
     * @see:
     * @since: 1.0
     * @Create Date:2014年9月24日
     */
    public Lottery lottery(LotteryUserInfo userInfo)throws Exception;

    public int countClickTimesByUserId(String userId) throws Exception;
    
    public void addClickRecord(LotteryClickRecord record)throws Exception;
    
}
