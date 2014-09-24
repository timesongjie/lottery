package com.bbkmobile.iqoo.interfaces.lottery.business;  

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.common.lottery.LotteryPool;
import com.bbkmobile.iqoo.common.lottery.Top3LotteryPool;
import com.bbkmobile.iqoo.interfaces.lottery.dao.LotteryDAO;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
  
@Service
public class LotteryServiceImpl implements LotteryService{

    private LotteryPool top3Pool = new Top3LotteryPool();
    
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
    @Override
    public void addLotteryRecord(LotteryRecord record) throws Exception {
        List<LotteryRecord> records = new ArrayList<LotteryRecord>(1);
        records.add(record);
        lotteryDaoImpl.addLotteryRecords(records);
    }
    @Override
    public Lottery lottery(int userType) throws Exception {
        //TODO
        return top3Pool.pool();
    }

    public static void main(String[] args) {
        final LotteryPool top3Pool = new Top3LotteryPool();
        for(int i=0; i<100; i++){
            new Thread(){
                @Override
                public void run() {
                    Lottery lottery = top3Pool.pool();
                    System.out.println("恭喜您，您获得 " + lottery.getGrand() + "奖~");
                }
                
            }.start();;
        }
    }
   
    
    
}
