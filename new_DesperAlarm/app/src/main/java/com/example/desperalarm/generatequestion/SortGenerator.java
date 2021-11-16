package com.example.desperalarm.generatequestion;

import java.util.Arrays;
import java.util.Random;

/**
 * generator for sorting questions
 */
public class SortGenerator extends QuestionGenerator {

    /**
     * constant for length of the random string
     */
    private final int STR_LENGTH = 15;
    /**
     * random string used in the question
     */
    private String randomString;

    /**
     * generator a random string
     * @return ramdom string generated
     */
    private String generateRandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < STR_LENGTH) {
            int index = (int) (random.nextFloat() * alphabet.length());
            builder.append(alphabet.charAt(index));
        }
        return builder.toString();
    }

    /**
     * output the question
     * @return question prompt
     */
    public String outputQuestion() {
        randomString = generateRandomString();
        return "Sort the following String in alphabetic order:\n\n" + randomString;
    }

    /**
     * check if the answer is correct
     * @param answer the answer user entered
     * @return true if the answer is correct
     */
    public boolean checkAnswer(String answer) {
        // transform string to char array and sort
        char[] chars = randomString.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted.equals(answer);
    }
}
