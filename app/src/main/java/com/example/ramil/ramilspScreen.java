package com.example.ramil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ramilspScreen extends AppCompatActivity {
    Handler hnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramilsp_screen);
        hnd = new Handler();
        hnd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent int860=new
                        Intent(ramilspScreen.this,MainActivity.class);
                startActivity(int860);
                finish();
            }
        },400);
    }
}