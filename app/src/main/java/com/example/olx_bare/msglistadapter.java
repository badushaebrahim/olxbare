package com.example.olx_bare;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class msglistadapter<ViewHolder1> extends RecyclerView.Adapter<msglistadapter.ViewHolder1> {
    Context context;

    msglistadapter(){

    }
    @NonNull
    @Override
    public msglistadapter.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull msglistadapter.ViewHolder1 holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
        }
    }
}
