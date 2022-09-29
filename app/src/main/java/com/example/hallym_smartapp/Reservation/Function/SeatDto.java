package com.example.hallym_smartapp.Reservation.Function;

import androidx.room.Ignore;

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
    private String endTime = "";

    public SeatDto(int seatNum) {
        this.seatNum = seatNum;
    }

    public SeatDto(int seatNum, String userId, boolean seatState, String endTime) {
        this.seatNum = seatNum;
        this.userId = userId;
        this.seatState = seatState;
        this.endTime = endTime;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public SeatDto() {
    }
    @Exclude
    public Map<String, Object> map(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("seatNum",seatNum);
        result.put("userId",userId);
        result.put("seatState",seatState);
        result.put("remainTime",endTime);
        return result;
    }
}

