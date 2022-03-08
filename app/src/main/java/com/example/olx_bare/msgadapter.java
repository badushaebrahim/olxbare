package com.example.olx_bare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class msgadapter<ViewHolder1> extends RecyclerView.Adapter<msgadapter.ViewHolder1>{
    private final Context context;
    List<msgmodel> liste;
    public msgadapter(List<msgmodel> liste2, Context context) {

        super();
        this.liste = liste2;
        this.context = context;
    }
    @NonNull
    @Override
    public msgadapter.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msgbuble, parent, false);
        msgadapter.ViewHolder1 viewHolder = new msgadapter.ViewHolder1(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull msgadapter.ViewHolder1 holder, int position) {
        final msgmodel getDataAdapter =  liste.get(position);
        holder.tx.setText(getDataAdapter.getMsg());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView tx;
        public ViewHolder1(View view) {
            super(view);
            tx=view.findViewById(R.id.msghere);
        }
    }
}
