package com.example.bloodbank.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.viewmodels.DonorData;
import com.example.bloodbank.viewmodels.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievmentsView extends Fragment {

    private int cur_day, cur_month, cur_year, day, month, year, totday;
    private Calendar calendar;
    private ProgressDialog pd;
    DatabaseReference donar_ref, user_ref;
    FirebaseAuth mAuth;

    private TextView totalDonate, lastDonate, nextDonate, donateInfo;

    private String lastDate;

    private String[] bloodgroup, divisionlist;

    private View view;
    private Button yes;
    private LinearLayout yesno;


    public AchievmentsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

           view=inflater.inflate(R.layout.fragment_achievments_view, container, false);

       getActivity().setTitle("Achievement");

       pd=new ProgressDialog(getActivity());
       pd.setMessage("Loading...");
       pd.setCancelable(true);
       pd.setCanceledOnTouchOutside(false);


        lastDonate = view.findViewById(R.id.setLastDonate);
        totalDonate = view.findViewById(R.id.settotalDonate);
        donateInfo = view.findViewById(R.id.donateInfo);

        divisionlist=getResources().getStringArray(R.array.division_list);
        bloodgroup=getResources().getStringArray(R.array.Blood_Group);

        mAuth=FirebaseAuth.getInstance();
        donar_ref= FirebaseDatabase.getInstance().getReference("donars");
        user_ref=FirebaseDatabase.getInstance().getReference("users");

        Query userQ=user_ref.child(mAuth.getCurrentUser().getUid());

        try{
            pd.show();
            userQ.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if(dataSnapshot.exists()){
                            final UserData userData=dataSnapshot.getValue(UserData.class);
                            final int getdiv=userData.getDivision();
                            final int getbg=userData.getBloodGroup();

                            Query donar=donar_ref.child(divisionlist[getdiv]).child(bloodgroup[getbg]).child(mAuth.getCurrentUser().getUid());

                            donar.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    if(dataSnapshot.exists()){
                                        final DonorData donorData=dataSnapshot.getValue(DonorData.class);
                                        totalDonate.setText(donorData.getTotalDonate()+"times");
                                        if(donorData.getTotalDonate()==0){
                                            lastDate="01/01/2001";
                                            lastDonate.setText("Do not donate yet !");
                                        }else{
                                            lastDate=donorData.getLastDonate();
                                            lastDonate.setText(donorData.getLastDonate());
                                        }
                                        totday = 0;
                                        nextDonate = view.findViewById(R.id.nextDonate);
                                        yesno = view.findViewById(R.id.yesnolayout);

                                        if(lastDate.length() !=0){

                                            int cnt = 0;
                                            int tot = 0;

                                        }

                                    }
                                            pd.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                                    pd.dismiss();
                     }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }catch(Exception e){

        }


        return view;
    }

}
