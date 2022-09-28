package com.example.hallym_smartapp.Reservation.Flag;


import static com.example.hallym_smartapp.R.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hallym_smartapp.R;

// {@link Fragment} subclass
public class Fragment1 extends Fragment {
    public Fragment1(){
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedI){
        return inflater.inflate(R.layout.two_floor, container,false);
    }
}
