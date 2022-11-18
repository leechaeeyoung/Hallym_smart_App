package com.example.hallym_smartapp.MyPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Timeadd {
    public String add(String str) {
        SimpleDateFormat format = new SimpleDateFormat("MM월 dd일 HH시 mm분 ss초");
        Date date = new Date();

        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 2);
        String result = format.format(cal.getTime());
        System.out.println("test" + format.format(cal.getTime()));
        return result;
    }
    public String renew(String str) {
        SimpleDateFormat format = new SimpleDateFormat("MM월 dd일 HH시 mm분 ss초");
        Date date = new Date();

        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 2);
        String result =  format.format(cal.getTime());
        System.out.println("날짜"+format.format(cal.getTime()));
        return result;
    }
}