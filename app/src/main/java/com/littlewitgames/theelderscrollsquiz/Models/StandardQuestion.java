package com.littlewitgames.theelderscrollsquiz.Models;

/**
 * Created by Rasmus on 22-12-2014.
 */
public class StandardQuestion {
    private long id;
    private String question;
    private String correct_answer;
    private String wrong_answer_one;
    private String wrong_answer_two;
    private String wrong_answer_three;
    private String category;

    public StandardQuestion() {

    }

    public StandardQuestion(long id, String question, String correct_answer, String wrong_answer_one, String wrong_answer_two, String wrong_answer_three, String category) {
        this.id = id;
        this.question = question;
        this.correct_answer = correct_answer;
        this.wrong_answer_one = wrong_answer_one;
        this.wrong_answer_two = wrong_answer_two;
        this.wrong_answer_three = wrong_answer_three;
        this.category = category;
    }

    public StandardQuestion(String question, String correct_answer, String wrong_answer_one, String wrong_answer_two, String wrong_answer_three, String category) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.wrong_answer_one = wrong_answer_one;
        this.wrong_answer_two = wrong_answer_two;
        this.wrong_answer_three = wrong_answer_three;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getWrong_answer_one() {
        return wrong_answer_one;
    }

    public void setWrong_answer_one(String wrong_answer_one) {
        this.wrong_answer_one = wrong_answer_one;
    }

    public String getWrong_answer_two() {
        return wrong_answer_two;
    }

    public void setWrong_answer_two(String wrong_answer_two) {
        this.wrong_answer_two = wrong_answer_two;
    }

    public String getWrong_answer_three() {
        return wrong_answer_three;
    }

    public void setWrong_answer_three(String wrong_answer_three) {
        this.wrong_answer_three = wrong_answer_three;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
