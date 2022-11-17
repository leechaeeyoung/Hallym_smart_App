package com.example.hallym_smartapp.Reservation.Activity;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;
import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.TimeConvert;
import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Function.ReserveDialog;
import com.example.hallym_smartapp.Reservation.Function.SeatDto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class CountAdapter extends RecyclerView.Adapter<CountAdapter.MyViewHolder> {

    List<SeatDto> seatDto;
    Context context;
    SeatDto seatDto1;
    UserDTO userDto;
    ReserveDialog room1 = new ReserveDialog();

    public static long timeValue;
    public static CountDownTimer mCountDownTimer1;

    boolean timerCheck = false;
    long mTimeLeft;

    final int floorNum = 3;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    public CountAdapter(Context c, List<SeatDto> seatDto) {
        this.seatDto = seatDto;
        this.context = c;
    }

    public CountAdapter() {
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_custom, viewGroup, false);
        return new MyViewHolder(v);
    }


    @Override
    public synchronized void onBindViewHolder(final CountAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int seat) {

        if (loginStatus)
            userReservationCheck(loginId);

        final int seatNum = seatDto.get(seat).getSeatNum();
        final String userId = seatDto.get(seat).getUsedId();
        final boolean seatCheck = seatDto.get(seat).isSeatCheck();

        myViewHolder.seatNumber.setText("" + seatNum);

        if (!seatCheck)
            myViewHolder.seatNumber.setBackgroundResource(R.drawable.available_seat);

        if (seatCheck) {
            myViewHolder.seatNumber.setBackgroundResource(R.drawable.notavailable_seat);

        } // 이미지


        myViewHolder.seatNumber.setOnClickListener(new View.OnClickListener() {   // 좌석 클릭시 이벤트

            @Override
            public synchronized void onClick(View v) {   // 좌석 클릭시 이벤트
                Log.e("test", Integer.toString(seat));

                if (!seatCheck) { // 선택한 좌석이 비어 있있다면

                    if (userDto.isSeatState()) {   // 사용자가 다른 자리에 이미 예약되어 있다면

                        room1.moveDialog(seat, floorNum, userDto, seatDto);   // 자리 이동 다이얼로그 호출

                    } else

                        room1.reservationDialog(seat, floorNum, userDto, seatDto);    // 자리 예약 다이얼로그 호출

                } else {

                    if (userDto.getSeatNum() == (seat + 1))     // 선택한 자리가 본인 자리라면

                        room1.cancelDialog(seatDto, userDto);                 // 자리 반환 다이얼로그 호출

                    else

                        Toast.makeText(context, "이미 예약되어 있는 자리 입니다.", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return this.seatDto.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView seatNumber;
        ProgressBar pb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumber = (TextView) itemView.findViewById(R.id.seatNumber);
            pb = itemView.findViewById(R.id.pb);
        }
    }


    public synchronized void userReservationCheck(String id) {
        Log.d("DBUG", "check");

        Query query = databaseReference.child("User").child(id);
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDto = dataSnapshot.getValue(UserDTO.class);
                Log.d("DBUG", "success");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DBUG", "cancelled");
            }
        });
    }

    public  void userReservationCheck1(final int position) {

        Log.d("input id", Integer.toString(position));

        Query query = databaseReference.child("Room").child(Integer.toString(position + 1) + "seat");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DBUG1", "success");
                seatDto1 = dataSnapshot.getValue(SeatDto.class);
                Log.e("시간", "dddddd"+seatDto1.getRemainTime());
                TimeConvert timeConvert = new TimeConvert(seatDto1.getRemainTime());
                timeValue = timeConvert.getDiff();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public  void userReservationCheck3(final String id) {

        Log.d("input id", id);

        Query query = databaseReference.child("User").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDto = dataSnapshot.getValue(UserDTO.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // ListAdapter DiffUtil
    public static class SeatInit extends DiffUtil.ItemCallback<String> {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    }

    public String updateCountDownText(Long mTimeLeftInMillis1) {
        int hours = (int) (mTimeLeftInMillis1 / 3600000);
        int minutes = (int) (mTimeLeftInMillis1 % 3600000) / 60000;
        int seconds = (int) ((mTimeLeftInMillis1 % 3600000) % 60000) / 1000;

        String timeLeftFormatted1 = String.format(Locale.getDefault(), "%02d : %02d", hours, minutes);
        Log.e("TimerTest", timeLeftFormatted1);
        return timeLeftFormatted1;
    }

}