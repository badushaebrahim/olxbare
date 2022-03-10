package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Editaccount extends AppCompatActivity {
   EditText name,email,password,phone,address;
   String na,em,pw,ph,ad;
   int pho;
   da n = new da();
    RequestQueue queue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaccount);
        datafunction();
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
            int me = sh.getInt("uid", 0);
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
            //Listing l = new Listing();
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
            //Liste.add(l);
           // System.out.println(l.getHead());
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
}
