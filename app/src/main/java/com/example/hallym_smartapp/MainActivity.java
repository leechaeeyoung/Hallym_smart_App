package com.example.hallym_smartapp;

import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Reservation.Activity.FiveFloorActivity;
import com.example.hallym_smartapp.Reservation.Activity.FourFloorActivity;
import com.example.hallym_smartapp.Reservation.Activity.ThirdFloorActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    Button Btn_Libraryreserve, Btn_myInfo;
    Intent intent;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBackPressed();

        startActivity(new Intent(this, Splash.class));

        Btn_Libraryreserve =findViewById(R.id.Btn_Libraryreserve);
        Btn_myInfo=findViewById(R.id.Btn_myInfo);

//        Btn_Libraryreserve.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(getApplicationContext(), )
//            }
//        }); // 좌석 설정 필요

//        Btn_myInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    intent = new Intent(getApplicationContext(), My)
//                    startActivity(intent);
//            }
//        }); // 마이 페이지 설정



        // TEST code
        Button btn5 = (Button) findViewById(R.id.fiveFloor);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), FiveFloorActivity.class);
                startActivity(intent);
            }
        });

        Button btn4 = (Button) findViewById(R.id.fourFloor);
        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), FourFloorActivity.class);
                startActivity(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.thirdFloor);
        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ThirdFloorActivity.class);
                startActivity(intent);
            }
        });
    }
}

