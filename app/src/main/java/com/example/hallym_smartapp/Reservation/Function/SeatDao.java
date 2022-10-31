package com.example.hallym_smartapp.Reservation.Function;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;

import com.example.hallym_smartapp.Login.UserDTO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

// DB접속 데이터 CRUD작업
public class SeatDao {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    // 데이터 읽기 위한 객체생성
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    // 유저 정보 초기화
    public void updateUser(UserDTO userDTO){
        userDTO.setSeatState(false);
        userDTO.setReservationDate("");
        userDTO.setRemainTime("");
        userDTO.setFloorNum(0);
        userDTO.setRowNames("");
        Map<String, Object> postValues = userDTO.map();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/"+loginId, postValues);
        databaseReference.updateChildren(childUpdates);
    }
    // 유저 정보 업데이트
    public void updateUser(String rowName, int rowIndex, int floorNum, UserDTO userDTO,boolean seatState,String startTime,String remainTime){
        userDTO.setRowNames(rowName);
        userDTO.setRowIndex(rowIndex);
        userDTO.setFloorNum(floorNum);
        userDTO.setSeatState(seatState);
        userDTO.setReservationDate(startTime);
        userDTO.setRemainTime(remainTime);
        Map<String, Object> postValues = userDTO.map();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/"+loginId,postValues);
        databaseReference.updateChildren(childUpdates);
    }
    // 좌석 정보 업데이트
    public void updateSeat(String rowNames, int rowIndex, String startTime){
        String key = databaseReference.child("Floor").push().getKey();
        SeatDto seatDB = new SeatDto(rowNames,rowIndex,loginId, true,startTime);
        Map<String, Object> postValues = seatDB.map();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/Floor/"+rowNames+rowIndex+"seat",postValues);
        databaseReference.updateChildren(childUpdates);
    }

//    // 좌석 예약 완료 시 남은 좌석 down
//    public void downCnt(){
//        SeatCnt seatCnt = new SeatCnt(Integer.toString(totalCnt-1));
//        Map<String,Object> postValues = seatCnt.map();
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/seat_cnt/"+"nowSeatCnt",postValues);
//        databaseReference.updateChildren(childUpdates);
//    }
//    // 좌석 반납 시 남은 좌석 up
//    public void downCnt(){
//        SeatCnt seatCnt = new SeatCnt(Integer.toString(totalCnt+1));
//        Map<String,Object> postValues = seatCnt.map();
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/seat_cnt/"+"nowSeatCnt",postValues);
//        databaseReference.updateChildren(childUpdates);
//    }
}
