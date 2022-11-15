package com.example.hallym_smartapp.Reservation.Function;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// 데이터 교환
@IgnoreExtraProperties
public class SeatDto {
    private int rowIndex;
    private char rowNames;
    private String userId = "";
    private boolean seatState = false;
    private String remainTime = "";

    public SeatDto(char rowNames,int rowIndex) {
        this.rowIndex = rowIndex;
        this.rowNames = rowNames;
    }

    public SeatDto(char rowNames, int rowIndex,String userId, boolean seatState, String remainTime) {
        this.rowNames = rowNames;
        this.rowIndex = rowIndex;
        this.userId = userId;
        this.seatState = seatState;
        this.remainTime = remainTime;
    }

    public char getRowNames() {
        return rowNames;
    }

    public void setRowNames(char rowNames) {
        this.rowNames = rowNames;
    }

    public int getRowIndex(){return rowIndex;}
    
    public void setRowIndex(int rowIndex){ this.rowIndex = rowIndex; }

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
        result.put("rowNames",rowNames);
        result.put("rowIndex",rowIndex);
        result.put("userId",userId);
        result.put("seatState",seatState);
        result.put("remainTime",remainTime);
        return result;
    }
}

