package com.example.hallym_smartapp.Reservation.Function;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;
import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.MyPage.TimeConvert;
import com.example.hallym_smartapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SeatAdapter extends ListAdapter<String, SeatAdapter.MyViewHolder> {
    List<SeatDto> seatDto;
    Context context;
    SeatDto seatDto1;
    UserDTO userDto;
    ReserveDialog room1 = new ReserveDialog();

    public static long timeValue;

    final int floorNum = 3;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public SeatAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_custom, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.bind(getItem(position));

        if (loginStatus)
            idReservationCheck(loginId);

        final int seatNum = seatDto.get(position).getSeatNum();
        final String userId = seatDto.get(position).getUsedId();
        final boolean seatCheck = seatDto.get(position).isSeatCheck();

        holder.seatNumber.setText("" + seatNum);

        if (!seatCheck)
            holder.seatNumber.setBackgroundResource(R.drawable.available_seat);

        if (seatCheck) {
            holder.seatNumber.setBackgroundResource(R.drawable.notavailable_seat);
        } // 이미지

        holder.seatNumber.setOnClickListener(v -> {
            Log.e("test", Integer.toString(position));
            if (!seatCheck) { // 선택한 좌석이 비어 있있다면

                if (userDto.isSeatState()) {   // 사용자가 다른 자리에 이미 예약되어 있다면

                    room1.moveDialog(position, floorNum, userDto, seatDto);   // 자리 이동 다이얼로그 호출

                } else

                    room1.reservationDialog(position, floorNum, userDto, seatDto);    // 자리 예약 다이얼로그 호출

            } else {

                if (userDto.getSeatNum() == (position + 1))     // 선택한 자리가 본인 자리라면

                    room1.cancelDialog(seatDto, userDto);                 // 자리 반환 다이얼로그 호출

                else

                    Toast.makeText(context, "이미 예약되어 있는 자리 입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // 아이템에 해당하는 뷰 홀더
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView seatNumber;
        ProgressBar pb;

//         // 테스트용 텍스트뷰
//        private final AppCompatTextView seatNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumber = itemView.findViewById(R.id.seatNumber);
            pb = itemView.findViewById(R.id.pb);
        }

        private void bind(String word) {
            seatNumber.setText(word);
            seatNumber.setOnClickListener(view -> System.out.println("좌석 클릭"));
        }
    }

    @Override
    public int getItemCount() {
        return this.seatDto.size();
    }

    // ListAdapter DiffUtil
    public static class SeatDiffUtil extends DiffUtil.ItemCallback<String> {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    }

    public synchronized void idReservationCheck(String id) {
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
}


