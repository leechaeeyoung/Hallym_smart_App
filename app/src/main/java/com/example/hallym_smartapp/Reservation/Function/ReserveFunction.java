package com.example.hallym_smartapp.Reservation.Function;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.room.Query;

import com.example.hallym_smartapp.R;

public class ReserveFunction extends Activity {
    public static Context tableContext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_main);
        setTitle("한림대학교 도서관 좌석 예약하기");
    }
//    public void nowSeat(){
//        Query query = databaseReference.child("seat_cnt").child("nowSeatCnt");
//    }
}
