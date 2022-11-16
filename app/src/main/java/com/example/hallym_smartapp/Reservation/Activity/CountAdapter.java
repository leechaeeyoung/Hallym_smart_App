package com.example.hallym_smartapp.Reservation.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hallym_smartapp.Login.UserDTO;
import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Function.SeatDto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CountAdapter {
    List<SeatDto> seatDto;
    Context context;
    SeatDto seatDto1;
    UserDTO userDto;
    ThreeFloorActivity threeFloor = new ThreeFloorActivity();

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

}
