package com.example.hallym_smartapp.Reservation.Function;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import com.example.hallym_smartapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReserveDialog extends AppCompatActivity {
    Long now;
    Date date;
    List<SeatDto> count = new ArrayList<SeatDto>();
    static boolean check = false;
    static boolean flag = true;
    public static int totalSeat = 0;
    SeatDto seatTest;
    static Context context;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        setContentView();
        setTitle("3층 좌석");
        context = this;
        setContentView(R.layout.three_floor);
        count = new ArrayList<>();
    }
    protected void onPause(){
        super.onPause();
        flag = false;
        Log.e("pauseTest","test");
    }
    private String reservationDate(){
        SimpleDateFormat format = new SimpleDateFormat(" mm월 dd일  HH시 mm분 ss초");
        now = System.currentTimeMillis();
        date = new Date(now);
        return format.format(date);
    }
}
