package com.example.hallym_smartapp.MyPage;

import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Activity.ThirdFloorActivity;
import com.google.errorprone.annotations.CompileTimeConstant;
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
    Button Btn_myInfo,extendBt,cancelBt;
    TextView myIdInfo,myNameInfo,todaySeat,timerem;
    public long mTimeLeft;
    public CountDownTimer mCountDown;
    public static String remainTimes;
    public boolean timerCheck = false;
    boolean flag = false;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        setTitle("마이페이지");
        myIdInfo = (TextView)findViewById(R.id.myNameInfo);
        myNameInfo = (TextView)findViewById(R.id.myNameInfo);
        todaySeat = (TextView)findViewById(R.id.todaySeat);
        timerem = (TextView)findViewById(R.id.timerem);
        Btn_myInfo = (Button)findViewById(R.id.Btn_myInfo);
    }

    // 사용자 개인정보 조회
    public void userDetail(String id){
        Query query = databaseReference.child("User").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final UserDTO userDTO = snapshot.getValue(UserDTO.class);
                if(userDTO.isSeatState()){
                    // 예약 테스트 코드
                    TimeConvert timeConvert=new TimeConvert(userDTO.getRemainTime());
                    mTimeLeft = timeConvert.getDiff();
                    Log.e("강한림",Long.toString(mTimeLeft));
                    startTimer(userDTO);
                    flag=true;

                } else if(timerCheck){
                    pauseTimer();
                }
                // mypage에 학번, 이름뜨는 칸
                myNameInfo.setText(userDTO.getName());
                myIdInfo.setText(userDTO.getId());
                // myseat에 좌석정보, 남은시간 뜨는 칸
                todaySeat.setText(userDTO.getFloorNum()+"층 열람실 "+userDTO.getRowNames()+userDTO.getRowIndex());
                timerem.setText(userDTO.getRemainTime());

                // 연장
                extendBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseTimer();
                        //dao 설정
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancel", error.toException());
            }
        });
    }

    private void pauseTimer() {
        mCountDown.cancel();
        timerCheck=false;
    }

    private void startTimer(UserDTO userDTO) {
        mCountDown = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                ThirdFloorActivity thirdActivity = new ThirdFloorActivity();
                mTimeLeft=0;
                timerCheck=false;
//                thirdActivity.returnSeat(userDTO);
            }
        }.start();
        timerCheck=true;
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeft/ 3600000);
        int minutes = (int) (mTimeLeft % 3600000) / 60000;
        int seconds = (int) ((mTimeLeft % 3600000) % 60000) / 1000;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d 시간 %02d 분 %02d 초", hours, minutes, seconds);
        timerem.setText(timeLeftFormatted);
    }

}
