package com.example.hallym_smartapp.Reservation.Activity;

import static com.example.hallym_smartapp.Reservation.Function.ReserveDialog.totalSeat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Flag.MyPageFrag;
import com.example.hallym_smartapp.Reservation.Flag.Fragment2;
import com.example.hallym_smartapp.Reservation.Flag.Fragment3;
import com.example.hallym_smartapp.Reservation.Flag.Fragment4;
import com.example.hallym_smartapp.Reservation.Function.ReserveDialog;
import com.example.hallym_smartapp.Reservation.Function.SeatCnt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    LinearLayout list_3floor;
    TextView thirdSeatNum;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    // 층수마다 플래그
    private final int MyPageFrag = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;
    private final int FRAGMENT4 = 4;

    private Button bt_tab1, bt_tab2, bt_tab3, bt_tab4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_main);

        setTitle("좌석 현황");

        list_3floor = findViewById(R.id.list_3floor);
        nowCnt();

        // tab키 참조
        bt_tab1 = (Button) findViewById(R.id.bt_tab1);
        bt_tab2 = (Button) findViewById(R.id.bt_tab2);
        bt_tab3 = (Button) findViewById(R.id.bt_tab3);
        bt_tab4 = (Button) findViewById(R.id.bt_tab4);

        // 버튼 누를 때 리스너 연결
        bt_tab1.setOnClickListener(this);
        bt_tab2.setOnClickListener(this);
        bt_tab3.setOnClickListener(this);
        bt_tab4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_tab1:
                callFragment(MyPageFrag);
                break;
            case R.id.bt_tab2:
                // 3층 버튼 클릭 시 프래그 2 호출
                callFragment(FRAGMENT2);
                break;
            case R.id.bt_tab3:
                // 4층 버튼 클릭 시 프래그 3 호출
                callFragment(FRAGMENT3);
                break;
            case R.id.bt_tab4:
                // 5층 버튼 클릭 시 프래그 4 호출
                callFragment(FRAGMENT4);
                break;
        }
    }

    private void callFragment(int fragment_no) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment_no) {
            case 1:
                MyPageFrag mypage = new MyPageFrag();
                transaction.replace(R.id.fragment_container, mypage);
                transaction.commit();
                break;
            case 2:
                Fragment2 frag2 = new Fragment2();
                transaction.replace(R.id.fragment_container, frag2);
                transaction.commit();
                break;
            case 3:
                Fragment3 frag3 = new Fragment3();
                transaction.replace(R.id.fragment_container, frag3);
                transaction.commit();
                break;
            case 4:
                Fragment4 frag4 = new Fragment4();
                transaction.replace(R.id.fragment_container, frag4);
                transaction.commit();
                break;
        }
    }

    // 현재 좌석 수를 나타네는 메소드
    public void nowCnt() {
        Query query = databaseReference.child("seat_cnt").child("nowSeatCnt");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SeatCnt seatCnt = snapshot.getValue(SeatCnt.class);
                String nowCnt = seatCnt.getNowSeatCnt();

                // 처음 실행 시, ValueEventListener 이벤트가 발생하기에
                // seatCnt null 처리
                if(seatCnt==null){
                    return;
                }
                totalSeat = Integer.parseInt(seatCnt.getNowSeatCnt());

                thirdSeatNum = findViewById(R.id.thirdSeatNum);
                thirdSeatNum.setText(nowCnt);

                list_3floor.setOnClickListener(v -> {
                    intent = new Intent(getApplicationContext(), ReserveDialog.class);
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancelled", error.toException());
            }
        });
    }
    // DB 좌석 정보
    public void seatDBSet(){
        SeatCnt seatCnt = new SeatCnt(Integer.toString(90));
        Map<String, Object> postValues = seatCnt.map();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/seat_cnt/"+"nowSeatCnt",postValues);
        databaseReference.updateChildren(childUpdates);
    }
}
