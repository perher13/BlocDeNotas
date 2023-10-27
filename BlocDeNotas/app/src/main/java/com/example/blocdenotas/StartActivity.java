package com.example.blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask myTimerTask = new TimerTask() {

            @Override
            public void run() {
                Intent pasarPantalla = new Intent(StartActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        };

        Timer timer = new Timer();
        timer.schedule(myTimerTask, 3000);
    }
}