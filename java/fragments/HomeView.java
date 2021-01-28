package com.example.bloodbank.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.BloodRequestAdapter;
import com.example.bloodbank.viewmodels.CustomUserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeView extends Fragment {

    private RecyclerView recyclerView;
    private ProgressDialog pd;
    private BloodRequestAdapter bloodRequestAdapter;
    private List<CustomUserData> postLists;
    private DatabaseReference donar_ref;
    private FirebaseAuth mAuth;

    public HomeView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pd=new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);


        View view =inflater.inflate(R.layout.fragment_home_view, container, false);

        postLists=new ArrayList<>();
        donar_ref= FirebaseDatabase.getInstance().getReference();
        bloodRequestAdapter=new BloodRequestAdapter(postLists);

        mAuth=FirebaseAuth.getInstance();
        getActivity().setTitle("Blood Point");

        recyclerView=view.findViewById(R.id.recyleposts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(bloodRequestAdapter);
        
        AddPosts();

        return view;
    }

    private void AddPosts() {
        Query allPosts=donar_ref.child("posts");
        pd.show();
        allPosts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot singlepost:dataSnapshot.getChildren()){
                        CustomUserData customUserData=singlepost.getValue(CustomUserData.class);
                        postLists.add(customUserData);
                        bloodRequestAdapter.notifyDataSetChanged();
                    }
                    pd.dismiss();
                }else{
                    Toast.makeText(getActivity(), "Database is empty now!",
                            Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });
    }

}
