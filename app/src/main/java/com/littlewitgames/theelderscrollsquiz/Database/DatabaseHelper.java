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
    static final String CORRECTLY_ANSWERED_NUM= "correctly_answered_questions";
    static final String TOTAL_QUESTIONS       = "total_questions";

    private final Context context;

    private static final String DATABASE_CREATE_ONE = "CREATE TABLE " + TABLE_QUIZES + "("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + QUIZ_NAME + " TEXT NOT NULL, " + CORRECTLY_ANSWERED_NUM + " INTEGER DEFAULT 0, " + TOTAL_QUESTIONS + " INTEGER DEFAULT 0);";
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

            ContentValues contentValuesQuizes = new ContentValues();
            ContentValues contentValuesQuestions = new ContentValues();

            // General lore quizes
            addQuiz(db, contentValuesQuizes, 0, "Geography");
            addQuiz(db, contentValuesQuizes, 1, "Factions");
            addQuiz(db, contentValuesQuizes, 2, "Races");
            addQuiz(db, contentValuesQuizes, 3, "History");
            addQuiz(db, contentValuesQuizes, 4, "Culture");
            addQuiz(db, contentValuesQuizes, 5, "Music");
            addQuiz(db, contentValuesQuizes, 6, "Characters");
            addQuiz(db, contentValuesQuizes, 7, "Food");
            addQuiz(db, contentValuesQuizes, 8, "Artifacts");

            // Creature quizes
            addQuiz(db, contentValuesQuizes, 9, "Wildlife");
            addQuiz(db, contentValuesQuizes, 10, "Dragons");

            // Religion quizes
            addQuiz(db, contentValuesQuizes, 11, "Daedric Princes");
            addQuiz(db, contentValuesQuizes, 12, "The Nine Divines");

            // Game specific quizes
            // Skyrim
            addQuiz(db, contentValuesQuizes, 13, "Elder Scrolls V - Skyrim - Items");
            addQuiz(db, contentValuesQuizes, 14, "Elder Scrolls V - Skyrim - Story");
            addQuiz(db, contentValuesQuizes, 15, "Elder Scrolls V - Skyrim - Characters");
            addQuiz(db, contentValuesQuizes, 16, "Elder Scrolls V - Skyrim - Spells");
            addQuiz(db, contentValuesQuizes, 17, "Elder Scrolls V - Skyrim - Character build");
            addQuiz(db, contentValuesQuizes, 18, "Elder Scrolls V - Skyrim - Quotes");
            addQuiz(db, contentValuesQuizes, 19, "Elder Scrolls V - Skyrim - Quests");

            // Oblivion
            addQuiz(db, contentValuesQuizes, 20, "Elder Scrolls IV - Oblivion - Items");
            addQuiz(db, contentValuesQuizes, 21, "Elder Scrolls IV - Oblivion - Story");
            addQuiz(db, contentValuesQuizes, 22, "Elder Scrolls IV - Oblivion - Characters");
            addQuiz(db, contentValuesQuizes, 23, "Elder Scrolls IV - Oblivion - Spells");
            addQuiz(db, contentValuesQuizes, 24, "Elder Scrolls IV - Oblivion - Character build");
            addQuiz(db, contentValuesQuizes, 25, "Elder Scrolls IV - Oblivion - Quotes");
            addQuiz(db, contentValuesQuizes, 26, "Elder Scrolls IV - Oblivion - Quests");

            // Morrowind
            addQuiz(db, contentValuesQuizes, 27, "Elder Scrolls III - Morrowind - Items");
            addQuiz(db, contentValuesQuizes, 28, "Elder Scrolls III - Morrowind - Story");
            addQuiz(db, contentValuesQuizes, 29, "Elder Scrolls III - Morrowind - Characters");
            addQuiz(db, contentValuesQuizes, 30, "Elder Scrolls III - Morrowind - Spells");
            addQuiz(db, contentValuesQuizes, 31, "Elder Scrolls III - Morrowind - Character build");
            addQuiz(db, contentValuesQuizes, 32, "Elder Scrolls III - Morrowind - Quotes");
            addQuiz(db, contentValuesQuizes, 33, "Elder Scrolls III - Morrowind - Quests");

            // Geography questions
            addQuestion(db, contentValuesQuestions, "What is the name of Tamriel's northernmost province?", "Skyrim", "High Rock", "Morrowind", "Hammerfell", 0);
            addQuestion(db, contentValuesQuestions, "What is the name of the island group located to the west of the coast of Valenwood", "Summerset Isles", "Stros M'kai", "Solstheim", "Vvardenfell", 0);
            addQuestion(db, contentValuesQuestions, "In which province is the coastal city Camlorn located?", "High Rock", "Cyrodill", "Hammerfell", "Morrowind", 0);
            addQuestion(db, contentValuesQuestions, "In the Nedic language, which province goes by the name of Deathland?", "Hammerfell", "Elsweyr", "Black Marsh", "Valenwood", 0);
            addQuestion(db, contentValuesQuestions, "Where is the White-Gold Tower situated?", "The Imperial City", "The College of Winterhold", "Mournhold", "Solstheim", 0);
            addQuestion(db, contentValuesQuestions, "What is the Imperial City surrounded by?", "Water", "Mountains", "Snowy plains", "Crop fields", 0);
            addQuestion(db, contentValuesQuestions, "What lies north of Skyrim?", "The Sea of Ghosts", "The Topal Sea", "Stros M'Kai", "The Imperial City", 0);
            addQuestion(db, contentValuesQuestions, "Which is the largest of the following provinces?", "Cyrodiil", "Valenwood", "Elsweyr", "Skyrim", 0);
            addQuestion(db, contentValuesQuestions, "Which of the cities are located in the Black Marsh?", "Stormhold", "Orcrest", "Kvatch", "Balmora", 0);
            addQuestion(db, contentValuesQuestions, "What is the name of the continent that is located far to the north of Tamriel and with its cold climate has caused climate has caused the Sea of Ghosts to become icy?", "Atmora", "Niden", "Elsweyr", "Bahamas", 0);
            addQuestion(db, contentValuesQuestions, "Which of these is the name of one of Nirn's continents?", "Akavir", "Bora", "Sinderis", "Takun", 0);
            addQuestion(db, contentValuesQuestions, "Which of these are not one of the four warring kingdoms of Akavir?", "Poo'Tang", "Tsaesci", "Ka Po' Tun", "Kamal", 0);
            addQuestion(db, contentValuesQuestions, "The continued existence of this continent is unknown and is claimed by the High Elves to be birthplace of elves and mankind.", "Aldmeris", "Thras", "Brismeris", "Ildmeris", 0);
            addQuestion(db, contentValuesQuestions, "Which of these continents is characterized by its rocky, barren hills?", "Yokuda", "Thras", "Akavir", "Aldmeris", 0);
            addQuestion(db, contentValuesQuestions, "Named in the ancient Elven language of Ehlnofex, what does Nirn actually mean?", "Arena", "World", "Mortal plane", "Life", 0);

            // Factions
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 1);

            // Races
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 2);

            // History
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 3);



            // Culture
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 4);

            // Music

            // Personalities

            // Food

            /*

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
*/
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void addQuiz (SQLiteDatabase db, ContentValues cv, int id, String name) {
        cv.put(ID, id);
        cv.put(QUIZ_NAME, name);
        db.insert(TABLE_QUIZES, QUIZ_NAME, cv);
    }

    public void addQuestion (SQLiteDatabase db, ContentValues cv, String question, String correct_answer, String wrong_answer_one, String wrong_answer_two, String wrong_answer_three, int quiz_id) {
        cv.put(QUESTION, question);
        cv.put(CORRECT_ANSWER, correct_answer);
        cv.put(WRONG_ANSWER_ONE, wrong_answer_one);
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
