package com.example.desperalarm.generatequestion;

import java.util.Random;

public class CalculationGenerator extends QuestionGenerator {

    private int[] questionVals;
    private boolean questionOp;

    /**
     * Generate two random numbers
     * @return array of generated numbers
     */
    private int[] generateNumber() {
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
    private boolean generateOperation() {
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
     * output the generated question
     * @return generated question
     */
    public String outputQuestion() {
        questionVals = generateNumber();
        questionOp = generateOperation();
        return formatQuestion(questionVals, questionOp);
    }

    /**
     * Check if the answer is correct
     * @return true if the answer is correct
     */
    public boolean checkAnswer(String answer) {
        if (questionOp) {
            return Integer.parseInt(answer) == (questionVals[0] + questionVals[1]);
        }
        return Integer.parseInt(answer)== (questionVals[0] - questionVals[1]);
    }
}
