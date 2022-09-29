package com.example.hallym_smartapp.Reservation.Function;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class SeatCnt {
    private String nowSeatCnt = "200";

    public SeatCnt(){}
    public SeatCnt(String nowSeatCnt){
        this.nowSeatCnt=nowSeatCnt;
    }
    public String getNowSeatCnt(){
        return nowSeatCnt;
    }
    public void setNowSeatCnt(String nowSeatCnt){
        this.nowSeatCnt=nowSeatCnt;
    }
    @Exclude
    public Map<String, Object> map() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nowSeatCnt", nowSeatCnt);
        return result;
    }
}
