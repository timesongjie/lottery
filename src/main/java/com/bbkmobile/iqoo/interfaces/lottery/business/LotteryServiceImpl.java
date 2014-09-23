package com.bbkmobile.iqoo.interfaces.lottery.business;  

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbkmobile.iqoo.interfaces.lottery.dao.LotteryDAO;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
  
@Service
public class LotteryServiceImpl implements LotteryService{

    @Resource
    private LotteryDAO lotteryDaoImpl;

    @Override
    public List<LotteryRecord> getTop10Records() throws Exception {
        return lotteryDaoImpl.list();
    }

    @Override
    public void addLotteryDownloadRecord(LotteryDownloadRecord record)
            throws Exception {
        lotteryDaoImpl.addLotteryDownloadRecords(record);
    }
    
    
}
