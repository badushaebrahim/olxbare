package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class msglistadapter<ViewHolder1> extends RecyclerView.Adapter<msglistadapter.ViewHolder1> {
    Context context;
    List<msglistmodel> liste;
    msglistadapter(List<msglistmodel> liste2, Context context){
    this.liste=liste2;
    this.context=context;
    }
    @NonNull
    @Override
    public msglistadapter.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msgwelcome, parent, false);
        msglistadapter.ViewHolder1 viewHolder = new msglistadapter.ViewHolder1(view);
        System.out.print("At adapter on create ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull msglistadapter.ViewHolder1 holder, int position) {
        final msglistmodel getDataAdapter =  liste.get(position);
        String sen = getDataAdapter.getSenter();
        String ab="about "+getDataAdapter.getProname();
        holder.t1.setText(sen);
        holder.t2.setText(ab);
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "onclick at respond", Toast.LENGTH_LONG).show();
                Intent k = new Intent(context,messages.class);
                k.putExtra("head",getDataAdapter.getProname());

                k.putExtra("sid",getDataAdapter.getSid());
                k.putExtra("lid",getDataAdapter.getProid());
                context.startActivity(k);
            }
        });
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView t1,t2;
        Button b;
        public ViewHolder1(@NonNull View itemView) {

            super(itemView);
            t1=itemView.findViewById(R.id.textView12);
            t2=itemView.findViewById(R.id.textView10);
            b= itemView.findViewById(R.id.button4);
        }
    }
}
