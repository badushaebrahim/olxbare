package com.example.olx_bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Login  extends AppCompatActivity {
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
        Intent x = new Intent(Login.this,MainActivity.class);
        startActivity(x);
        finish();
    }


}
