package com.example.hallym_smartapp.Login;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserDTO{
    private String id;
    private String pwd;
    private String name;
    private int floorNum;
    private int seatNum;
    private String reservationDate="";
    private String remainTime="";
    private boolean myreservationState=false;

    public UserDTO(){}

    public UserDTO(String id, String pwd, String name){
        this.id=id;
        this.pwd=pwd;
        this.name=name;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public boolean isMyreservationState() {
        return myreservationState;
    }

    public void setMyreservationState(boolean myreservationState) {
        this.myreservationState = myreservationState;
    }

    // DB에 User 데이터 삽입
    @Exclude
    public Map<String, Object> map(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("id",id);
        result.put("pwd",pwd);
        result.put("floorNum",floorNum);
        result.put("seatNum",seatNum);
        result.put("reservationDate",reservationDate);
        result.put("remainTime",remainTime);
        result.put("myreservationState",myreservationState);

        return result;
    }
}
