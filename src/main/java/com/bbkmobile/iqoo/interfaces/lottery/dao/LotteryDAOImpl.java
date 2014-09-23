package com.bbkmobile.iqoo.interfaces.lottery.dao;  

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
  
@Repository
public class LotteryDAOImpl implements LotteryDAO{

    @Resource
    private SqlSession session;
    
    @Override
    public List<LotteryRecord> list() throws Exception {
        return session.selectList("selectLotteryRecords");
    }

    @Override
    public void addLotteryRecords(List<LotteryRecord> records) throws Exception {
        
    }

    @Override
    public void addLotteryDownloadRecords(LotteryDownloadRecord record)
            throws Exception {
    }

    @Override
    public int countUserDownloads(String userId) throws Exception {
        return 0;
    }

}
