package com.example.olx_bare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=3000,sp=2500;
    //After completion of 2000 ms, the next activity will get started.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         //       WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //no app bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
//no appbar
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.splash);
        //this will bind your MainActivity.class file with activity_main.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView im = (ImageView) findViewById(R.id.imageView);
                    TextView t = (TextView) findViewById(R.id.textView2);
                    TextView t2 = (TextView) findViewById(R.id.textView4);
                    Animation ani = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                    im.startAnimation(ani);
                    t.startAnimation(ani);
                    t2.startAnimation(ani);
                }
            },sp);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash.this,
                        Login.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
