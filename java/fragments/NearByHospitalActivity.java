package com.example.bloodbank.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByHospitalActivity extends Fragment {


    public NearByHospitalActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_near_by_hospital, container, false);
    }

}
