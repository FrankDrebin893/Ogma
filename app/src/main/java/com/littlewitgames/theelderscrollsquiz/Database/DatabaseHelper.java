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

            ContentValues cv = new ContentValues();
            ContentValues cvQ = new ContentValues();
            ContentValues cvTwo = new ContentValues();
            ContentValues cvThree = new ContentValues();
            cv.put(ID, 0);
            cv.put(QUIZ_NAME, "Elder Scrolls V - Skyrim");
            cv.put(TOTAL_SCORE, 0);
            cv.put(CORRECTLY_ANSWERED_NUM, 0);
            cv.put(TOTAL_QUESTIONS, 0);
            db.insert(TABLE_QUIZES, QUIZ_NAME, cv);


            cvQ.put(QUESTION, "What is the name of the mortal plane?");
            cvQ.put(CORRECT_ANSWER, "Nirn");
            cvQ.put(WRONG_ANSWER_ONE, "Oblivion");
            cvQ.put(WRONG_ANSWER_TWO, "Tamriel");
            cvQ.put(WRONG_ANSWER_THREE, "Rorikstead");
            cvQ.put(QUIZ_ID, 0);
            db.insert(TABLE, QUESTION, cvQ);

            cvQ.put(QUESTION, "Who is the leader of the Stormcloaks in Elder Scrolls Skyrim IV?");
            cvQ.put(CORRECT_ANSWER, "Ulfric Stormcloak");
            cvQ.put(WRONG_ANSWER_ONE, "Nazeem");
            cvQ.put(WRONG_ANSWER_TWO, "Ragnar the Red");
            cvQ.put(WRONG_ANSWER_THREE, "Sheogorath");
            cvQ.put(QUIZ_ID, 0);
            db.insert(TABLE, QUESTION, cvQ);

            cvQ.put(QUESTION, "Who killed high king Torygg in Elder Scrolls Skyrim IV?");
            cvQ.put(CORRECT_ANSWER, "Ulfric Stormcloak");
            cvQ.put(WRONG_ANSWER_ONE, "Nazeem");
            cvQ.put(WRONG_ANSWER_TWO, "Ragnar the Red");
            cvQ.put(WRONG_ANSWER_THREE, "Sheogorath");
            cvQ.put(QUIZ_ID, 0);
            db.insert(TABLE, QUESTION, cvQ);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("How did we get here?");

    }
}
