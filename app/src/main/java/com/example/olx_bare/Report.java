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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Report extends AppCompatActivity {
    EditText ms;
    String msg;
    int lid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
    }
  public   void getinfo(View v){
        ms=findViewById(R.id.repo);
        msg= ms.getText().toString().trim();
      if(!msg.isEmpty()){
          da n = new da();
          StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"getcomp.php", new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  Log.d("resok",response);
                  //response="success";
                  String nes = response.toString();
                  Log.d("resok",nes);
                  if (response.trim().equals("success")) {
                      Log.d("TAG", "onResponse: worked");
                      ms.setText("");
                      Intent intent = new Intent(Report.this, MainActivity.class);
                      //intent.putExtra("lname",header);
                      //intent.putExtra("rid",);
                      startActivity(intent);
                      finish();

                  } else if(response.trim().equals("fail")) {
                      Log.d("TAG", "onResponse: not in bro ");
                      Toast.makeText(Report.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                  }
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

                  Toast.makeText(Report.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
              }
          }){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String, String> data = new HashMap<>();
                 // data.put("msg", msgr);
                  @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
                  int me = sh.getInt("uid", 0);
                  String me1=Integer.toString(me);
                  data.put("me", me1);
                  //int reciver= getIntent().getExtras().getInt("sid");
                  //data.put("reciver", String.valueOf(reciver));
                  int prodno = getIntent().getExtras().getInt("lid");
                  data.put("lid", String.valueOf(prodno));
                  String proname=getIntent().getExtras().getString("head");
                  data.put("com",msg);
                  return data;
              }
          };
          RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
          requestQueue.add(stringRequest);
      }
      else{
          ms.requestFocus();
          ms.setError("please add message");
      }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Report.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
}
