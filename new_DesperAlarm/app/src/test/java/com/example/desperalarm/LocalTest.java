package com.example.desperalarm;

import org.junit.Test;

import static org.junit.Assert.*;

import android.app.Application;
import android.app.Instrumentation;

import androidx.lifecycle.LiveData;

import com.example.desperalarm.activities.QuestionActivity;
import com.example.desperalarm.application.App;
import com.example.desperalarm.data.Alarm;
import com.example.desperalarm.data.AlarmRepository;

import java.util.List;

/**
 * local unit test, which will execute on the development machine (host).
 * InstrumentedTest also include some unit tests
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LocalTest {
    @Test
    /**
     * check the checkAnswer function outputs the correct value
     */
    public void checkAnswer() {
        int[] additionInput = new int[]{22, 35};
        assertTrue(QuestionActivity.checkAnswer(additionInput, 1, 22+35));
        assertFalse(QuestionActivity.checkAnswer(additionInput, 1, 22-35));
        int[] subtractionInput = new int[]{10, 90};
        assertTrue(QuestionActivity.checkAnswer(subtractionInput, 0, 10-90));
        assertFalse(QuestionActivity.checkAnswer(subtractionInput, 0, 10+90));
        int[] multInput = new int[]{8, 9};
        assertTrue(QuestionActivity.checkAnswer(multInput, 2, 56));
        assertFalse(QuestionActivity.checkAnswer(multInput, 2, 77));
    }

    @Test
    /**
     * check generated numbers and operation are random
     */
    public void checkQuestionGeneration() {
        int[] firstVal = QuestionActivity.generateNumber100();
        int[] secondVal = QuestionActivity.generateNumber100();
        int[] thirdVal = QuestionActivity.generateNumber100();
        assertFalse(firstVal == secondVal && secondVal == thirdVal);
        int firstOp = QuestionActivity.generateOperation();
        int secondOp = QuestionActivity.generateOperation();
        int thirdOp = QuestionActivity.generateOperation();
        int fourthOp = QuestionActivity.generateOperation();
        assertFalse(firstOp== 1 && secondOp == 1 && thirdOp == 1 && fourthOp == 1);
    }


}