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

    public static class Listing {
        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public int getLid() {
            return Lid;
        }

        public void setLid(int lid) {
            Lid = lid;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLongi() {
            return longi;
        }

        public void setLongi(float longi) {
            this.longi = longi;
        }

        String link,head,detail;
        int sellerid,Lid;
        float lat,longi;

    }
}
