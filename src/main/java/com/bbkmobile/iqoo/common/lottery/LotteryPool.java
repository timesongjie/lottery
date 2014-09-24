package com.bbkmobile.iqoo.common.lottery;  

import java.util.List;
import java.util.Random;

  
public abstract class LotteryPool {

    protected List<Lottery> pools;
    
    public Lottery pool(){
        Random random = new Random();
        synchronized (pools) {
            if(pools.size() >0){
                int index = random.nextInt(pools.size());
                Lottery lottery = pools.remove(index);
                return lottery;
            }
            return Lottery.NONLOTTERY;
        }
    }
    public abstract void init();
    
}
