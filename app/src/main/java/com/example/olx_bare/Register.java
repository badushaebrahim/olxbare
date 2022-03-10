package com.example.olx_bare;

import android.content.Intent;
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

public class Register  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        //no app bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
//no appbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void save(View v){
        //Toast.makeText(getApplicationContext(), "save called", Toast.LENGTH_SHORT).show();
        String name,email,pwd,pwd2,phoneno,address;
        int tno;
        EditText n1= (EditText) findViewById(R.id.Nameed);
        EditText n2= (EditText) findViewById(R.id.Emailed);
        EditText n3= (EditText) findViewById(R.id.Passworded);
        EditText n31= (EditText) findViewById(R.id.password2);
        EditText n4= (EditText) findViewById(R.id.Numbered);
        EditText n5= (EditText) findViewById(R.id.Addressed);
        name= n1.getText().toString();
        email=n2.getText().toString();
        pwd= n3.getText().toString();
        pwd2=n31.getText().toString();
        phoneno=n4.getText().toString();
        address=n5.getText().toString();

        //Intent is used to switch from one activity to another.

        if(!pwd.equals(pwd2)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "line 57", Toast.LENGTH_SHORT).show();
        }
        else if((!name.isEmpty()) && (!email.isEmpty()) && (!pwd.isEmpty())){
            Toast.makeText(getApplicationContext(), "line 60", Toast.LENGTH_SHORT).show();
        if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            Toast.makeText(getApplicationContext(), "62", Toast.LENGTH_SHORT).show();
            n2.requestFocus();
            n2.setError("please enter valid email");
        } else if (pwd.length()<8) {
            Toast.makeText(getApplicationContext(), "66", Toast.LENGTH_SHORT).show();
            n3.requestFocus();
            n3.setError("Please add password with atleast 8 characters");
        }
    }else{
            Toast.makeText(getApplicationContext(), "71", Toast.LENGTH_SHORT).show();
            da n = new da();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"regapi.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String TAG="regisrer";
                    if (response.trim().equals("success")) {
                        Log.d(TAG, "onResponse: uss");
                        Intent i=new Intent(Register.this,
                                Login.class);
                       /* i.putExtra("name",name);
                        i.putExtra("pwd",pwd);
                        i.putExtra("email",email);
                        i.putExtra("number",phoneno);
                        i.putExtra("address",address);
                        */startActivity(i);


                    } else if (response.trim().equals("failure")) {
                        Log.d(TAG, "onResponse: fil");


                    }Log.d(TAG, "onResponse: "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("email", email);
                    data.put("password", pwd);
                    data.put("address",address);
                    data.put("number", phoneno);
                    data.put("lati","10.1004");
                    data.put("longi","76.3570");
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
public  void bypas(View v){
    String name,email,pwd,pwd2,phoneno,address;
    int tno;
    EditText n1= (EditText) findViewById(R.id.Nameed);
    EditText n2= (EditText) findViewById(R.id.Emailed);
    EditText n3= (EditText) findViewById(R.id.Passworded);
    EditText n31= (EditText) findViewById(R.id.password2);
    EditText n4= (EditText) findViewById(R.id.Numbered);
    EditText n5= (EditText) findViewById(R.id.Addressed);
    name= n1.getText().toString();
    email=n2.getText().toString();
    pwd= n3.getText().toString();
    pwd2=n31.getText().toString();
    phoneno=n4.getText().toString();
    address=n5.getText().toString();
    Toast.makeText(getApplicationContext(), "71", Toast.LENGTH_SHORT).show();
    da n = new da();

    if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
        Toast.makeText(getApplicationContext(), "62", Toast.LENGTH_SHORT).show();
        n2.requestFocus();
        n2.setError("please enter valid email");

    } else if(!pwd.equals(pwd2)){
        Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "line 57", Toast.LENGTH_SHORT).show();
    }
    else if (pwd.length()<8) {
        Toast.makeText(getApplicationContext(), "66", Toast.LENGTH_SHORT).show();
        n3.requestFocus();
        n3.setError("Please add password with atleast 8 characters");
    }
    else if (pwd.isEmpty()){
        n3.requestFocus();
        n3.setError("Empty Password");
        Toast.makeText(getApplicationContext(), "No Password", Toast.LENGTH_SHORT).show();
    }
    else if (email.isEmpty()){
        n2.requestFocus();
        n2.setError("Empty Email");
        Toast.makeText(getApplicationContext(), "No Email is empty", Toast.LENGTH_SHORT).show();
    }
    else if (phoneno.isEmpty()){
        n4.requestFocus();
        n4.setError("Empty Phone Number");
        Toast.makeText(getApplicationContext(), "No Phone Number", Toast.LENGTH_SHORT).show();
    }else if (address.isEmpty()){
        n5.requestFocus();
        n5.setError("Empty Address");
        Toast.makeText(getApplicationContext(), "No address", Toast.LENGTH_SHORT).show();
    }
    else if (name.isEmpty()){
        n1.requestFocus();
        n1.setError("Empty Name");
        Toast.makeText(getApplicationContext(), "No Name", Toast.LENGTH_SHORT).show();
    }
    else {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL + "regapi.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String TAG = "regisrer";
                if (response.trim().equals("success")) {
                    Log.d(TAG, "onResponse: uss");
                    Intent i = new Intent(Register.this,
                            Login.class);
                       /* i.putExtra("name",name);
                        i.putExtra("pwd",pwd);
                        i.putExtra("email",email);
                        i.putExtra("number",phoneno);
                        i.putExtra("address",address);
                        */
                    startActivity(i);


                } else if (response.trim().equals("failure")) {
                    Log.d(TAG, "onResponse: fil");


                }
                Log.d(TAG, "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("name", name);
                data.put("email", email);
                data.put("password", pwd);
                data.put("address", address);
                data.put("number", phoneno);
                data.put("lati", "10.1004");
                data.put("longi", "76.3570");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
public void ultrabypas(View v){
    String name,email,pwd,pwd2,phoneno,address;
    int tno;
    EditText n1= (EditText) findViewById(R.id.Nameed);
    EditText n2= (EditText) findViewById(R.id.Emailed);
    EditText n3= (EditText) findViewById(R.id.Passworded);
    EditText n31= (EditText) findViewById(R.id.password2);
    EditText n4= (EditText) findViewById(R.id.Numbered);
    EditText n5= (EditText) findViewById(R.id.Addressed);
    name= n1.getText().toString();
    email=n2.getText().toString();
    pwd= n3.getText().toString();
    pwd2=n31.getText().toString();
    phoneno=n4.getText().toString();
    address=n5.getText().toString();
    Toast.makeText(getApplicationContext(), "71", Toast.LENGTH_SHORT).show();
    da n = new da();

    if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
        Toast.makeText(getApplicationContext(), "62", Toast.LENGTH_SHORT).show();
        n2.requestFocus();
        n2.setError("please enter valid email");

    } else if(!pwd.equals(pwd2)){
        Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "line 57", Toast.LENGTH_SHORT).show();
    }
    else if (pwd.length()<8) {
        Toast.makeText(getApplicationContext(), "66", Toast.LENGTH_SHORT).show();
        n3.requestFocus();
        n3.setError("Please add password with atleast 8 characters");
    }
    else if (pwd.isEmpty()){
        n3.requestFocus();
        n3.setError("Empty Password");
        Toast.makeText(getApplicationContext(), "No Password", Toast.LENGTH_SHORT).show();
    }
    else if (email.isEmpty()){
        n2.requestFocus();
        n2.setError("Empty Email");
        Toast.makeText(getApplicationContext(), "No Email is empty", Toast.LENGTH_SHORT).show();
    }
    else if (phoneno.isEmpty()){
        n4.requestFocus();
        n4.setError("Empty Phone Number");
        Toast.makeText(getApplicationContext(), "No Phone Number", Toast.LENGTH_SHORT).show();
    }else if (address.isEmpty()){
        n5.requestFocus();
        n5.setError("Empty Address");
        Toast.makeText(getApplicationContext(), "No address", Toast.LENGTH_SHORT).show();
    }
    else if (name.isEmpty()){
        n1.requestFocus();
        n1.setError("Empty Name");
        Toast.makeText(getApplicationContext(), "No Name", Toast.LENGTH_SHORT).show();
    }
    else {
        Intent in = new Intent(getApplicationContext(), Pickloc.class);
                in.putExtra("name", name);
        in.putExtra("email", email);
        in.putExtra("password", pwd);
        in.putExtra("address", address);
        in.putExtra("number", phoneno);
       // in.putExtra("lati", "10.1004");
       // in.putExtra("longi", "76.3570");
             //   return data;
    startActivity(in);


    }
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
