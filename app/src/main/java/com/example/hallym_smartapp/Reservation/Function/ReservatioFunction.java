package com.example.hallym_smartapp.Reservation.Function;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.ReservationTimeSet;

import java.util.List;

public class ReservatioFunction {
    static SeatDao seatDao = new SeatDao();
    public void reservationSeat(int floorNum, char rowName, int rowIndex, UserDTO userDTO, List<SeatDto> seatDto,String reservationTime){
        ReservationTimeSet reservationTimeSet = new ReservationTimeSet();
        seatDao.upCnt();
        seatDao.updateSeat(rowName,rowIndex,reservationTime);
        seatDao.updateUser(rowName,rowIndex,floorNum,userDTO,true,userDTO.getReservationDate(),userDTO.getRemainTime());

    }
    public static void moveSeat(char rowName, int rowIndex, int floorNum, UserDTO userDTO, List<SeatDto> seatDto){
        seatDao.emptySeat((char) userDTO.getRowIndex(),userDTO.getRowIndex());
        seatDao.updateUser(rowName,rowIndex,floorNum,userDTO,true,userDTO.getReservationDate(),userDTO.getRemainTime());
        seatDao.updateSeat(rowName,rowIndex,userDTO.getRemainTime());
    }
    public static void deleteInfo(UserDTO userDTO){
        seatDao.downCnt();
        seatDao.emptySeat((char) userDTO.getRowIndex(),userDTO.getRowIndex());
        seatDao.updateUser(userDTO);
    }
    public void renew(UserDTO userDTO){
        ReservationTimeSet reservationTimeSet = new ReservationTimeSet();
        String result = reservationTimeSet.renew(userDTO.getRemainTime());
        seatDao.updateUser(userDTO,result);
    }
}
