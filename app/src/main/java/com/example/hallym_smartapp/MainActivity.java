package com.example.hallym_smartapp;

import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Api.ApiActivity;
import com.example.hallym_smartapp.Login.LoginActivity;
import com.example.hallym_smartapp.MyPage.MyPage;
import com.example.hallym_smartapp.Reservation.Activity.ReservationActivity;
import com.example.hallym_smartapp.Reservation.Function.SeatCnt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    Button Btn_Libraryreserve, Btn_myInfo, Btn_FindCampus;
    Intent intent;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBackPressed();

        Btn_Libraryreserve =findViewById(R.id.Btn_Libraryreserve);
        Btn_myInfo=findViewById(R.id.Btn_myInfo);
        Btn_FindCampus =findViewById(R.id. Btn_FindCampus);

        Btn_Libraryreserve.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), ReservationActivity.class);
            startActivity(intent);
        }); // 도서관 예약 클릭


        Btn_FindCampus.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), ApiActivity.class);
            startActivity(intent);
        }); // 도서관 예약 클릭

        Btn_myInfo.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), MyPage.class);
            startActivity(intent);
        }); // 마이 페이지 클릭
    }
    public void onBackPressed(){}

    //DB에 좌석수 정보 set
    public void seatCntDB(){
        SeatCnt seatCnt = new SeatCnt(Integer.toString(90));
        Map<String, Object> postValues = seatCnt.map();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/seat_cnt/"+"nowSeatCnt",postValues);
        databaseReference.updateChildren(childUpdates);
    }
}

