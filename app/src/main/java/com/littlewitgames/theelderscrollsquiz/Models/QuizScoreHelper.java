package com.littlewitgames.theelderscrollsquiz.Models;

/**
 * Created by Rasmus on 26-12-2014.
 */
public class QuizScoreHelper {
    private int totalQuestionsNum;
    private int correctQuestions;
    private int currentQuestionNum;

    public QuizScoreHelper(int totalQuestionsNum, int correctQuestions) {
        this.totalQuestionsNum = totalQuestionsNum;
        this.correctQuestions = correctQuestions;
        currentQuestionNum = 1;
    }

    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public void setCurrentQuestionNum(int currentQuestionNum) {
        this.currentQuestionNum = currentQuestionNum;
    }

    public int getTotalQuestionsNum() {
        return totalQuestionsNum;
    }

    public void setTotalQuestionsNum(int totalQuestionsNum) {
        this.totalQuestionsNum = totalQuestionsNum;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }
}
