package com.example.desperalarm;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    private TextView display;
    private EditText input;
    private Button pauseResumeButton;
    private Button setButton;
    private Button resetButton;
    private CountDownTimer timer;
    private boolean timerRunning;
    private long timeLeft;
    private long endTime;
    private long startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        display = findViewById(R.id.timerDisplay);
        input = findViewById(R.id.timerInput);
        pauseResumeButton = findViewById(R.id.pauseResumeButton);
        setButton = findViewById(R.id.setTimerButton);
        resetButton = findViewById(R.id.resetTimerButton);

        // TODO: implement reset button

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeTimer();
                startTimer();
            }
        });

        pauseResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void initializeTimer() {
        String inputText = input.getText().toString();
        if (inputText.length() == 0) {
            Toast.makeText(Timer.this, "Time can not be empty", Toast.LENGTH_SHORT).show();
        } else {
            long inputInMs = Long.parseLong(inputText) * 60000;
            // error checking
            if (inputInMs <= 0) {
                Toast.makeText(Timer.this, "Time is not valid", Toast.LENGTH_SHORT).show();
                return;
            }
            startTime = inputInMs;
            timeLeft = startTime;
            closeKeyboard();
            input.setText("");
        }
    }

    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeft;

        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateDisplay();
            }

            @Override
            public void onFinish() {
                Toast.makeText(Timer.this, "TIME IS UP", Toast.LENGTH_SHORT).show();
                timerRunning = false;
                updateUI();
            }
        }.start();

        timerRunning = true;
        updateUI();
    }

    private void pauseTimer() {
        timer.cancel();
        timerRunning = false;
        updateUI();
    }

    private void updateDisplay() {
        int hours = (int) (timeLeft / 1000) / 3600;
        int minutes = (int) ((timeLeft / 1000) % 3600) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        display.setText(timeLeftFormatted);
    }

    private void updateUI() {
        if (timerRunning) {
            input.setVisibility(View.INVISIBLE);
            setButton.setVisibility(View.INVISIBLE);
            resetButton.setVisibility(View.INVISIBLE);
            pauseResumeButton.setText("Pause");
        } else {
            input.setVisibility(View.VISIBLE);
            setButton.setVisibility(View.VISIBLE);
            pauseResumeButton.setText("Resume");

            if (timeLeft == 0) {
                pauseResumeButton.setVisibility(View.INVISIBLE);
            } else {
                pauseResumeButton.setVisibility(View.VISIBLE);
            }
        }
    }

}
