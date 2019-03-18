package com.starv.tvindex.util.timer;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by weizhi on 2016/11/1.
 * 只针对单个timer  单个timertask
 */
public class TimerUtil {

    public static TimerUtil instance;
    public static TimerUtil getInstance(){
        if(null == instance){
            instance = new TimerUtil();
        }
        return instance;
    }


    private Timer timer;
    private TimerTask timerTask = null;
    private TimerUtil(){
        if(null == timer){
            timer = new Timer();
        }

    }


    public TimerUtil setTimerTask(TimerTask task){
        if(null == timerTask){
            timerTask = task;
        }
        return this;
    }


    public void start(long time){
        if(null == timer || null == timerTask){
            return;
        }
        timer.schedule(timerTask,time,10);

//        Log.i("test","---------- start -------------");
    }
    /**
     * Cancels the Timer and removes any scheduled tasks.
     * */
    public void cancleTimer(){
        if(null == timer){
            return;
        }
        timer.cancel();
        timer = null;
    }

    /**
     * Removes all canceled tasks from the task queue.
     * */
    public void purgeTimer(){
        if(null == timer){
            return;
        }
        timer.purge();
        timer = null;
    }

    public void cancleTask(){
        if(null == timerTask){
            return;
        }
        timerTask.cancel();
        timerTask = null;
    }
}
