package com.example.hallym_smartapp.Reservation.Function;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.ReservationTimeSet;

import java.util.List;

public class ReservatioFunction {
    static SeatDao seatDao = new SeatDao();
    public void reservationSeat(int floorNum, int seatNum, UserDTO userDTO, List<SeatDto> seatDto,String reservationTime){
        ReservationTimeSet reservationTimeSet = new ReservationTimeSet();
        seatDao.upCnt();
        seatDao.updateSeat(seatNum,reservationTime);
        seatDao.updateUser(seatNum,floorNum,userDTO,true,userDTO.getReservationDate(),userDTO.getRemainTime());
        seatDto.get(seatNum).setSeatCheck(true); // 예약 완료된 좌석 붉은색으로변경
    }
    public static void moveSeat(int seatNum, int floorNum, UserDTO userDTO, List<SeatDto> seatDto){
        seatDao.emptySeat(seatNum);
        seatDao.updateUser(seatNum,floorNum,userDTO,true,userDTO.getReservationDate(),userDTO.getRemainTime());
        seatDao.updateSeat(seatNum,userDTO.getRemainTime());
    }
    public static void deleteInfo(UserDTO userDTO){
        seatDao.downCnt();
        seatDao.emptySeat(userDTO.getSeatNum());
        seatDao.updateUser(userDTO);
    }
    public void renew(UserDTO userDTO){
        ReservationTimeSet reservationTimeSet = new ReservationTimeSet();
        String result = reservationTimeSet.renew(userDTO.getRemainTime());
        seatDao.updateUser(userDTO,result);
    }
}
