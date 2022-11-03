package com.example.hallym_smartapp.Reservation.Flag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hallym_smartapp.R;

public class MyPageFrag extends Fragment {
    public MyPageFrag() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedI) {
        return inflater.inflate(R.layout.myseat, container, false);
    }
}
