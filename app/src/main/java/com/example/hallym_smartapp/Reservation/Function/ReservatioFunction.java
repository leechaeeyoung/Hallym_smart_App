package com.example.hallym_smartapp.Reservation.Function;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.TimeTest;
import com.example.hallym_smartapp.MyPage.Timeadd;

import java.sql.Time;
import java.util.List;

public class ReservatioFunction {
    static SeatDao seatDao = new SeatDao();

    public static void reservationSeat(int floorNum, int seatNum, UserDTO userDTO, List<SeatDto> seatDto, String reservationTime){
        Timeadd timeadd = new Timeadd();
        seatDao.upCnt();
        seatDao.updateSeat(floorNum,seatNum, timeadd.add(reservationTime));
        seatDao.updateUser(seatNum,floorNum,userDTO,true,reservationTime,timeadd.add(reservationTime));
        seatDto.get(seatNum).setSeatState(true); // 예약 완료된 좌석 색 변경
    }
    public static void deleteInfo(UserDTO userDTO){
        seatDao.downCnt();
        seatDao.emptySeat(userDTO.getFloorNum(), userDTO.getSeatNum());
        seatDao.updateUser(userDTO);
    }
    public void renew(UserDTO userDTO){
        Timeadd timeadd = new Timeadd();
        String result = timeadd.renew(userDTO.getRemainTime());
        seatDao.updateUser(userDTO,result);
    }
}