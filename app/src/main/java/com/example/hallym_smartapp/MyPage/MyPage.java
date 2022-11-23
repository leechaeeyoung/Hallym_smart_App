package com.example.hallym_smartapp.MyPage;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;
import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Api.ApiActivity;
import com.example.hallym_smartapp.Login.LoginActivity;
import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Activity.ReservationActivity;
import com.example.hallym_smartapp.Reservation.Function.ReservatioFunction;
import com.google.errorprone.annotations.CompileTimeConstant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.Locale;
import java.util.function.Function;

public class MyPage extends AppCompatActivity {
    TextView myIdInfo,myNameInfo;
    Button Btn_myInfo;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        setTitle("마이페이지");
        myIdInfo = (TextView)findViewById(R.id.myIdInfo);
        myNameInfo = (TextView)findViewById(R.id.myNameInfo);

        if(loginStatus)
            userDetail(loginId);

        Btn_myInfo.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // 사용자 개인정보 조회
    public void userDetail(String id){
        Query query = databaseReference.child("User").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final UserDTO userDTO = snapshot.getValue(UserDTO.class);
                // mypage에 학번, 이름뜨는 칸
                myNameInfo.setText(userDTO.getName());
                myIdInfo.setText(userDTO.getId());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancel", error.toException());
            }
        });
    }
}