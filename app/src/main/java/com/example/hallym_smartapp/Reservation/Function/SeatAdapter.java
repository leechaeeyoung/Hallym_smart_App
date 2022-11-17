package com.example.hallym_smartapp.Reservation.Function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;

import java.util.List;

public class SeatAdapter extends ListAdapter<String, SeatAdapter.MyViewHolder> {
    List<SeatDto> seatDto;
    Context context;
    SeatDto seatDto1;
    UserDTO userDto;
    ReserveDialog room1 = new ReserveDialog();

    final int floorNum = 3;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    // 아이템에 해당하는 뷰 홀더
    static class MyViewHolder extends RecyclerView.ViewHolder {

        // 테스트용 텍스트뷰
        private final AppCompatTextView seatNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumber = itemView.findViewById(R.id.seatNumber);
        }

        private void bind(String word) {
            seatNumber.setText(word);
            seatNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("좌석 클릭");

                }
            });
        }
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
}


