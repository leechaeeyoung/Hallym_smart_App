package com.example.hallym_smartapp.Reservation.Function;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;
import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SeatAdapter extends ListAdapter<SeatDto, SeatAdapter.MyViewHolder> {
    //List<SeatDto> seatDto = Collections.emptyList();
    Context context;
    SeatDto seatDto1;
    UserDTO userDto;
    ReserveDialog third = new ReserveDialog();

    public static long timeValue;
    public static CountDownTimer countDownTimer;

    final int floorNum = 3;
    boolean timerCheck = false;
    long mTimeLeft;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public SeatAdapter(@NonNull DiffUtil.ItemCallback<SeatDto> diffCallback) {
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

        final int seatNum = getCurrentList().get(position).getSeatNum();
        final String userId = getCurrentList().get(position).getUsedId();
        final boolean seatCheck = getCurrentList().get(position).isSeatCheck();

        holder.seatNumber.setText("" + seatNum);

        if (!seatCheck)  // 예약가능
            holder.seatNumber.setBackgroundResource(R.drawable.available_seat);

        if (seatCheck) { // 예약 불가능
            holder.seatNumber.setBackgroundResource(R.drawable.not_available_seat);
        } // 이미지

        // 좌석 클릭 시 이벤트
        holder.seatNumber.setOnClickListener(v -> {
            Log.e("test", Integer.toString(position));
            if (!seatCheck) { // 선택한 좌석이 비어 있있다면
                third.reservationDialog(
                        floorNum,
                        seatNum,
                        userDto,
                        getCurrentList(),
                        () -> notifyDataSetChanged()
                ); // 자리 예약 다이얼로그 호출
            } else {

                if (userDto.getSeatNum() == (position + 1)) // 선택한 자리가 본인 자리라면

                    third.cancelDialog(getCurrentList(), userDto);   // 자리 반환 다이얼로그 호출

                else

                    Toast.makeText(context, "이미 예약되어 있는 자리 입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // 아이템에 해당하는 뷰 홀더
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView seatNumber;
        ProgressBar pb;

//         // 테스트용 텍스트뷰
//        private final AppCompatTextView seatNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumber = itemView.findViewById(R.id.seatNumber);
            pb = itemView.findViewById(R.id.pb);
        }

        private void bind(SeatDto seatDto) {
            seatNumber.setText(String.valueOf(seatDto.getSeatNum()));
            seatNumber.setOnClickListener(view -> System.out.println("좌석 클릭"));
        }
    }

    // ListAdapter DiffUtil
    public static class SeatDiffUtil extends DiffUtil.ItemCallback<SeatDto> {
        @Override
        public boolean areItemsTheSame(@NonNull SeatDto oldItem, @NonNull SeatDto newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull SeatDto oldItem, @NonNull SeatDto newItem) {
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
}




