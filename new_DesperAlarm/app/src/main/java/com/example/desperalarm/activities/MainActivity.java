package com.example.desperalarm.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.desperalarm.R;

/**
 * MainActivity class
 * regular alarm reference: https://learntodroid.com/how-to-create-a-simple-alarm-clock-app-in-android/
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
