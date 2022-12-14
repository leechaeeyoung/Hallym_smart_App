package com.example.hallym_smartapp.Reservation.Function;

import androidx.annotation.Nullable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// 데이터 교환
@IgnoreExtraProperties
public class SeatDto {
    private int seatNum;
    private String userId = "";
    private boolean seatState = false;
    private String remainTime = "";

    public SeatDto(int seatNum) {
        this.seatNum=seatNum;
    }

    public SeatDto(int seatNum,String userId, boolean seatState, String remainTime) {
        this.seatNum = seatNum;
        this.userId = userId;
        this.seatState = seatState;
        this.remainTime = remainTime;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public String getUsedId() {
        return userId;
    }

    public void setUsedId(String usedId) {
        this.userId = usedId;
    }

    public boolean isSeatCheck() {
        return seatState;
    }

    public void setSeatCheck(boolean seatState) {
        this.seatState = seatState;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String endTime) {
        this.remainTime = remainTime;
    }

    public SeatDto() {
    }
    @Exclude
    public Map<String, Object> map(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("seatNum",seatNum);
        result.put("userId",userId);
        result.put("seatState",seatState);
        result.put("remainTime",remainTime);
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
