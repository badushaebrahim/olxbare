package com.example.olx_bare;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editprod extends AppCompatActivity {
        EditText name,price,detail;
        String una,ude,upr;
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
public  void getdata(){
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


      public void newdata(View V){
        init();
        una=name.getText().toString().trim();
        ude=detail.getText().toString().trim();
        upr=detail.getText().toString().trim();
        if(una.isEmpty()){
            name.requestFocus();
            name.setError("Empty Name");

        }
        else if(upr.isEmpty()){
            price.requestFocus();
            price.setError("Empty price");
        }
        else if(ude.isEmpty()){
        detail.requestFocus();
        detail.setError("Empty details");
        }
         else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL + "editprod.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String TAG = "regisrer";
                        if (response.trim().equals("success")) {
                            Log.d(TAG, "onResponse: uss");
                            Toast.makeText(getApplicationContext(), "Update OK", Toast.LENGTH_SHORT).show();
                            Intent i2 = new Intent(editprod.this,
                                    MyListings.class);
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
                        data.put("head", una);
                        data.put("price",upr);
                        data.put("detail",ude);
                        int mak=getIntent().getExtras().getInt("lid");
                        data.put("id", String.valueOf(mak));
                        return data;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(stringRequest);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
