package com.example.hallym_smartapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.hallym_smartapp.Reservation.mainPage.Fragment1;
import com.example.hallym_smartapp.Reservation.mainPage.Fragment2;
import com.example.hallym_smartapp.Reservation.mainPage.Fragment3;
import com.example.hallym_smartapp.Reservation.mainPage.Fragment4;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
    private void callFragment(int fragment_no){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment_no){
            case 1:
                // 프래그 1 호출
                Fragment1 frag1 = new Fragment1();
                transaction.replace(R.id.fragment_container, frag1);
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
}