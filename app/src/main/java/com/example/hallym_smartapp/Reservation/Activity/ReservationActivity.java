package com.example.hallym_smartapp.Reservation.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Flag.Fragment2;
import com.example.hallym_smartapp.Reservation.Flag.Fragment3;
import com.example.hallym_smartapp.Reservation.Flag.Fragment4;
import com.example.hallym_smartapp.Reservation.Function.SeatCnt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    // 층수마다 플래그
    private final int FRAGMENT1=1;
    private final int FRAGMENT2=2;
    private final int FRAGMENT3=3;
    private final int FRAGMENT4=4;

    private Button bt_tab1, bt_tab2, bt_tab3, bt_tab4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tab키 참조
        bt_tab1=(Button)findViewById(R.id.bt_tab1);
        bt_tab2=(Button)findViewById(R.id.bt_tab2);
        bt_tab3=(Button)findViewById(R.id.bt_tab3);
        bt_tab4=(Button)findViewById(R.id.bt_tab4);

        // 버튼 누를 때 리스너 연결
        bt_tab1.setOnClickListener(this);
        bt_tab2.setOnClickListener(this);
        bt_tab3.setOnClickListener(this);
        bt_tab4.setOnClickListener(this);
    }

    // DB 좌석 정보
    public void seatDBSet(){
        SeatCnt seatCnt = new SeatCnt(Integer.toString(100));
        Map<String, Object> postValues = seatCnt.map();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/seat_count/"+"nowSeatCount",postValues);
        databaseReference.updateChildren(childUpdates);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_tab1:
                // 2층 버튼 클릭 시 프래그 1 호출
                callFragment(FRAGMENT1);
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

        switch (fragment_no){
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
}
