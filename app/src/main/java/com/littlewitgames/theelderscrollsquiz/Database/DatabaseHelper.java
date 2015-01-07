package com.littlewitgames.theelderscrollsquiz.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;

import com.littlewitgames.theelderscrollsquiz.Models.StandardQuestion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;


/**
 * Created by Rasmus on 22-12-2014.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "questions.db";
    private static final int SCHEMA           = 1;
    static final String ID                    = "_id";
    static final String QUESTION              = "question";
    static final String CORRECT_ANSWER        = "correct_answer";
    static final String WRONG_ANSWER_ONE      = "wrong_answer_one";
    static final String WRONG_ANSWER_TWO      = "wrong_answer_two";
    static final String WRONG_ANSWER_THREE    = "wrong_answer_three";
    static final String QUIZ_ID               = "quiz_id";
    static final String TABLE                 = "questions";

    static final String TABLE_QUIZES          = "quizes";
    static final String QUIZ_NAME             = "name";
    static final String TOTAL_SCORE           = "total_score";
    static final String CORRECTLY_ANSWERED_NUM= "correctly_answered_questions";
    static final String TOTAL_QUESTIONS       = "total_questions";

    private final Context context;

    private static final String DATABASE_CREATE_ONE = "CREATE TABLE " + TABLE_QUIZES + "("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + QUIZ_NAME + " TEXT NOT NULL, " + TOTAL_SCORE + " INTEGER DEFAULT 0, " + CORRECTLY_ANSWERED_NUM + " INTEGER DEFAULT 0, " + TOTAL_QUESTIONS + " INTEGER DEFAULT 0);";
    private static final String DATABASE_CREATE_TWO = "CREATE TABLE " + TABLE + "("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " TEXT NOT NULL, " + CORRECT_ANSWER + " TEXT NOT NULL, " + WRONG_ANSWER_ONE + " TEXT NOT NULL, " + WRONG_ANSWER_TWO + " TEXT NOT NULL, " + WRONG_ANSWER_THREE + " TEXT NOT NULL, " + QUIZ_ID + " INTEGER, FOREIGN KEY(" + QUIZ_ID + ") REFERENCES " + TABLE_QUIZES + "(" + ID + "));";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(DATABASE_CREATE_ONE);
            db.execSQL(DATABASE_CREATE_TWO);

            // General questions
            ContentValues contentValuesRegions        = new ContentValues();
            ContentValues contentValuesHistory        = new ContentValues();
            ContentValues contentValuesPersonalities  = new ContentValues();
            ContentValues contentValuesFoods          = new ContentValues();
            ContentValues contentValuesMusic          = new ContentValues();
            ContentValues contentValuesCulture        = new ContentValues();

            // Creatures
            ContentValues contentValuesWildlife       = new ContentValues();
            ContentValues contentValuesDragons        = new ContentValues();

            // Religion
            ContentValues contentValuesDaedricPrinces = new ContentValues();
            ContentValues contentValuesNineDivines    = new ContentValues();





            ContentValues contentValuesQuizes = new ContentValues();
            ContentValues contentValuesQuestions = new ContentValues();
            ContentValues cvTwo = new ContentValues();
            ContentValues cvThree = new ContentValues();
            contentValuesQuizes.put(ID, 0);
            contentValuesQuizes.put(QUIZ_NAME, "Elder Scrolls V - Skyrim");
            contentValuesQuizes.put(TOTAL_SCORE, 0);
            contentValuesQuizes.put(CORRECTLY_ANSWERED_NUM, 0);
            contentValuesQuizes.put(TOTAL_QUESTIONS, 0);
            db.insert(TABLE_QUIZES, QUIZ_NAME, contentValuesQuizes);


            contentValuesQuizes.put(ID, 1);
            contentValuesQuizes.put(QUIZ_NAME, "Elder Scrolls V - SkyrimTwo");
            contentValuesQuizes.put(TOTAL_SCORE, 0);
            contentValuesQuizes.put(CORRECTLY_ANSWERED_NUM, 0);
            contentValuesQuizes.put(TOTAL_QUESTIONS, 0);
            db.insert(TABLE_QUIZES, QUIZ_NAME, contentValuesQuizes);

            contentValuesQuestions.put(QUESTION, "What is the name of the mortal plane?");
            contentValuesQuestions.put(CORRECT_ANSWER, "Nirn");
            contentValuesQuestions.put(WRONG_ANSWER_ONE, "Oblivion");
            contentValuesQuestions.put(WRONG_ANSWER_TWO, "Tamriel");
            contentValuesQuestions.put(WRONG_ANSWER_THREE, "Rorikstead");
            contentValuesQuestions.put(QUIZ_ID, 1);
            db.insert(TABLE, QUESTION, contentValuesQuestions);

            contentValuesQuestions.put(QUESTION, "Who is the leader of the Stormcloaks in Elder Scrolls Skyrim IV?");
            contentValuesQuestions.put(CORRECT_ANSWER, "Ulfric Stormcloak");
            contentValuesQuestions.put(WRONG_ANSWER_ONE, "Nazeem");
            contentValuesQuestions.put(WRONG_ANSWER_TWO, "Ragnar the Red");
            contentValuesQuestions.put(WRONG_ANSWER_THREE, "Sheogorath");
            contentValuesQuestions.put(QUIZ_ID, 1);
            db.insert(TABLE, QUESTION, contentValuesQuestions);

            contentValuesQuestions.put(QUESTION, "What is the name of the mortal plane?");
            contentValuesQuestions.put(CORRECT_ANSWER, "Nirn");
            contentValuesQuestions.put(WRONG_ANSWER_ONE, "Oblivion");
            contentValuesQuestions.put(WRONG_ANSWER_TWO, "Tamriel");
            contentValuesQuestions.put(WRONG_ANSWER_THREE, "Rorikstead");
            contentValuesQuestions.put(QUIZ_ID, 0);
            db.insert(TABLE, QUESTION, contentValuesQuestions);

            contentValuesQuestions.put(QUESTION, "Who is the leader of the Stormcloaks in Elder Scrolls Skyrim IV?");
            contentValuesQuestions.put(CORRECT_ANSWER, "Ulfric Stormcloak");
            contentValuesQuestions.put(WRONG_ANSWER_ONE, "Nazeem");
            contentValuesQuestions.put(WRONG_ANSWER_TWO, "Ragnar the Red");
            contentValuesQuestions.put(WRONG_ANSWER_THREE, "Sheogorath");
            contentValuesQuestions.put(QUIZ_ID, 0);
            db.insert(TABLE, QUESTION, contentValuesQuestions);

            addQuestion(db, contentValuesQuestions, "Who killed high king Torygg in Elder Scrolls Skyrim IV?", "Ulfric Stormcloak", "Nazeem", "Ragnar the Red", "General Tullius", 0);

            contentValuesQuestions.put(QUESTION, "Who killed high king Torygg in Elder Scrolls Skyrim IV?");
            contentValuesQuestions.put(CORRECT_ANSWER, "Ulfric Stormcloak");
            contentValuesQuestions.put(WRONG_ANSWER_ONE, "Nazeem");
            contentValuesQuestions.put(WRONG_ANSWER_TWO, "Ragnar the Red");
            contentValuesQuestions.put(WRONG_ANSWER_THREE, "Sheogorath");
            contentValuesQuestions.put(QUIZ_ID, 0);
            db.insert(TABLE, QUESTION, contentValuesQuestions);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void addQuiz (SQLiteDatabase db, ContentValues cv, int id, String name, int correctly_answered, int total_questions) {
        cv.put(ID, id);
        cv.put(QUIZ_NAME, name);
        cv.put(CORRECTLY_ANSWERED_NUM, correctly_answered);
        cv.put(TOTAL_QUESTIONS, total_questions);
        db.insert(TABLE_QUIZES, QUIZ_NAME, cv);
    }

    public void addQuestion (SQLiteDatabase db, ContentValues cv, String question, String correct_answer, String wrong_answer_one, String wrong_answer_two, String wrong_answer_three, int quiz_id) {
        cv.put(QUESTION, question);
        cv.put(CORRECT_ANSWER, correct_answer);
        cv.put(WRONG_ANSWER_TWO, wrong_answer_one);
        cv.put(WRONG_ANSWER_TWO, wrong_answer_two);
        cv.put(WRONG_ANSWER_THREE, wrong_answer_three);
        cv.put(QUIZ_ID, quiz_id);
        db.insert(TABLE, QUESTION, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("How did we get here?");

    }
}
