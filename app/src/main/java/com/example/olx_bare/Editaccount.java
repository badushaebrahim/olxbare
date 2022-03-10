package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Editaccount extends AppCompatActivity {
   EditText name,email,password,phone,address;
   String na,em,pw,ph,ad;
   String una,uem,uad,uph,upw;
   int pho,me;
   da n = new da();
    RequestQueue queue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaccount);
        //remove action bar
        this.getSupportActionBar().hide();
        //function populate the edit text with neede values
        datafunction();
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        me = sh.getInt("uid", 0);
    }


        void init(){
        name=findViewById(R.id.Nameed);
        email=findViewById(R.id.Emailed);
        password=findViewById(R.id.Passworded);
        phone=findViewById(R.id.Numbered);
        address= findViewById(R.id.Addressed);
        }
        void datafunction(){
        init();
            @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
            me = sh.getInt("uid", 0);
            JsonArrayRequest jar = new JsonArrayRequest(n.URL + "getacc.php?uid="+me,
                    responce -> {
                        System.out.println(responce);
                        try {
                            parse_val(responce);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show());


            queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(jar);
        }

    private void parse_val(JSONArray jarray) throws JSONException {
        int i=0;
        JSONObject jos = null;
        for (i = 0; i < jarray.length(); i++) {

            try {
                jos = jarray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            na =jos.getString("name");
            name.setText(na);
            ad=jos.getString("address");
            address.setText(ad);
            em = jos.getString("email");
            email.setText(em);
            pw=jos.getString("password");
            password.setText(pw);
            ph=jos.getString("Number");
            phone.setText(ph);

    }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Editaccount.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
    public void backhomes(View v){
        Intent intent = new Intent(Editaccount.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }

    public void getedi(){
        una=name.getText().toString().trim();
        uem=email.getText().toString().trim();
        upw=password.getText().toString().trim();
        uph=phone.getText().toString().trim();
        uad=address.getText().toString();

    }


    public void edito(View v){
        getedi();



        if (!uem.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            Toast.makeText(getApplicationContext(), "62", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            email.setError("please enter valid email");

        }
        else if (upw.length()<8) {
            Toast.makeText(getApplicationContext(), "66", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            password.setError("Please add password with atleast 8 characters");
        }
        else if (upw.isEmpty()){
            password.requestFocus();
            password.setError("Empty Password");
            Toast.makeText(getApplicationContext(), "No Password", Toast.LENGTH_SHORT).show();
        }
        else if (uem.isEmpty()){
            email.requestFocus();
            email.setError("Empty Email");
            Toast.makeText(getApplicationContext(), "No Email is empty", Toast.LENGTH_SHORT).show();
        }
        else if (uph.isEmpty()){
            phone.requestFocus();
            phone.setError("Empty Phone Number");
            Toast.makeText(getApplicationContext(), "No Phone Number", Toast.LENGTH_SHORT).show();
        }else if (uad.isEmpty()){
            address.requestFocus();
            address.setError("Empty Address");
            Toast.makeText(getApplicationContext(), "No address", Toast.LENGTH_SHORT).show();
        }
        else if (una.isEmpty()){
            email.requestFocus();
            email.setError("Empty Name");
            Toast.makeText(getApplicationContext(), "No Name", Toast.LENGTH_SHORT).show();
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL + "editacc.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String TAG = "regisrer";
                    if (response.trim().equals("success")) {
                        Log.d(TAG, "onResponse: uss");
                        Toast.makeText(getApplicationContext(), "Update OK", Toast.LENGTH_LONG).show();
                        Intent i2 = new Intent(Editaccount.this,
                                MainActivity.class);
                        startActivity(i2);
                            finish();

                    } else if (response.trim().equals("failure")) {
                        Log.d(TAG, "onResponse: fil");


                    }
                    Log.d(TAG, "onResponse: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", una);
                    data.put("email", uem);
                    data.put("password", upw);
                    data.put("address", uad);
                    data.put("number", uph);
                    data.put("ids", String.valueOf(me));
                    return data;
                }
            };
            //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            queue.add(stringRequest);
    }


    }












}
