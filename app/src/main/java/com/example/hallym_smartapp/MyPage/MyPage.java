package com.example.hallym_smartapp.MyPage;

import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;
import com.google.errorprone.annotations.CompileTimeConstant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;

public class MyPage extends AppCompatActivity {
    Button Btn_myInfo;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        setTitle("마이페이지");
//        if(loginStatus)
    }

    // 사용자 개인정보 조회
    public void userDetail(String id){
        Query query = databaseReference.child("User").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final UserDTO userDTO = snapshot.getValue(UserDTO.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancel", error.toException());
            }
        });
    }

}
