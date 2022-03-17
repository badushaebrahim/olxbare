package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class messages extends AppCompatActivity {
    EditText msg;
    String msgr,touse;
    Button snd;
    int rid,pid;

   public  List<msgmodel> Liste;
    RecyclerView reses;
    //  RecyclerView.Adapter reseradapter;
    RecyclerView.LayoutManager reslay;

    RequestQueue queue;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.messager);
        killapbar();
        init();
        getdatafrom();
        adddatat();
        sendact();

    }

    private void adddatat() {
        reses=findViewById(R.id.recyclerView2);
        reslay = new LinearLayoutManager(this);
        reses.setLayoutManager(reslay);
        Liste = new ArrayList<>();
        getdatafrom();
    }

    private void getdatafrom() {
        JsonArrayRequest jar = new JsonArrayRequest(touse ,
                responce -> {
                    try {
                        System.out.println(responce);
                        parse_msg(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(messages.this, error.toString(), Toast.LENGTH_LONG).show());


        queue = Volley.newRequestQueue(messages.this);
        queue.add(jar);

    }
    private void parse_msg (JSONArray jarray) throws JSONException {

        for (int i = 0; i < jarray.length(); i++) {
            JSONObject jos = jarray.getJSONObject(i);
            msgmodel l = new msgmodel();
            l.setMsg(jos.getString("msg"));
            l.setPid(jos.getInt("prodid"));
            l.setPname(jos.getString("prodname"));
            l.setSid(jos.getInt("senterid"));
            l.setRid(jos.getInt("reciverid"));
            l.setSentername(jos.getString("sentername"));

            Liste.add(l);
        }
        //adapter
      //  resinf rar = new resinf(Liste, this);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
            msgadapter m = new msgadapter(Liste,this,me);
        reses.setAdapter(m);
    }

    private void sendact() {
        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgr=msg.getText().toString().trim();
                if(!msgr.isEmpty()){
                da n = new da();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"sentmessagebuyr.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("resok",response);
                        //response="success";
                        String nes = response.toString();
                        Log.d("resok",nes);
                        if (response.trim().equals("oks")) {
                            Log.d("TAG", "onResponse: worked");
                            msg.setText("");
                                adddatat();
                           // SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                            // Creating an Editor object to edit(write to the file)
                            /*SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putInt("uid", Integer.parseInt(response.trim()));
                            myEdit.commit();
                            Intent intent = new Intent(messages.this, MainActivity.class);
                            //intent.putExtra("me",EMAIL);
                            // intent.putExtra("pwd",PASSWORD);
                            startActivity(intent);
                            finish();*/
                        } else if(response.trim().equals("fail")) {
                            Log.d("TAG", "onResponse: not in bro ");
                            Toast.makeText(messages.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(messages.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("msg", msgr);
                        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
                        int me = sh.getInt("uid", 0);
                        String me1=Integer.toString(me);
                        data.put("senter", me1);
                        int reciver= getIntent().getExtras().getInt("sid");
                        data.put("reciver", String.valueOf(reciver));
                        int prodno = getIntent().getExtras().getInt("lid");
                        data.put("prodno", String.valueOf(prodno));
                        String proname=getIntent().getExtras().getString("head");
                        data.put("prodname",proname);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
            else{
                    msg.requestFocus();
                    msg.setError("please add message");
                }
            }
        });
    }

    private void killapbar() {try {
        this.getSupportActionBar().hide();
    }
    catch (Exception e){
        Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();}
    }

    public void init(){
        msg=findViewById(R.id.msg);
        snd=findViewById(R.id.sendbut);
        rid=getIntent().getExtras().getInt("sid");
        pid=getIntent().getExtras().getInt("lid");
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        da n = new da();
        touse=n.URL+"getmsg.php?me="+me+"&rid="+rid+"&pid="+pid;
    }

    void gohome(){
        Intent intent = new Intent(messages.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    gohome();
    }
}
