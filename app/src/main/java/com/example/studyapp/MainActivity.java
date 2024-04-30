package com.example.studyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageButton entrance_btn = findViewById(R.id.btn_entrance);
        EditText text_login = findViewById(R.id.namelogin);


        entrance_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });

        Intent homepage = new Intent(this, HomePageActivity.class);


        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_login.setText("");
            }
        });
        entrance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = text_login.getText().toString();
                homepage.putExtra("userName",userInput);

                startActivity(homepage);
            }
        });

    }
}