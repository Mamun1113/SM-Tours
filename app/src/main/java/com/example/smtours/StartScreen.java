package com.example.smtours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import java.util.Timer;
import java.util.TimerTask;

public class StartScreen extends AppCompatActivity {
    Timer timerObj;
    private Object Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);

        timerObj = new Timer();
        timerObj.schedule(new TimerTask()
        {
            @Override
            public void run() {
                Intent intent = new Intent(StartScreen.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}