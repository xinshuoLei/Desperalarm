package com.example.desperalarm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desperalarm.R;
import com.example.desperalarm.generatequestion.CalculationGenerator;
import com.example.desperalarm.generatequestion.CountGenerator;
import com.example.desperalarm.generatequestion.HardCalculationGenerator;
import com.example.desperalarm.generatequestion.QuestionGenerator;
import com.example.desperalarm.generatequestion.SortGenerator;
import com.example.desperalarm.service.AlarmService;


/**
 * class for the question activity.
 * show a random question and check the answer that user entered
 */
public class QuestionActivity extends AppCompatActivity {

    /**
     * constant for question types
     */
    private final String EMPTY = "";
    private final String CALCULATION = "Calculation";
    private final String CALCULATION_HARD = "Hard calculation (multiplication included)";
    private final String SORT = "Sorting";
    private final String COUNT = "Count appearances";
    private String[] typesArray = {EMPTY, CALCULATION, CALCULATION_HARD, SORT, COUNT};

    /**
     * QuestionGenerator class used
     */
    private QuestionGenerator generator = null;

    /**
     * selected question type
     */

    private String typeSelection = EMPTY;
    /**
     * components in view
     */
    private TextView question;
    private Button submit;
    private EditText answer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.challenge);
        submit = findViewById(R.id.submit);
        answer = findViewById(R.id.answer);
        answer.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 EditText answer = findViewById(R.id.answer);
                 String result = answer.getText().toString();
                 if (generator.checkAnswer(result)) {
                     // if answer is right, stop alarm
                     Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                     getApplicationContext().stopService(intentService);
                     finish();
                 } else {
                     // if answer is wrong, clear text field and show a message
                     Toast toast = Toast.makeText(QuestionActivity.this, "Wrong Answer!",
                             Toast.LENGTH_SHORT);
                     toast.show();
                     answer.setText("");
                 }
            }
        });

        // set options for question types
        Spinner questionType = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, typesArray);
        questionType.setAdapter(adapter);
        questionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                typeSelection = typesArray[position];
                setQuestion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
    }

    /**
     * set the question prompt based on current selection
     */
    private void setQuestion() {
        switch (typeSelection) {
            case CALCULATION:
                generator = new CalculationGenerator();
                question.setText(generator.outputQuestion());
                break;

            case CALCULATION_HARD:
                generator = new HardCalculationGenerator();
                question.setText(generator.outputQuestion());
                break;

            case SORT:
                generator = new SortGenerator();
                question.setText(generator.outputQuestion());
                break;

            case COUNT:
                generator = new CountGenerator();
                question.setText(generator.outputQuestion());
                break;

            default:
                question.setText("");
        }

        // update component visibilities in ui
        if (!typeSelection.equals(EMPTY)) {
            question.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
        } else {
            question.setVisibility(View.INVISIBLE);
            answer.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.INVISIBLE);
        }

    }
}
