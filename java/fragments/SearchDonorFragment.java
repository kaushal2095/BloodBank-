package com.example.bloodbank.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bloodbank.R;
import com.example.bloodbank.viewmodels.DonorData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDonorFragment extends Fragment {

    private View view;
    private ProgressDialog pd;
    private FirebaseAuth mAuth;
    private FirebaseUser fuser;
    private FirebaseDatabase fdb;
    DatabaseReference db_ref, user_ref;

    Spinner bloodgroup, division;
    Button btnsearch;
    List<DonorData> donorItem;

    public SearchDonorFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view=inflater.inflate(R.layout.fragment_search_donor, container, false);

         pd=new ProgressDialog(getActivity());
         pd.setMessage("Loading...");
         pd.setCancelable(true);
         pd.setCanceledOnTouchOutside(false);

         mAuth=FirebaseAuth.getInstance();
         fuser=mAuth.getCurrentUser();
         fdb=FirebaseDatabase.getInstance();

        bloodgroup = view.findViewById(R.id.btngetBloodGroup);
        division = view.findViewById(R.id.btngetDivison);
        btnsearch = view.findViewById(R.id.btnSearch);

        getActivity().setTitle("Find Blood Donor");

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                donorItem = new ArrayList<>();
                donorItem.clear();
            }
        });

        return view;
    }

}
