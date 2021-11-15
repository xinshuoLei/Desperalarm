package com.example.desperalarm.generatequestion;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class CountGenerator extends QuestionGenerator{
    private final int ARRAY_LENGTH = 20;
    private int[] randomArray;
    private int threshold;
    private int upperbound = 100;

    private int[] generateRandomArray() {
        Random random = new Random();
        int[] arr = new int[ARRAY_LENGTH];
        for (int i = 0; i < 20; i++) {
            arr[i] = random.nextInt(upperbound);
        }
        return arr;
    }

    private int generaThreshold() {
        Random random = new Random();
        int randomThreshold = random.nextInt(100);
        return randomThreshold;
    }

    public String outputQuestion() {
        randomArray = generateRandomArray();
        threshold = generaThreshold();
        return "Count the number of integers > " + threshold + " in the array\n\n"
                + Arrays.toString(randomArray);
    }

    public boolean checkAnswer(String answer) {
        int count = 0;
        for (int num : randomArray) {
            if (num > threshold) {
                count++;
            }
        }
        return count == Integer.parseInt(answer);
    }
}
