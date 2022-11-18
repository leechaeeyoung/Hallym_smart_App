package com.example.hallym_smartapp.MyPage;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.hallym_smartapp.Reservation.Activity.CountAdapter;
import com.example.hallym_smartapp.Reservation.Function.ReservatioFunction;
import com.example.hallym_smartapp.Reservation.Function.SeatAdapter;

import java.util.Locale;

public class TimeTest {
    boolean timerCheck = false;
    long mTimeLeft;
    CountDownTimer mCountDown;
    Context context;
    SeatAdapter.MyViewHolder holder;

    public TimeTest(SeatAdapter.MyViewHolder holder, Context context, long mTimeLeft){
        this.holder= holder;
        this.context = context;
        this.mTimeLeft = mTimeLeft;
    }

    public void startTimer() {
        mCountDown = new CountDownTimer(mTimeLeft, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountDownText(holder);
            }

            @Override
            public void onFinish() {
                ReservatioFunction function = new ReservatioFunction();
                mTimeLeft = 0;
                timerCheck = false;
            }
        }.start();
        timerCheck = true;
    }


    public void pauseTimer() {
        mCountDown.cancel();
        timerCheck = false;
    }


    public void updateCountDownText(SeatAdapter.MyViewHolder holder) {
        int hours = (int) (mTimeLeft / 3600000);
        int minutes = (int) (mTimeLeft % 3600000) / 60000;
        int seconds = (int) ((mTimeLeft % 3600000) % 60000) / 1000;

        Log.e("TimerTest", "test1");
        String timeLeftFormatted1 = String.format(Locale.getDefault(), "%02d : %02d", hours, minutes);
        holder.seatNumber.setText(timeLeftFormatted1);
    }
}
