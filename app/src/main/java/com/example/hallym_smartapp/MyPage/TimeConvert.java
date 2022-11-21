package com.example.hallym_smartapp.MyPage;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

        // 현재 date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM월 dd일 HH시 mm분 ss초");

        // 요청시간을 date로 parsing 후 시간 가져오기
        this.reqDate = null;

        try{
            this.reqDate = dateFormat.parse(reqDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.reqTime= Objects.requireNonNull(reqDate).getTime();


        // 현재시간을 요청시간 형태로 가져오기기
        try{
           currentDate = dateFormat.parse(dateFormat.format(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.currentTime = currentDate.getTime();

        //시, 분, 초로 표현
        this.diff = reqTime - currentTime;
        Log.e("test",String.valueOf(diff));
        this.hour=diff/3600000;
        this.min = (diff%3600000)/60000;
        this.second = ((diff%3600000)%60000)/1000;
    }
    public long getDiff(){
        Log.e("시간: ",Long.toString(diff));
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
