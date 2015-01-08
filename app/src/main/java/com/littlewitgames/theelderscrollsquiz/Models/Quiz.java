package com.littlewitgames.theelderscrollsquiz.Models;

/**
 * Created by Rasmus on 08-01-2015.
 */
public class Quiz {
    private long id;
    private String name;
    private int correctly_answered;
    private int total_questions;

    public Quiz() {
    }

    public Quiz(long id, String name, int correctly_answered, int total_questions) {
        this.id = id;
        this.name = name;
        this.correctly_answered = correctly_answered;
        this.total_questions = total_questions;
    }

    public Quiz(String name, int correctly_answered, int total_questions) {
        this.name = name;
        this.correctly_answered = correctly_answered;
        this.total_questions = total_questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCorrectly_answered() {
        return correctly_answered;
    }

    public void setCorrectly_answered(int correctly_answered) {
        this.correctly_answered = correctly_answered;
    }

    public int getTotal_questions() {
        return total_questions;
    }

    public void setTotal_questions(int total_questions) {
        this.total_questions = total_questions;
    }
}
