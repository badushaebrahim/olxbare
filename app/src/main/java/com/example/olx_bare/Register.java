package com.example.olx_bare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void save(View v){
        String name,email,pwd,pwd2,phoneno,address;
        int tno;
        EditText n1= (EditText) findViewById(R.id.Name);
        EditText n2= (EditText) findViewById(R.id.Email);
        EditText n3= (EditText) findViewById(R.id.Password);
        EditText n31= (EditText) findViewById(R.id.password2);
        EditText n4= (EditText) findViewById(R.id.Number);
        EditText n5= (EditText) findViewById(R.id.Address);
        name= n1.getText().toString();
        email=n2.getText().toString();
        pwd= n3.getText().toString();
        pwd2=n31.getText().toString();
        phoneno=n4.getText().toString();
        address=n5.getText().toString();
        if(!pwd.equals(pwd2)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else if(!name.equals("") && !email.equals("") && !pwd.equals("")){
        if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            n2.requestFocus();
            n2.setError("please enter valid email");
        } else if (pwd.length()<8) {
            n3.requestFocus();
            n3.setError("Please add password with atleast 8 characters");
        }
    }else{
            Intent i=new Intent(Register.this,
                    Pickloc.class);
            //Intent is used to switch from one activity to another.
            i.putExtra("name",name);
            i.putExtra("pwd",pwd);
            i.putExtra("email",email);
            i.putExtra("number",phoneno);
            i.putExtra("address",address);
            startActivity(i);
        }


    }
}
