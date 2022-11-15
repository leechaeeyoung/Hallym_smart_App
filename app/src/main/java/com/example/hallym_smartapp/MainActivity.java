package com.example.hallym_smartapp;

import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Login.LoginActivity;
import com.example.hallym_smartapp.MyPage.MyPage;
import com.example.hallym_smartapp.Reservation.Activity.FiveFloorActivity;
import com.example.hallym_smartapp.Reservation.Activity.FourFloorActivity;
import com.example.hallym_smartapp.Reservation.Activity.ThirdFloorActivity;
import com.example.hallym_smartapp.Reservation.Function.SeatList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    Button Btn_Libraryreserve, Btn_myInfo,loginBt;
    Intent intent;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBackPressed();

        Btn_Libraryreserve =findViewById(R.id.Btn_Libraryreserve);
        Btn_myInfo=findViewById(R.id.Btn_myInfo);

        Btn_Libraryreserve.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), SeatList.class);
        }); // 도서관 예약 클릭

        Btn_myInfo.setOnClickListener(v -> {
                intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
        }); // 마이 페이지 클릭

        loginBt.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }); //로그인 클릭
    }
}

