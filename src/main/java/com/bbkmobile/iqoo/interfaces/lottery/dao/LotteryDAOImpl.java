package com.bbkmobile.iqoo.interfaces.lottery.dao;  

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryClickRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;
  
@Repository
public class LotteryDAOImpl implements LotteryDAO{

    @Resource
    private SqlSession session;

    @Override
    public List<LotteryRecord> list() throws Exception {
        return session.selectList("lotteryMapper.selectLotteryRecords");
    }
    @Override
    public List<LotteryRecord> list(LotteryUserInfo userInfo) throws Exception {
        return session.selectList("lotteryMapper.ownsRecords",userInfo);
    }
    @Override
    public void addLotteryDownloadRecords(LotteryDownloadRecord record)
            throws Exception {
        if(record != null){
            session.insert("lotteryMapper.addLotteryDownloadRecord",record);
        }
    }

    @Override
    public int countUserDownloads(String userId) throws Exception {
        return (Integer)session.selectOne("lotteryMapper.countUserDownloadRecord",userId);
    }

    @Override
    public boolean isSvip(LotteryUserInfo userInfo) throws Exception {
        int count = (Integer)session.selectOne("lotteryMapper.isVip",userInfo);
        if(count >0){
            return true;
        }
        return false;
    }
    @Override
    public int countClickTimesByUserId(String userId) throws Exception{
        DateFormat df = new SimpleDateFormat("MMdd");
        String logPost = df.format(new Date());
        
        Map<String,String> params = new HashMap<String,String>();
        params.put("tableName", "t_lottery_click_"+logPost);
        params.put("userId", "'"+userId+"'");
        
     return (Integer)session.selectOne("lotteryMapper.countClickTimesByUserId",params);
    }

    @Override
    public void addClickRecords(LotteryClickRecord records) throws Exception {
        
        DateFormat df = new SimpleDateFormat("MMdd");
        String logPost = df.format(new Date());
        
        Map<String,String> params = new HashMap<String,String>();
        params.put("tableName", "t_lottery_click_"+logPost);
        params.put("userId", "'"+records.getUserId()+"'");
        if(records.getGrade() != null && !"".equals(records.getGrade().trim())){
            params.put("grade", "'"+records.getGrade()+"'");
        }else{
            params.put("grade", "'0'");
        }
        session.insert("lotteryMapper.addclickRecords", params);
    }

    @Override
    public int hasTop3Award(LotteryUserInfo user) throws Exception {
        Lottery lottery =  session.selectOne("lotteryMapper.hasTop3",user);
        if(lottery != null && lottery.getId() > 0){
            return 1;
        }
        return 0;
    }
    @Override
    public int has4Award(LotteryUserInfo user) throws Exception {
        Lottery lottery =  session.selectOne("lotteryMapper.has4",user);
        if(lottery != null && lottery.getId() > 0){
            return 1;
        }
        return 0;
    }
    @Override
    public Lottery getAward(int grade) throws Exception {
        return session.selectOne("lotteryMapper.getAward", grade);
    }

    @Override
    public void updateAward(String userId, int awardId) throws Exception {
        Map<String,Object> params = new HashMap<String, Object>(2);
        params.put("userId", userId);
        params.put("id", awardId);
        session.update("lotteryMapper.updateAward",params);
    }
    
    
}
