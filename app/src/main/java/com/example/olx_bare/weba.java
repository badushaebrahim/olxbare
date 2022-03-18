package com.example.olx_bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class weba extends AppCompatActivity {
    WebView w;
    Button b;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weba);
        this.getSupportActionBar().hide();
        w=findViewById(R.id.bottle);
        w.getSettings().setLoadsImagesAutomatically(true);
        w.setWebViewClient(new WebViewClient());
        w.getSettings().setJavaScriptEnabled(true);
        w.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        da n = new da();
        String url=n.URL+"dashboard.php";
        w.loadUrl(url);
    b=findViewById(R.id.button5);
    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent k = new Intent(weba.this,Adminlogin.class);
            startActivity(k);
        }
    });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
