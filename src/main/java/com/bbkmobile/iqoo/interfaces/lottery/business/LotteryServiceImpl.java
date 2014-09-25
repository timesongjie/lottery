package com.bbkmobile.iqoo.interfaces.lottery.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.common.lottery.LotteryPool;
import com.bbkmobile.iqoo.common.lottery.Top3LotteryPool;
import com.bbkmobile.iqoo.interfaces.lottery.dao.LotteryDAO;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryClickRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

@Service
public class LotteryServiceImpl implements LotteryService {

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
    public Lottery lottery(LotteryUserInfo userInfo) throws Exception {
        // 根据不同的用户类型进行抽奖
        // is Svip
        boolean isSvip = lotteryDaoImpl.isSvip(userInfo);
        if (isSvip) {

        }
        // is vip
        int downloads = lotteryDaoImpl.countUserDownloads(userInfo.getId());
        if (downloads > 0) {

        }
        return top3Pool.pool();
    }

    @Override
    public int countClickTimesByUserId(String userId) throws Exception {
        return lotteryDaoImpl.countClickTimesByUserId(userId);
    }

    @Override
    public void addClickRecord(LotteryClickRecord record) throws Exception {
        lotteryDaoImpl.addClickRecords(record);
    }
}
