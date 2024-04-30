package com.example.studyapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PomodoroActivity extends AppCompatActivity {

    ImageButton homebtn;
    ImageButton todobtn;
    CountDownTimer timer = null;
    Boolean isRun = false;
    Boolean isRunning = false;
    TextView countdownTimer;
    long miles = 1500200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        ImageButton btn_start = findViewById(R.id.btn_start);
        ImageButton btn_delete = findViewById(R.id.btn_delete);
        ImageButton btn_pause = findViewById(R.id.btn_pause);

        btn_start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });

        btn_pause.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });

        btn_delete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });


        countdownTimer = findViewById(R.id.text_clock);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning == false)
                {
                    timerStart();
                    isRunning = true;
                }


            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning == true)
                {
                    pauseTime();
                    isRunning = false;
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning == true)
                {
                    deleteTime();
                    isRunning = false;
                }
            }
        });




        homebtn = findViewById(R.id.btn_home);
        todobtn = findViewById(R.id.btn_todo);

        homebtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });


        todobtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                        break;
                }
                return false;
            }
        });


        Intent home_page = new Intent(this, HomePageActivity.class);
        Intent todo_page = new Intent(this, TodoActivity.class);



        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(home_page);
            }
        });

        todobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(todo_page);
            }
        });


    }

    private void deleteTime() {
        countdownTimer.setText("00\n00");
        timer.cancel();
        if(timer != null)
            timer = null;
        miles = 1500200;
        isRunning = false;
    }

    private void pauseTime() {
        timer.cancel();
        if(timer != null)
            timer = null;
        isRunning = false;
    }

    private void startTime() {
        if(isRun == false)
        {
            timerStart();
            isRun = true;
        }
        else {
            timer.cancel();
            if(timer != null)
                timer = null;
            timerStart();
        }
    }

    private void timerStart() {
        timer = new CountDownTimer(miles,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                String timeFormatted = String.format("%02d\n%02d",minutes,seconds);
                miles = miles - 1000;
                countdownTimer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                countdownTimer.setText("00\n00");
                Toast.makeText(PomodoroActivity.this,"Zaman Doldu", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}