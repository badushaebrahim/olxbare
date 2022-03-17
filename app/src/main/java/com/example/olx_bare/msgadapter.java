package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class msgadapter<ViewHolder1> extends RecyclerView.Adapter<msgadapter.ViewHolder1>{
    private final Context context;
    List<msgmodel> liste;
    int me ;
    public msgadapter(List<msgmodel> liste2, Context context,int me) {

        super();
        this.me=me;
        this.liste = liste2;
        this.context = context;
    }
    @NonNull
    @Override
    public msgadapter.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msgbuble, parent, false);
        msgadapter.ViewHolder1 viewHolder = new msgadapter.ViewHolder1(view);
        System.out.print("At adapter on create ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull msgadapter.ViewHolder1 holder, int position) {
        final msgmodel getDataAdapter =  liste.get(position);
        System.out.print("At adapter on create ");
        int csk=getDataAdapter.getSid();
       if(csk==me) {
          // holder.card.setCardBackgroundColor(Color.parseColor("#3be5eb"));
           holder.card.setCardBackgroundColor(Color.BLUE);
       }else {
          // holder.card.setCardBackgroundColor(Color.parseColor("#35f079"));
           holder.card.setForegroundGravity(Gravity.LEFT);
       }holder.tx.setText(getDataAdapter.getMsg());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView tx;
        CardView card;
        public ViewHolder1(View view) {
            super(view);
            card = view.findViewById(R.id.cardu);
            tx=view.findViewById(R.id.msghere);
        }
    }
}
