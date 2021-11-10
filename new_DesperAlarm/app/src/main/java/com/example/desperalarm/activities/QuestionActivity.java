package com.example.desperalarm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desperalarm.R;
import com.example.desperalarm.service.AlarmService;
import java.util.Random;


/**
 * class for the question activity.
 * show a random question and check the answer that user entered
 */
public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        TextView question = findViewById(R.id.challenge);
        // generate a random question
        int[] questionVals = generateNumber();
        boolean questionOp = generateOperation();
        question.setText(formatQuestion(questionVals, questionOp));
        Button submit = findViewById(R.id.submit);
        // when user clicks submit, check answer
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText answer = findViewById(R.id.answer);
                String result = answer.getText().toString();
                if (checkAnswer(questionVals, questionOp, Integer.parseInt(result))) {
                    Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                    getApplicationContext().stopService(intentService);
                    finish();
                } else {
                    // if answer is wrong, clear text fild
                    answer.setText("");
                }
            }
        });

    }

    /**
     * Generate two random numbers
     * @return array of generated numbers
     */
    public static int[] generateNumber() {
        Random rand = new Random();
        int upperbound = 100;
        int firstVal = rand.nextInt(upperbound);
        int secondVal = rand.nextInt(upperbound);
        return new int[]{firstVal, secondVal};
    }

    /**
     * Generate a random operation, true represents addition, false represents subtraction
     * @return the generated operation
     */
    public static boolean generateOperation() {
        Random rand = new Random();
        boolean operation = rand.nextBoolean();
        return operation;
    }

    /**
     * Format the question into a string
     * @param vals question vals
     * @param operation question operation
     * @return formatted question
     */
    private String formatQuestion(int[] vals, boolean operation) {
        String operationString = " - ";
        if (operation) {
            operationString = " + ";
        }
        return vals[0] + operationString + vals[1] + " = ?";
    }

    /**
     * Check if the answer is correct
     * @param vals question vals
     * @param operation question ops
     * @param answer the answer to chek
     * @return true if the answer is correct
     */
    public static boolean checkAnswer(int[] vals, boolean operation, int answer) {
        if (operation) {
            return answer == (vals[0] + vals[1]);
        }
        return answer == (vals[0] - vals[1]);
    }
}
