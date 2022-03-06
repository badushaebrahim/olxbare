package com.example.olx_bare;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.olx_bare.R;
import com.example.olx_bare.resinf;
import com.squareup.picasso.Picasso;

import java.util.List;

public class resinf2<ViewHolder1> extends RecyclerView.Adapter<resinf2.ViewHolder1>{
    private final Context context;
    List<Listing> liste;
    public resinf2(List<Listing> liste2, Context context) {

        super();
        this.liste = liste2;
        this.context = context;
    }
    @NonNull
    @Override
    public resinf2.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carvie, parent, false);
        System.out.print("at resnif 2 on create");
        resinf2.ViewHolder1 viewHolder = new resinf2.ViewHolder1(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull resinf2.ViewHolder1 holder, int position) {
        final Listing getDataAdapter =  liste.get(position);
        //holder.views.setText(getDataAdapter.getViews());
        new da("at resnif2");
        holder.videoTitle.setText(getDataAdapter.getHead());
        holder.views.setText(getDataAdapter.getHead());
        //Picasso.with(context).load(getDataAdapter.getChannel_image()).into(holder.channelImage);
        Picasso.with(context).load(getDataAdapter.getLink()).into(holder.thumbnail);
        String q= getDataAdapter.getType();
        Toast.makeText(context.getApplicationContext(), ""+q, Toast.LENGTH_SHORT).show();
        if(q.equals("Product")){
            new da("product at if ");
            holder.im.setImageResource(R.drawable.cart);}
        else {holder.im.setImageResource(R.drawable.service);}
        //
        int pr= getDataAdapter.getExpprice();
        String  s=String.valueOf(pr);
        holder.lm.setText("More..");
        holder.lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, newr.class);
                Log.d("TAG", "onClick: "+getDataAdapter.getLid());

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
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView thumbnail,channelImage,im;
        TextView videoTitle, views,lm;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            //channelImage = itemView.findViewById(R.id.channel_image);
            videoTitle = itemView.findViewById(R.id.video_title);
            views = itemView.findViewById(R.id.det);
            lm=itemView.findViewById(R.id.lm);
        }
    }
}