package com.example.desperalarm;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.desperalarm.generatequestion.CalculationGenerator;

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
        CalculationGenerator generator = new CalculationGenerator();
        generator.outputQuestion();
        int[] questionVals = generator.getQuestionVals();
        boolean questionOp = generator.getQuestionOp();
        int correctAnswer = questionVals[0] - questionVals[1];
        int wrongAnswer = questionVals[0] + questionVals[1];
        if (questionOp) {
            correctAnswer = questionVals[0] + questionVals[1];
            wrongAnswer = questionVals[0] - questionVals[1];
        }
        assertTrue(generator.checkAnswer(String.valueOf(correctAnswer)));
        assertFalse(generator.checkAnswer(String.valueOf(wrongAnswer)));
    }

    @Test
    /**
     * check generated numbers and operation are random
     */
    public void checkQuestionGeneration() {
        CalculationGenerator generator = new CalculationGenerator();
        String firstQuestion = generator.outputQuestion();
        String secondQuestion = generator.outputQuestion();
        String thirdQuestion = generator.outputQuestion();
        assertFalse(firstQuestion.equals(secondQuestion) && secondQuestion.equals(thirdQuestion));
    }


}