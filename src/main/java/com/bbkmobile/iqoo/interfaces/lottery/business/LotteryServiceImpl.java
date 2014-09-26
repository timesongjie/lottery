package com.bbkmobile.iqoo.interfaces.lottery.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.interfaces.lottery.dao.LotteryDAO;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryClickRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

@Service
public class LotteryServiceImpl implements LotteryService {

    // private LotteryPool top3Pool = new Top3LotteryPool();

    private Random random = new Random();
    private Object lock = new Object();

    @Resource
    private LotteryDAO lotteryDaoImpl;

    @Override
    public List<LotteryRecord> getTop10Records() throws Exception {
        return lotteryDaoImpl.list();
    }
    @Override
    public List<LotteryRecord> getOwnsRecords(LotteryUserInfo info)
            throws Exception {
        return lotteryDaoImpl.list(info);
    }
    @Override
    public void addLotteryDownloadRecord(LotteryDownloadRecord record)
            throws Exception {
        lotteryDaoImpl.addLotteryDownloadRecords(record);
    }

    // @Override
    // public void addLotteryRecord(LotteryRecord record) throws Exception {
    // List<LotteryRecord> records = new ArrayList<LotteryRecord>(1);
    // records.add(record);
    // lotteryDaoImpl.addLotteryRecords(records);
    // }

    @Override
    public Lottery lottery(LotteryUserInfo userInfo) throws Exception {
        // 根据不同的用户类型进行抽奖
        // is svip
        Lottery lottery = null;
        try {
            if (lotteryDaoImpl.isSvip(userInfo)) {
                // svip 并且未中过一/二/三/等奖 then（抽奖 > 中奖返回，未中奖继续）else 继续
                if (lotteryDaoImpl.hasTop3Award(userInfo) == 0) {
                    lottery = doLotteryTop3(userInfo);
                    // 中奖 then return else 继续
                    if (lottery != null) {
                        return lottery;
                    }
                }
            }
            // is vip
            int downloads = lotteryDaoImpl.countUserDownloads(userInfo.getId());
            if (downloads > 0) {
                // vip 并且 当天 未中过四等奖则抽奖 > 中奖返回
                if(lotteryDaoImpl.has4Award(userInfo) == 0){
                    lottery = doLottery4(userInfo);
                }
            }
            //other nothing
        } catch (Exception e) {
            throw e;
        } finally {
            // 新增抽奖记录
            LotteryClickRecord clickRecord = new LotteryClickRecord();
            clickRecord.setUserId(userInfo.getId());
            if(lottery != null){
                clickRecord.setGrade(lottery.getGrade());
            }
            addClickRecord(clickRecord);
        }
        return lottery;
    }
   private Lottery doLottery4(LotteryUserInfo userInfo)throws Exception {
        Lottery lottery = null;
        synchronized (lock) {
            // 随机4/5等奖，但一定会有
            int rgrade = random.nextInt(2) + 4;
            // DB获取对应奖品记录，有则更新记录，产生中奖记录，并返回，否则返回null
            if (rgrade != 0) {
                // 中奖咯，但看得DB是否有奖哦
                lottery = lotteryDaoImpl.getAward(rgrade);
                if (lottery != null && lottery.getId() > 0) {
                    // 更新记录
                    lotteryDaoImpl.updateAward(userInfo.getId(),
                            lottery.getId());
                }
            }
        }
        return lottery;
    }
   private Lottery doLotteryTop3(LotteryUserInfo userInfo) throws Exception {
        Lottery lottery = null;
        synchronized (lock) {
            // 随机0/1/2/3等奖
            int rgrade = random.nextInt(4);
            // DB获取对应奖品记录，有则更新记录，产生中奖记录，并返回，否则返回null
            if (rgrade != 0) {
                // 中奖咯，但看得DB是否有奖哦
                lottery = lotteryDaoImpl.getAward(rgrade);
                if (lottery != null && lottery.getId() > 0) {
                    // 更新记录
                    lotteryDaoImpl.updateAward(userInfo.getId(),
                            lottery.getId());
                }
            }
        }
        return lottery;
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
