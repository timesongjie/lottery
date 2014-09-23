package com.bbkmobile.iqoo.interfaces.lottery.business;

import java.util.List;

import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;

public interface LotteryService {

    public List<LotteryRecord> getTop10Records() throws Exception;

    public void addLotteryDownloadRecord(LotteryDownloadRecord record)
            throws Exception;
}
