package com.example.olx_bare;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class messageslist  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msglist);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
