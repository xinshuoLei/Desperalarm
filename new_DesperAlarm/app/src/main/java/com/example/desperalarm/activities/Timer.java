package com.example.desperalarm.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desperalarm.R;

import java.util.Locale;

/**
 * The timer class
 */
public class Timer extends AppCompatActivity {
    /**
     * number constants
     */
    private final int THOUSAND = 1000;
    private final int SIXTY = 60;
    private final int SIXTY_K = 60000;
    private final int THREE_POINT_SIX_K = 3600;
    /**
    * UI components
    */
    private TextView display;
    private EditText input;
    private Button pauseResumeButton;
    private Button setButton;
    private Button resetButton;
    private CountDownTimer timer;

    /**
    * boolean that indicates if the timer if running
    */
    private boolean timerRunning;
    /**
    * time left in timer, unit is ms
    */
    private long timeLeft;
    /**
    * end time of timer, unit is ms
    */
    private long endTime;
    /**
    * start time of timer, unit is ms
    */
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
    
    /**
    * function that close input keyboard
    */
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    
    /**
    * function that initialize timer
    */
    private void initializeTimer() {
        String inputText = input.getText().toString();
        if (inputText.length() == 0) {
            Toast.makeText(Timer.this, "Time can not be empty", Toast.LENGTH_SHORT).show();
        } else {
            long inputInMs = Long.parseLong(inputText) * SIXTY_K;
            // error checking
            if (inputInMs <= 0) {
                Toast.makeText(Timer.this, "Time is not valid", Toast.LENGTH_SHORT).show();
                return;
            }
            startTime = inputInMs;
            timeLeft = startTime;
            closeKeyboard();
            // empty input field
            input.setText("");
        }
    }
    
    /**
    * function that actually start/resume timer
    */
    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeft;

        timer = new CountDownTimer(timeLeft, THOUSAND) {
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
    
    /**
    * function that pause timer
    */
    private void pauseTimer() {
        timer.cancel();
        timerRunning = false;
        updateUI();
    }
    
    /**
    * function that update the countdown
    */
    private void updateDisplay() {
        int hours = (int) (timeLeft / THOUSAND) / THREE_POINT_SIX_K;
        int minutes = (int) ((timeLeft / THOUSAND) % THREE_POINT_SIX_K) / SIXTY;
        int seconds = (int) (timeLeft / THOUSAND) % SIXTY;

        String timeLeftFormatted;
        // format time
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        display.setText(timeLeftFormatted);
    }
    
    /**
    * function that update the UI. set button visibilities.
    */
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
