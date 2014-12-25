package com.littlewitgames.theelderscrollsquiz.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    static final String WRONG_ANSWER_THREE    = "wrong_answer_tree";
    static final String CATEGORY              = "category";
    static final String TABLE                 = "questions";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE + "("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " TEXT, " + CORRECT_ANSWER + " TEXT, " + WRONG_ANSWER_ONE + " TEXT, " + WRONG_ANSWER_TWO + " TEXT, " + WRONG_ANSWER_THREE + " TEXT, " + CATEGORY + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(DATABASE_CREATE);

            ContentValues cv = new ContentValues();

            cv.put(QUESTION, "What is the name of the mortal plane?");
            cv.put(CORRECT_ANSWER, "Nirn");
            cv.put(WRONG_ANSWER_ONE, "Oblivion");
            cv.put(WRONG_ANSWER_TWO, "Tamriel");
            cv.put(WRONG_ANSWER_THREE, "Rorikstead");
            cv.put(CATEGORY, "History");
            db.insert(TABLE, QUESTION, cv);

            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("How did we get here?");

    }
}
