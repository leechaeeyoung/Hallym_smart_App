package com.example.hallym_smartapp.Reservation.Function;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.R;
import com.murgupluoglu.seatview.Seat;
import com.murgupluoglu.seatview.SeatView;
import com.murgupluoglu.seatview.SeatViewListener;
import com.murgupluoglu.seatview.extensions.DebugExtension;

import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;

public class SeatActivity extends AppCompatActivity {
//    protected void onCreate(Bundle savedInstanceState) {
//        SeatView seatView = null;
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.five_floor);
//
//        seatView.seatViewListener = new SeatViewListener() {
//            @Override
//            public boolean canSelectSeat(@NonNull Seat seat, @NonNull HashMap<String, Seat> hashMap) {
//                return false;
//            }
//
//            @Override
//            public void seatReleased(@NonNull Seat seat, @NonNull HashMap<String, Seat> hashMap) {
//                Toast.makeText(this,"취소된 좌석: "+releasedSeat.seatName,
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void seatSelected(@NonNull Seat seat, @NonNull HashMap<String, Seat> hashMap) {
//                Toast.makeText(this, "선택된 좌석: "+selectedSeat.seatName,
//                        Toast.LENGTH_SHORT).show();
//            }
//        };
}
