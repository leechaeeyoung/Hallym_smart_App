package com.example.hallym_smartapp.Reservation.Flag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Activity.CountAdapter;
import com.example.hallym_smartapp.Reservation.Function.SeatAdapter;
import com.example.hallym_smartapp.Reservation.Function.SeatDto;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    Button bt_tab2;
    // 리사이클러뷰 추가
    RecyclerView recyclerView;
    // 리사클러뷰 어댑터 추가
    SeatAdapter seatAdapter;

    GridLayoutManager layout;

    public Fragment2(){
    }

    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedI){
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.third_floor, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    // 리사이클러뷰 초기화
    private void initRecyclerView() {
        recyclerView = requireView().findViewById(R.id.recyclerView);
        layout = new GridLayoutManager(getActivity(), 5);
        seatAdapter = new SeatAdapter(new SeatAdapter.SeatDiffUtil());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(seatAdapter);
    }
}