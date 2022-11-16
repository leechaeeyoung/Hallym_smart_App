package com.example.hallym_smartapp.Reservation.Function;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import static com.example.hallym_smartapp.Reservation.Function.ReserveDialog.totalSeat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


//3층 자리 예약 test
public class SeatList extends AppCompatActivity {
    Intent intent;
    LinearLayout list_3floor;
    TextView thirdSeatNum;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("좌석 현황");
        setContentView(R.layout.reservation_main);

        list_3floor = findViewById(R.id.list_3floor);
        nowCnt();
    }
    
    // 현재 좌석 수를 나타네는 메소드
    public void nowCnt(){
        Query query = databaseReference.child("seat_cnt").child("nowSeatCnt");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SeatCnt seatCnt = snapshot.getValue(SeatCnt.class);
                String nowCnt = seatCnt.getNowSeatCnt();
                totalSeat = Integer.parseInt(seatCnt.getNowSeatCnt());

                thirdSeatNum = findViewById(R.id.thirdSeatNum);
                thirdSeatNum.setText(nowCnt);

                list_3floor.setOnClickListener(v -> {
                    intent = new Intent(getApplicationContext(),ReserveDialog.class);
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancelled",error.toException());
            }
        });

    }
}