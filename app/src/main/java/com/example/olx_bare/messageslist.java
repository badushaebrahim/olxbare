package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class messageslist  extends AppCompatActivity {
    RequestQueue queue;
    RecyclerView reses;
    RecyclerView.Adapter reseradapter;
    RecyclerView.LayoutManager reslay;
    List<msglistmodel> Liste;
    String touse;
    Button br;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msglist);
        reses=findViewById(R.id.recyclerView2);
        reslay = new LinearLayoutManager(this);
        reses.setLayoutManager(reslay);
        Liste = new ArrayList<>();
        this.getSupportActionBar().hide();
        br=findViewById(R.id.back);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(messageslist.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getdatafrom();
    }
    private void getdatafrom() {

        da n = new da();
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        touse= n.URL+"getmsglist.php?id="+me;
        JsonArrayRequest jar = new JsonArrayRequest(touse ,
                responce -> {
                    try {
                        System.out.println(responce);
                        parse_msglistmodel(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(messageslist.this, error.toString(), Toast.LENGTH_LONG).show());


        queue = Volley.newRequestQueue(messageslist.this);
        queue.add(jar);

    }

    private void parse_msglistmodel (JSONArray jarray) throws JSONException {

        for (int i = 0; i < jarray.length(); i++) {
            JSONObject jos = jarray.getJSONObject(i);
            msglistmodel l = new msglistmodel();
          //  l.setProname(jos.getString("msg"));
            l.setProid(jos.getInt("prodid"));
            l.setProname(jos.getString("prodname"));
            l.setSid(jos.getInt("senterid"));
            l.setRcid(jos.getInt("reciverid"));
            l.setSenter(jos.getString("sentername"));

            Liste.add(l);
        }
        //adapter
        //  resinf rar = new resinf(Liste, this);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        msglistadapter m = new msglistadapter(Liste,this);
        reses.setAdapter(m);
    }


    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(messageslist.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
}
