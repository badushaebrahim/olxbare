package com.example.olx_bare;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class editprod extends AppCompatActivity {
        EditText name,price,detail;
        String na,de;
        String pr;
    RequestQueue queue;
    int me,sh;
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
        detail=findViewById(R.id.detailu);
       // @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        //me = sh.getInt("uid", 0);

    }
void getdata(){
        init();


            na =getIntent().getExtras().getString("head");
            name.setText(na);
            sh=getIntent().getExtras().getInt("price");
            pr=Integer.toString(sh);
            System.out.print(pr);
    Log.d("TAG", "getdata: "+pr);
            price.setText(pr);
            de = getIntent().getExtras().getString("Details");
            detail.setText(de);

        }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
