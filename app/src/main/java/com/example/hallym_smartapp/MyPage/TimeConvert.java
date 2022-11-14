package com.example.hallym_smartapp.MyPage;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConvert {
    long currentTime;
    long diff;
    long hour;
    long min;
    long second;
    long reqTime;
    Date reqDate;

    public TimeConvert(String date) {
        String reqDateString = date;

        Date currenDate = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM월 dd일 HH시 mm분 ss초");
        this.reqDate = null;

        try{
            this.reqDate = dateFormat.parse(reqDateString);
            Log.e("남은시간:",String.valueOf(reqDate));
        } catch (ParseException e){
            e.printStackTrace();
        }
        this.currentTime = currenDate.getTime();

        this.diff = reqTime - currentTime;
        Log.e("test",String.valueOf(diff));
        this.hour=diff/3600000;
        this.min = (diff%3600000)/60000;
        this.second = ((diff%3600000)%60000)/1000;
    }
    public long getDiff(){
        Log.e("시간값",Long.toString(diff));
        return diff;
    }
    public long getHour(){
        return hour;
    }
    public long getMin(){
        return min;
    }
    public long getSecond(){
        return second;
    }
}
