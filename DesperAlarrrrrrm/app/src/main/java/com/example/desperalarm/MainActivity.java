package com.example.desperalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button regularAlarm = findViewById(R.id.regularAlarm);
        regularAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regularAlarmIntent = new Intent(MainActivity.this, RegularAlarm.class);
                startActivity(regularAlarmIntent);
            }
        });
        Button helpButton = findViewById(R.id.help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(MainActivity.this, Help.class);
                startActivity(helpIntent);
            }
        });
        Button timerButton = findViewById(R.id.timer);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timerIntent = new Intent(MainActivity.this, Timer.class);
                startActivity(timerIntent);
            }
        });
        Button desperButton = findViewById(R.id.desperAlarm);
        desperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desperIntent = new Intent(MainActivity.this, DesperAlarm.class);
                startActivity(desperIntent);
            }
        });
    }

}

