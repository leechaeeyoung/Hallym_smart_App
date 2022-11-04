package com.example.hallym_smartapp.Reservation.Function;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;

import java.util.List;

// 좌석 예약, 반납, 연장 기능
public class ReserveFunction extends Activity {
    SeatDao seatDao = new SeatDao();
    public void reservationSeat(String rowName, int rowIndex, int floorNum,UserDTO userDTO, List<SeatDto> seatDto, String reservationTime){
        //좌석수 +1
//        seatDao.upCnt();
        // 좌석 정보 업데이트
        seatDao.updateSeat(rowName,rowIndex,reservationTime);
        // 학생 정보 업데이트
        seatDao.updateUser(rowName,rowIndex,floorNum,userDTO,true,reservationTime,reservationTime);
        // 예약 좌석 색상을 파란색으로 변경
        seatDto.get(rowIndex).setSeatCheck(true);
    }

    // 좌석 이동시
    public void moveSeat(String rowName, int rowIndex, int floorNum,UserDTO userDTO, List<SeatDto> seatDto){
        // 전 좌석의 색상을 붉은색으로 되돌리기
        seatDto.get((userDTO.getRowIndex())).setSeatCheck(false);
        // 선택좌석 색상 파란색으로 변경
        seatDto.get(rowIndex).setSeatCheck(true);
        // 전 좌석의 사용자 정보 제거
        seatDao.emptySeat(userDTO.getRowIndex());
        // 학생의 좌석 번호 변경
        seatDao.updateUser(rowName,rowIndex, floorNum, userDTO, true, userDTO.getReservationDate(), userDTO.getRemainTime());
        // 이동 좌석에 업데이트
        seatDao.updateSeat(rowName,rowIndex,userDTO.getRemainTime());
    }

}