package com.bbkmobile.iqoo.common.lottery;  

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.bbkmobile.iqoo.common.util.cfgfile.PropertiesFileManager;
  
public class Top3LotteryPool extends LotteryPool{

    public Top3LotteryPool(){
        init();
    };
    @Override
    public void init() {
        String top3Size = PropertiesFileManager.getInstance().getValueFromPropFile("vip_size"); // 最小29个用户
        pools = new ArrayList<Lottery>();
        doInit(pools,Integer.valueOf(top3Size));
    }
    private void doInit(List<Lottery> pools,int size) { 
        for(int i=0; i < size; i++){
            pools.add(Lottery.NONLOTTERY);
        }
        Calendar calendar = Calendar.getInstance();
        int month = 10;//calendar.get(Calendar.MONTH) + 1;
        int date = 2;//calendar.get(Calendar.DATE) ;
        
        Random random = new Random();
        
        if((month == 9 && date == 30) || (month == 10 && ( date == 1 || date == 2 || date == 3))){ // 一个一等奖
            pools.add(random.nextInt(size),Lottery.FIRSTLOTTERY);
        }
        while(true){ // 一个二等奖
            int index = random.nextInt(size);
            if(pools.get(index) == Lottery.NONLOTTERY){
                pools.add(index,Lottery.SECONDLOTTERY);
                break;
            }
        }
        if((month == 10 && date == 1) || (month == 10 && date == 2) ){ //这两天 2个二等奖 
            while(true){
                int index = random.nextInt(size);
                if(pools.get(index) == Lottery.NONLOTTERY){
                    pools.add(index,Lottery.SECONDLOTTERY);
                    break;
                }
            }
        }
        for(int i=0; i<25; i++){
            while(true){
                int index = random.nextInt(size);
                if(pools.get(index) == Lottery.FIRSTLOTTERY && pools.get(index) != Lottery.SECONDLOTTERY){
                    pools.add(index,Lottery.THIRDLOTTERY);
                    break;
                }
            }
        }
    }
}
