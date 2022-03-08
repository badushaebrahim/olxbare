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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class messages extends AppCompatActivity {
    EditText msg;
    String msgr;
    Button snd;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.messager);
        killapbar();
        init();

        sendact();
    }

    private void sendact() {
        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgr=msg.getText().toString().trim();
                if(!msgr.isEmpty()){
                da n = new da();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"Sentmessagebuyr.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("resok",response);
                        //response="success";
                        String nes = response.toString();
                        Log.d("resok",nes);
                        if (!response.trim().equals("sus")) {
                            Log.d("TAG", "onResponse: worked");

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
                        } else if(response.trim().equals("failure")) {
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
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }}
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
    }
}
