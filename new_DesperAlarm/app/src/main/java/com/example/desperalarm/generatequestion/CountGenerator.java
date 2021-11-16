package com.example.desperalarm.generatequestion;

import java.util.Arrays;
import java.util.Random;

/**
 * generator for counting questions
 */
public class CountGenerator extends QuestionGenerator{

    /**
     * constant for random array length
     */
    private final int ARRAY_LENGTH = 20;
    /**
     * constant for upperbound of elements in random array
     */
    private final int UPPERBOUND = 100;
    /**
     * random array used in question
     */
    private int[] randomArray;
    /**
     * threshold for counting
     */
    private int threshold;


    /**
     * geneator a random array of size ARRAY_LENGTH
     * @return the generated array
     */
    private int[] generateRandomArray() {
        Random random = new Random();
        int[] arr = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            arr[i] = random.nextInt(UPPERBOUND);
        }
        return arr;
    }

    /**
     * generate a threshold for counting
     * @return threshold generated
     */
    private int generaThreshold() {
        Random random = new Random();
        int randomThreshold = random.nextInt(100);
        return randomThreshold;
    }

    /**
     * output the question
     * @return question prompt
     */
    public String outputQuestion() {
        randomArray = generateRandomArray();
        threshold = generaThreshold();
        return "Count the number of integers > " + threshold + " in the array\n\n"
                + Arrays.toString(randomArray);
    }

    /**
     * check if the answer is correct
     * @param answer the answer user entered
     * @return true if the answer is correct
     */
    public boolean checkAnswer(String answer) {
        int count = 0;
        // count number of elements above threshold
        for (int num : randomArray) {
            if (num > threshold) {
                count++;
            }
        }
        return count == Integer.parseInt(answer);
    }
}
