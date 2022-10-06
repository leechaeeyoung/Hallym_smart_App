package com.example.hallym_smartapp.Reservation.Function;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReserveManage extends AppCompatActivity {
     Intent intent;

     private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
     private DatabaseReference databaseReference = firebaseDatabase.getReference();

     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setTitle("도서관 좌석 현황");
     }
     // 해당 층의 좌석수 현황 조회
    public void currentCnt(){
//         Query query = databaseReference.child("seat_cnt").child("nowSeatCnt");

    }
}

