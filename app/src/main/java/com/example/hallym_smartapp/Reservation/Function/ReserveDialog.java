package com.example.hallym_smartapp.Reservation.Function;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.TimeConvert;
import com.example.hallym_smartapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReserveDialog extends AppCompatActivity {
    Long now;
    Date date;
    List<SeatDto> count = new ArrayList<SeatDto>();
    static boolean check = false;
    static boolean flag = true;
    public static int totalSeat = 0;
    SeatDto seatTest;
    static Context context;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("3층 좌석");
        context = this;
        setContentView(R.layout.three_floor);
        count = new ArrayList<>();
    }

    protected void onPause() {
        super.onPause();
        flag = false;
        Log.e("pauseTest", "test");
    }

    private String reservationDate() {
        SimpleDateFormat format = new SimpleDateFormat(" mm월 dd일  HH시 mm분 ss초");
        now = System.currentTimeMillis();
        date = new Date(now);
        return format.format(date);
    }

    private String displayTime() {
        SimpleDateFormat format = new SimpleDateFormat("조회일자: MM월 dd일 HH시 mm분 ss초");
        now = System.currentTimeMillis();
        date = new Date(now);
        return format.format(date);
    }

    // 예약 다이얼로그
    public void reservationDialog(final int seatNum, final int floorNum, final UserDTO userDTO, final List<SeatDto> seatDto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("예약(현재좌석: "+seatNum+"\n예약하시겠습니까?").setCancelable(false).setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ReservatioFunction.moveSeat(seatNum, floorNum, userDTO, seatDto);
                Toast.makeText(context,seatNum+"번 자리가 예약되었습니다.", Toast.LENGTH_SHORT).show();
                TimeConvert timeConvert = new TimeConvert(userDTO.getRemainTime());
                Long timeValue = timeConvert.getDiff();
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"취소",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 이동 다이얼로그
    public void moveDialog(final int seatNum, final int floorNum, final UserDTO userDTO, final List<SeatDto> seatDto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("이동(변경좌석: " + floorNum + seatNum + "번)");
        builder.setMessage(displayTime() + "\n이동하시겠습니까?");
        builder.setCancelable(false);
        builder.setPositiveButton("예", (dialog, which) -> {
            ReservatioFunction.moveSeat(seatNum, floorNum, userDTO, seatDto);
            Toast.makeText(context, "이동이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("아니요", (dialog, which) -> Toast.makeText(context, "이동 취소", Toast.LENGTH_SHORT).show());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 예약취소 다이얼로그
    public void returnDialog(final List<SeatDto>seatDto, final UserDTO userDTO){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("반납");
        builder.setMessage(displayTime()+"\n예약취소/반납하시겠습니까?");
        builder.setNegativeButton("예", (dialog, which) -> {
            Toast.makeText(context,userDTO.getSeatNum()+"번 자리가 예약 취소/반납 되었습니다.",Toast.LENGTH_SHORT).show();
            ReservatioFunction.deleteInfo(userDTO);
        });
        builder.setNegativeButton("아니요", (dialog, which) -> Toast.makeText(context,"취소되었습니다.",Toast.LENGTH_SHORT).show());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 좌석 그려주는 메소드
    private void seatSet() {
        for(int i=1; i<=90; i++){
            Query query = databaseReference.child("Floor").child(i + "seat");
            query.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    check = false;
                    seatTest = snapshot.getValue(SeatDto.class);
                    SeatDto seatDto = seatTest;

                    if(count.size()>=90)
                        count.set(seatTest.getSeatNum()-1, seatDto);
                    else
                        count.add(seatDto);
                    Log.e("seatTest", String.valueOf(seatTest.getSeatNum()));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("loadPost:onCancelled", error.toException());
                }
            });
            }
        }


    // 좌석 수 DB 생성
    private void dbCreate() {
        String key = databaseReference.child("floor").push().getKey();
        for (int i=1; i<=90; i++) {
                SeatDto seat = new SeatDto(i);
                Map<String, Object> postValues = seat.map();
                Map<String, Object> seatUpdates = new HashMap<>();
                seatUpdates.put("/Floor/" + i + "seat", postValues);
                databaseReference.updateChildren(seatUpdates);
            }
        }
    }

