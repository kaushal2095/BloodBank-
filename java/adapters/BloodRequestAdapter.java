package com.example.bloodbank.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.viewmodels.CustomUserData;

import java.util.ArrayList;
import java.util.List;

public class BloodRequestAdapter extends RecyclerView.Adapter<BloodRequestAdapter.PostHolder> {

    private List<CustomUserData> postLists;

    public BloodRequestAdapter(List<CustomUserData> postLists) {
        this.postLists=postLists;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listitem= LayoutInflater.from(parent.getContext()).inflate(R.layout.request_list_item,parent,false);
        return new PostHolder(listitem);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        if(position%2==0){
            holder.itemView.setBackgroundColor(Color.parseColor("#C13F31"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        CustomUserData customUserData=postLists.get(position);
        holder.Name.setText("Posted by: "+customUserData.getName());
        holder.Address.setText("From: "+customUserData.getAddress()+", "+customUserData.getDivision());
        holder.bloodgroup.setText("Needs "+customUserData.getBloodGroup());
        holder.posted.setText("Posted on:"+customUserData.getTime()+", "+customUserData.getDate());
        holder.contact.setText(customUserData.getContact());

    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        TextView Name, bloodgroup, Address, contact, posted;
        public PostHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.reqstUser);
            contact = itemView.findViewById(R.id.targetCN);
            bloodgroup = itemView.findViewById(R.id.targetBG);
            Address = itemView.findViewById(R.id.reqstLocation);
            posted = itemView.findViewById(R.id.posted);
        }
    }
}
