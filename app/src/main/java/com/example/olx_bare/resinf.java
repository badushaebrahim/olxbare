package com.example.olx_bare;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class resinf<ViewHolder1> extends RecyclerView.Adapter<resinf.ViewHolder1>{
    private final Context context;
    List<Listing> liste;
    public resinf(List<Listing> liste, Context context) {
        super();
        this.liste = liste;
        this.context = context; }

    @NonNull
    @Override
    public resinf.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carvie, parent, false);
        resinf.ViewHolder1 viewHolder = new resinf.ViewHolder1(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull resinf.ViewHolder1 holder, int position) {
       //Toast.makeText(null,"resnif go",Toast.LENGTH_SHORT).show();
        System.out.println("resnif");
//Set data
        final Listing getDataAdapter =  liste.get(position);
        //holder.views.setText(getDataAdapter.getViews());
        holder.videoTitle.setText(getDataAdapter.getHead());
        holder.views.setText(getDataAdapter.getHead());
        //Picasso.with(context).load(getDataAdapter.getChannel_image()).into(holder.channelImage);
        Picasso.with(context).load(getDataAdapter.getLink()).into(holder.thumbnail);
        String q= getDataAdapter.getType();
        Toast.makeText(context.getApplicationContext(), ""+q, Toast.LENGTH_SHORT).show();
        if(q.equals("Product")){
        holder.im.setImageResource(R.drawable.cart);}
        else {holder.im.setImageResource(R.drawable.service);}
        //
        String pr= getDataAdapter.getExpprice();
        String  s=String.valueOf(pr);
        holder.lm.setText(s);
        holder.lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, newr.class);
                //Log.d("TAG", "onClick: "+getDataAdapter.getLid());

                intent.putExtra("head",getDataAdapter.getHead());
                intent.putExtra("lat",getDataAdapter.getLat());
                intent.putExtra("longi",getDataAdapter.getLongi());
                intent.putExtra("Details",getDataAdapter.getDetail());
                intent.putExtra("lid",getDataAdapter.getLid());
                intent.putExtra("sid",getDataAdapter.getSellerid());
                intent.putExtra("imglink",getDataAdapter.getLink());
                intent.putExtra("contact",getDataAdapter.getNumber());
                intent.putExtra("type",getDataAdapter.getType());
                intent.putExtra("address",getDataAdapter.getAddress());
                intent.putExtra("price",getDataAdapter.getExpprice());
                context.startActivity(intent);
            }});
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, newr.class);
                //Log.d("TAG", "onClick: "+getDataAdapter.getLid());

                intent.putExtra("head",getDataAdapter.getHead());
                intent.putExtra("lat",getDataAdapter.getLat());
                intent.putExtra("longi",getDataAdapter.getLongi());
                intent.putExtra("Details",getDataAdapter.getDetail());
                intent.putExtra("lid",getDataAdapter.getLid());
                intent.putExtra("sid",getDataAdapter.getSellerid());
                intent.putExtra("imglink",getDataAdapter.getLink());
                intent.putExtra("contact",getDataAdapter.getNumber());
                intent.putExtra("type",getDataAdapter.getType());
                intent.putExtra("address",getDataAdapter.getAddress());
                intent.putExtra("price",getDataAdapter.getExpprice());
                context.startActivity(intent);
            }
        });
        holder.videoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, newr.class);
                //Log.d("TAG", "onClick: "+getDataAdapter.getLid());

                intent.putExtra("head",getDataAdapter.getHead());
                intent.putExtra("lat",getDataAdapter.getLat());
                intent.putExtra("longi",getDataAdapter.getLongi());
                intent.putExtra("Details",getDataAdapter.getDetail());
                intent.putExtra("lid",getDataAdapter.getLid());
                intent.putExtra("sid",getDataAdapter.getSellerid());
                intent.putExtra("imglink",getDataAdapter.getLink());
                intent.putExtra("contact",getDataAdapter.getNumber());
                intent.putExtra("type",getDataAdapter.getType());
                intent.putExtra("address",getDataAdapter.getAddress());
                intent.putExtra("price",getDataAdapter.getExpprice());
                context.startActivity(intent);
            }
        });

        /* public void onClick() {
            //------listener onClick example method body ------
            Intent intent = new Intent(context, resinf.class);
            intent.putExtra("head",getDataAdapter.getLid());
            context.startActivity(intent);
        }*/

    }

    @Override
    public int getItemCount() {
        return liste.size();
        //return 0;
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView thumbnail,channelImage,im;
        TextView videoTitle, views,lm;
        public ViewHolder1(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            //channelImage = itemView.findViewById(R.id.channel_image);
            videoTitle = itemView.findViewById(R.id.video_title);
           im= itemView.findViewById(R.id.imr);
            views = itemView.findViewById(R.id.det);
            lm=itemView.findViewById(R.id.lm);
            Log.d("TAG", "ViewHolder1: ok");


        }

    }

}