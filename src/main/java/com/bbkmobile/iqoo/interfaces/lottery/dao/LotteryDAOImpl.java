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
        return session.selectList("lotteryMapper.selectLotteryRecords");
    }

    @Override
    public void addLotteryRecords(List<LotteryRecord> records) throws Exception {
        if(records != null && records.size() >0){
            for(LotteryRecord record : records){
                session.insert("lotteryMapper.addLotteryRecords", record);
            }
        }
    }

    @Override
    public void addLotteryDownloadRecords(LotteryDownloadRecord record)
            throws Exception {
        if(record != null){
            session.insert("");
        }
    }

    @Override
    public int countUserDownloads(String userId) throws Exception {
        return session.selectOne("");
    }

}
