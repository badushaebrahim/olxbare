package com.example.olx_bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Adminlogin extends AppCompatActivity {
    EditText ud,pw;
    String un,pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adlogin);

    }
    public void Adlo(View v){
        ud=findViewById(R.id.adui);
        pw=findViewById(R.id.adpwd);
        un= ud.getText().toString().trim();
        pd= pw.getText().toString().trim();
        //Toast.makeText(getApplicationContext(), ""+un+pd, Toast.LENGTH_LONG).show();
        if((un.equals("admin"))&&(pd.equals("1234"))){
            Intent k = new Intent(Adminlogin.this,weba.class);
            startActivity(k);
        }
        else {
            Toast.makeText(getApplicationContext(), "Emain or Password incorrec", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
