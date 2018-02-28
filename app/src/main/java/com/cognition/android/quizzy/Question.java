package com.cognition.android.quizzy;

/**
 * Question class
 */

class Question {
    private int mImage;
    private String mQuestion;
    private String[] mChoices;
    private int mAnswer;

    Question(int mImage, String mQuestion, String[] mChoices, int mAnswer) {
        this.mImage = mImage;
        this.mQuestion = mQuestion;
        this.mChoices = mChoices;
        this.mAnswer = mAnswer;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String[] getmChoices() {
        return mChoices;
    }

    public void setmChoices(String[] mChoices) {
        this.mChoices = mChoices;
    }

    public int getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(int mAnswer) {
        this.mAnswer = mAnswer;
    }
}
