package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MyListings extends AppCompatActivity {
    RecyclerView reses;
    RecyclerView.Adapter reseradapter;
    RecyclerView.LayoutManager reslay;
    List<Listing> Liste;
    RequestQueue queue;
    //String URL = "http://192.168.0.104/andro/getdata.php";
    da n = new da();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listi);
        killactionbar();

    }
        public void  killactionbar(){ this.getSupportActionBar().hide();}

    public void parse_data (JSONArray jarray) throws JSONException {
        int i=0;
        for (i = 0; i < jarray.length(); i++) {
            JSONObject jos = jarray.getJSONObject(i);
            Listing l = new Listing();
            l.setLink(jos.getString("imagelink"));
            l.setDetail(jos.getString("long_details"));
            l.setHead(jos.getString("Listing_title"));
            l.setLat((float) jos.getDouble("latitude"));
            l.setLongi((float) jos.getDouble("longi"));
            l.setSellerid(jos.getInt("sellerid"));
            l.setNumber(jos.getString("contactno"));
            l.setAddress(jos.getString("address"));
            l.setType(jos.getString("type"));
            l.setLid(jos.getInt("Lid"));
            l.setprice(jos.getInt("expected_price"));
            Liste.add(l);
            System.out.println(l.getHead());
        }

}













public void  goback(View V ){
    Intent intent = new Intent(MyListings.this, MainActivity.class);
    //intent.putExtra("lname",header);
    //intent.putExtra("rid",);
    startActivity(intent);
    finish();
}

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(MyListings.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
}
