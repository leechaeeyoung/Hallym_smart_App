package com.example.hallym_smartapp.Reservation.Flag;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;
import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.TimeConvert;
import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Function.ReservatioFunction;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MyPageFrag extends Fragment {
    TextView todaySeat, timerem;
    Button extendBt, cancelBt;
    public long mTimeLeft;
    public CountDownTimer mCountDown;
    public boolean timerCheck = false;
    public static String remainTime;
    boolean flag = false;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private UserDTO userDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedI) {
        return inflater.inflate(R.layout.myseat, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("pauseTest", "dd");
        if (flag)
            mCountDown.cancel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        todaySeat = (TextView) view.findViewById(R.id.todaySeat);
        timerem = (TextView) view.findViewById(R.id.timerem);
        extendBt = (Button) view.findViewById(R.id.extendBt);
        cancelBt = (Button) view.findViewById(R.id.cancelBt);

        if (loginStatus)
            userDetail(loginId);
    }

    public void userDetail(String id) {
        Query query = databaseReference.child("User").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final UserDTO userDTO = snapshot.getValue(UserDTO.class);

                if (userDTO.isSeatState()) {
                    // ?????? ????????? ??????
                    TimeConvert timeConvert = new TimeConvert(userDTO.getRemainTime());
                    mTimeLeft = timeConvert.getDiff();
                    Log.e("?????????", Long.toString(mTimeLeft));
                    startTimer(userDTO);
                    flag = true;

                } else if (timerCheck) {
                    pauseTimer();
                }

                // myseat??? ????????????, ???????????? ?????? ???
                todaySeat.setText(userDTO.getFloorNum() + "??? ????????? " + userDTO.getSeatNum() + "??? ??????");
                timerem.setText(userDTO.getReservationDate());

                // ?????? ??????
                cancelBt.setOnClickListener(v -> {
                    mCountDown.cancel();
                    Toast.makeText(getContext(), (userDTO.getSeatNum()) + "??? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                    ReservatioFunction function = new ReservatioFunction();
                    function.deleteInfo(userDTO);

                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .remove(MyPageFrag.this)
                            .commit();
                });

                // ??????
                extendBt.setOnClickListener(v -> {
                    ReservatioFunction function = new ReservatioFunction();
                    pauseTimer();
                    function.renew(userDTO);
                });
                if (userDTO.isSeatState()) {
                    todaySeat.setVisibility(View.VISIBLE);
                    timerem.setVisibility(View.VISIBLE);
                    extendBt.setVisibility(View.VISIBLE);
                    cancelBt.setVisibility(View.VISIBLE);
                } else {
                    todaySeat.setVisibility(View.INVISIBLE);
                    timerem.setVisibility(View.INVISIBLE);
                    extendBt.setVisibility(View.INVISIBLE);
                    cancelBt.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadPost:onCancel", error.toException());
            }
        });
    }

    private void startTimer(UserDTO userDTO) {
        this.userDTO = userDTO;
        mCountDown = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                ReservatioFunction function = new ReservatioFunction();
                mTimeLeft = 0;
                timerCheck = false;
                function.deleteInfo(userDTO);
            }
        }.start();
        timerCheck = true;
    }

    private void pauseTimer() {
        mCountDown.cancel();
        timerCheck = false;
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeft / 3600000);
        int minutes = (int) (mTimeLeft % 3600000) / 60000;
        int seconds = (int) ((mTimeLeft % 3600000) % 60000) / 1000;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d ?????? %02d ??? %02d ???", hours, minutes, seconds);
        timerem.setText(timeLeftFormatted);
    }
}
