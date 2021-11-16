package com.example.desperalarm.generatequestion;

/**
 * Abstract class for question generators
 */
public abstract class QuestionGenerator {
    /**
     * output a question
     * @return question prompt
     */
    public abstract String outputQuestion();

    /**
     * check if the answer is correct
     * @param answer the answer user entered
     * @return true if the answer is correct. false otherwise
     */
    public abstract boolean checkAnswer(String answer);
}
