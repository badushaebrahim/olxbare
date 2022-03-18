package com.example.olx_bare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class Login  extends AppCompatActivity {
    private   String EMAIL ,PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //no app bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
//no appbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void go(View v){
        Intent i=new Intent(Login.this,
                Register.class);
        //Intent is used to switch from one activity to another.

        startActivity(i);
        //invoke the SecondActivity.

        finish();
    }
    public void  login (View v ){
        EditText email=findViewById(R.id.Emailed);
        EditText password=findViewById(R.id.Passworded);
        EMAIL = email.getText().toString().trim();
        PASSWORD = password.getText().toString().trim();
            da n = new da();
        if(!EMAIL.equals("") && !PASSWORD.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"login.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("resok",response);
                    //response="success";
                    String nes = response.toString();
                    Log.d("resok",nes);
                    if (!response.trim().equals("sus")) {
                        Log.d("TAG", "onResponse: worked");

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                        // Creating an Editor object to edit(write to the file)
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putInt("uid", Integer.parseInt(response.trim()));
                        myEdit.commit();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        //intent.putExtra("me",EMAIL);
                        // intent.putExtra("pwd",PASSWORD);
                        startActivity(intent);
                        finish();
                    } else if(response.trim().equals("failure")) {
                        Log.d("TAG", "onResponse: not in bro ");
                        Toast.makeText(Login.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    
                    Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", EMAIL);
                    data.put("password", PASSWORD);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

public void goweb(View v){
        Intent i = new Intent(Login.this,Adminlogin.class);
        startActivity(i);
}
}
