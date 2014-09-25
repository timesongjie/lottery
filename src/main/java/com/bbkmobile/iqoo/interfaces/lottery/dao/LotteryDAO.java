package com.bbkmobile.iqoo.interfaces.lottery.dao;

import java.util.List;

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

    /**
     * 新增中奖记录
     * 
     * @Description:
     * @param records
     * @throws Exception
     * @Author:time
     * @see:
     * @since: 1.0
     * @Create Date:2014年9月23日
     */
    public void addLotteryRecords(List<LotteryRecord> records) throws Exception;

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
    
    public void addClickRecords(LotteryClickRecord records)throws Exception;
}
