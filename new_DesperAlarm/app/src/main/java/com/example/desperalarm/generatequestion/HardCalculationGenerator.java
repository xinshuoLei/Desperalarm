package com.example.desperalarm.generatequestion;

import java.util.Random;

public class HardCalculationGenerator extends  QuestionGenerator {

    private final int NUM_OPS = 3;
    private final int SUBTRACTION = 0;
    private final int ADDITION = 1;
    private final int MULTIPLICATION = 2;
    private final int UPPERBOUND = 100;
    private final int MULTIPLICATION_BOUND = 10;

    private int questionOp;

    private int[] questionVals;


    /**
     * Generate two random numbers for addition/subtraction
     * @return array of generated numbers
     */
    private int[] generateAddSubNumber() {
        Random rand = new Random();
        int firstVal = rand.nextInt(UPPERBOUND);
        int secondVal = rand.nextInt(UPPERBOUND);
        int thirdVal = rand.nextInt(UPPERBOUND);
        return new int[]{firstVal, secondVal, thirdVal};
    }

    /**
     * Generate random numbers for multiplication
     * 1 number for multiplication is < 10
     */
    private int[] generateMultiplicationNumber() {
        Random rand = new Random();
        int firstVal = rand.nextInt(UPPERBOUND);
        int secondVal = rand.nextInt(MULTIPLICATION_BOUND);
        return new int[]{firstVal, secondVal};
    }

    /**
     * Generate a random operation, true represents addition, false represents subtraction
     * @return the generated operation
     */
    private int generateOperation() {
        Random rand = new Random();
        return rand.nextInt(NUM_OPS);
    }

    /**
     * Format the question into a string
     * @param vals question vals
     * @param operation question operation
     * @return formatted question
     */
    private String formatQuestion(int[] vals, int operation) {
        String operationString = " - ";
        if (operation == MULTIPLICATION) {
            operationString = " x ";
            return vals[0] + operationString + vals[1] + " = ?";
        }
        if (operation == ADDITION) {
            operationString = " + ";
        }
        return vals[0] + operationString + vals[1] + operationString + vals[2] + " = ?";
    }

    /**
     * output the generated question
     * @return generated question
     */
    public String outputQuestion() {
        questionOp = generateOperation();
        // if operation is multiplication, set the second value to a random value < 10
        questionVals = generateAddSubNumber();
        if (questionOp == MULTIPLICATION) {
            questionVals = generateMultiplicationNumber();
        }
        return formatQuestion(questionVals, questionOp);
    }

    /**
     * Check if the answer is correct
     * @return true if the answer is correct
     */
    public boolean checkAnswer(String answer) {
        if (questionOp == ADDITION) {
            return Integer.parseInt(answer) == (questionVals[0] + questionVals[1] + questionVals[2]);
        } else if (questionOp == SUBTRACTION) {
            return Integer.parseInt(answer) == (questionVals[0] - questionVals[1] - questionVals[2]);
        }
        return Integer.parseInt(answer)== (questionVals[0] * questionVals[1]);
    }
}
