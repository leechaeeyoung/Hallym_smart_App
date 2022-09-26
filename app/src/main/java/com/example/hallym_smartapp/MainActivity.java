package com.example.hallym_smartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
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

        bt_tab1=(Button)findViewById(R.id.bt_tab1);

    }
}