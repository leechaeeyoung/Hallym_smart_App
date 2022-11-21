package com.example.hallym_smartapp.MyPage;


import android.util.Log;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeConvert {

    long currentTime;
    long diff;
    long hour;
    long min;
    long second;
    long reqTime;
    Date reqDate;


    public TimeConvert(String date) {

        //요청시간 String
        String reqDateStr = date;

        //현재시간 Date
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

        //요청시간을 Date로 parsing 후 time가져오기
        this.reqDate = null;

        try {
            this.reqDate = dateFormat.parse(reqDateStr);
            Log.e("남은시간1",String.valueOf(reqDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.reqTime = reqDate.getTime();

        //현재시간을 요청시간의 형태로 format 후 time 가져오기
        try {
            curDate = dateFormat.parse(dateFormat.format(curDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.currentTime = curDate.getTime();

        //시, 분, 초로 표현
        this.diff = reqTime - currentTime;
        Log.e("test",String.valueOf(diff));
        this.hour = diff/ 3600000;
        this.min = (diff % 3600000) / 60000;
        this.second = ((diff % 3600000) % 60000) / 1000;


    }
    public long getDiff(){
        Log.e("계산된 시간값", Long.toString(diff));
        return diff;
    }

    public long getHour() {
        return hour;
    }

    public long getMin() {
        return min;
    }

    public long getSecond() {
        return second;
    }

}