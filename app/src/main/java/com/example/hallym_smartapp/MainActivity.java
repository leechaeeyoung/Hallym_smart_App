package com.example.hallym_smartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Reservation.Activity.FiveFloorActivity;
import com.example.hallym_smartapp.Reservation.Activity.FourFloorActivity;
import com.example.hallym_smartapp.Reservation.Activity.ThirdFloorActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, Splash.class));

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

