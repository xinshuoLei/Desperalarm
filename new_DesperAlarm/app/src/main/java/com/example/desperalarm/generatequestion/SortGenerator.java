package com.example.desperalarm.generatequestion;

import java.util.Arrays;
import java.util.Random;

public class SortGenerator extends QuestionGenerator {
    private String randomString;

    private String generateRandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < 15) { // length of the random string.
            int index = (int) (random.nextFloat() * alphabet.length());
            builder.append(alphabet.charAt(index));
        }
        return builder.toString();
    }

    public String outputQuestion() {
        randomString = generateRandomString();
        return "Sort the following String in alphabetic order:\n\n" + randomString;
    }

    public boolean checkAnswer(String answer) {
        char[] chars = randomString.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted.equals(answer);
    }
}
