package com.example.desperalarm.generatequestion;

import java.util.Random;

public class HardCalculationGenerator extends  QuestionGenerator {
    private int[] questionVals;
    private int questionOp;

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
    private int generateOperation() {
        Random rand = new Random();
        int operation = rand.nextInt(3);
        return operation;
    }

    /**
     * Format the question into a string
     * @param vals question vals
     * @param operation question operation
     * @return formatted question
     */
    private String formatQuestion(int[] vals, int operation) {
        String operationString = " - ";
        if (operation == 1) {
            operationString = " + ";
        } else if (operation == 2) {
            operationString = " x ";
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
        if (questionOp == 1) {
            return Integer.parseInt(answer) == (questionVals[0] + questionVals[1]);
        } else if (questionOp == 2) {
            return Integer.parseInt(answer) == (questionVals[0] * questionVals[1]);
        }
        return Integer.parseInt(answer)== (questionVals[0] - questionVals[1]);
    }
}
