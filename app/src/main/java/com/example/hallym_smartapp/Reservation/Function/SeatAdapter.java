package com.example.hallym_smartapp.Reservation.Function;

import static com.example.hallym_smartapp.Login.LoginActivity.loginId;
import static com.example.hallym_smartapp.Login.LoginActivity.loginStatus;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder>{
    private ArrayList<SeatDto> seatDto;
    private Context context;
    SeatDto seatDto1;
    UserDTO userDto;
    ReserveDialog third = new ReserveDialog();
    final int floorNum = 3;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public SeatAdapter(Context context,ArrayList<SeatDto> seatDto){
        this.seatDto = seatDto;
        this.context = context;
    }

//    public SeatAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
//        super(diffCallback);
//    }

    @NonNull
    @Override
    public SeatAdapter.SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_custom, parent, false);
        SeatViewHolder holder = new SeatViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        if(loginStatus)
            userReservationCheck(loginId);

        final int seatNum = seatDto.get(position).getSeatNum();
        final String seatUser_id = seatDto.get(position).getUsedId();
        final boolean seatCheck = seatDto.get(position).isSeatCheck();

        holder.seatNumber.setText(seatNum);

        if(!seatCheck)
            holder.seatNumber.setBackgroundResource(R.drawable.not_available_seat);
        if(seatCheck)
            holder.seatNumber.setBackgroundResource(R.drawable.available_seat);

        holder.seatNumber.setOnClickListener(new View.OnClickListener(){
            @Override
            public synchronized void onClick(View v) {
                Log.e("test",Integer.toString(position));

                if(!seatCheck)// 선택 좌석 이용가능
                    third.reservationDialog(floorNum,position,userDto,seatDto);
                else{
                    if(userDto.getSeatNum() == (position+1))
                        third.cancelDialog(seatDto,userDto);
                    else
                        Toast.makeText(context, "이미 예약되어있는 자리 입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.seatDto.size();
    }

    public class SeatViewHolder extends RecyclerView.ViewHolder{
        public TextView seatNumber;
        ProgressBar pb;
        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.seatNumber=itemView.findViewById(R.id.seatNumber);
            this.pb = itemView.findViewById(R.id.pb);
        }
    }
    public synchronized void userReservationCheck(String id){
        Log.d("DBUG","check");

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
//    // ListAdapter DiffUtil
//    public static class SeatDiffUtil extends DiffUtil.ItemCallback<String> {
//        @Override
//        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
//            return oldItem.equals(newItem);
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
//            return oldItem.equals(newItem);
//        }
//    }
}

