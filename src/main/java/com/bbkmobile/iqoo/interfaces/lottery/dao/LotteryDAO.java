package com.bbkmobile.iqoo.interfaces.lottery.dao;

import java.util.List;

import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryClickRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

public interface LotteryDAO {

    /**
     * 获取中奖记录
     * 
     * @Description:
     * @return
     * @throws Exception
     * @Author:time
     * @see:
     * @since: 1.0
     * @Create Date:2014年9月23日
     */
    public List<LotteryRecord> list() throws Exception;

    public List<LotteryRecord> list(LotteryUserInfo userInfo) throws Exception;

    // /**
    // * 新增中奖记录
    // *
    // * @Description:
    // * @param records
    // * @throws Exception
    // * @Author:time
    // * @see:
    // * @since: 1.0
    // * @Create Date:2014年9月23日
    // */
    // public void addLotteryRecords(List<LotteryRecord> records) throws
    // Exception;

    /**
     * 新增下载应用记录
     * 
     * @Description:
     * @param record
     * @throws Exception
     * @Author:time
     * @see:
     * @since: 1.0
     * @Create Date:2014年9月23日
     */
    public void addLotteryDownloadRecords(LotteryDownloadRecord record)
            throws Exception;

    /**
     * 根据用户获取下载记录
     * 
     * @Description:
     * @param userId
     * @return
     * @throws Exception
     * @Author:time
     * @see:
     * @since: 1.0
     * @Create Date:2014年9月23日
     */
    public int countUserDownloads(String userId) throws Exception;

    /**
     * 是否Svip
     * 
     * @Description:
     * @param userInfo
     * @return
     * @throws Exception
     * @Author:time
     * @see:
     * @since: 1.0
     * @Create Date:2014年9月25日
     */
    public boolean isSvip(LotteryUserInfo userInfo) throws Exception;

    public int countClickTimesByUserId(String userId) throws Exception;

    public void addClickRecords(LotteryClickRecord records) throws Exception;

    /**
     * 根据用户id 和 等级 查看是否中过奖
     */
    public int hasTop3Award(LotteryUserInfo user) throws Exception;

    /**
     * 获取数据库中对应等级项，根据start_time/status，有则返回，无则返回空
     * 
     * @Author:time
     * @Create Date:2014年9月26日
     */
    public Lottery getAward(int grade) throws Exception;

    public void updateAward(String userId, int awardId) throws Exception;

    int has4Award(LotteryUserInfo user) throws Exception;
}
