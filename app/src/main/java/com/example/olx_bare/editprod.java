package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class editprod extends AppCompatActivity {
        EditText name,price,detail;
        String na,de;
        int pr;
    RequestQueue queue;
    int me;
    da n = new da();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
            setContentView(R.layout.editpord);
        getdata();
    }
    void init(){
        name=findViewById(R.id.nameu);
        price=findViewById(R.id.priceu);
        detail=findViewById(R.id.desigu);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        me = sh.getInt("uid", 0);

    }
void getdata(){
        init();


            na =getIntent().getExtras().getString("head");
            name.setText(na);
            pr=getIntent().getExtras().getInt("price");
            price.setText(pr);
            de = getIntent().getExtras().getString("Details");
            detail.setText(de);

        }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
