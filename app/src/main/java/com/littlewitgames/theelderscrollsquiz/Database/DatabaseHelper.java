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
            addQuestion(db, contentValuesQuestions, "Which of these does Elsweyr not border to?", "Black Marsh", "Valenwood", "Topal Sea", "Cyrodiil", 0);
            addQuestion(db, contentValuesQuestions, "What is the Imperial City surrounded by?", "Water", "Mountains", "Snowy plains", "Crop fields", 0);
            addQuestion(db, contentValuesQuestions, "What lies north of Skyrim?", "The Sea of Ghosts", "The Topal Sea", "Stros M'Kai", "The Imperial City", 0);
            addQuestion(db, contentValuesQuestions, "Which is the largest of the following provinces?", "Cyrodiil", "Valenwood", "Elsweyr", "Skyrim", 0);
            addQuestion(db, contentValuesQuestions, "Which of the cities are located in the Black Marsh?", "Stormhold", "Orcrest", "Kvatch", "Balmora", 0);
            addQuestion(db, contentValuesQuestions, "What is the name of the continent that is located far to the north of Tamriel and with its cold climate has caused climate has caused the Sea of Ghosts to become icy?", "Atmora", "Niden", "Elsweyr", "Bahamas", 0);
            addQuestion(db, contentValuesQuestions, "Which of these is the name of one of Nirn's continents?", "Akavir", "Bora", "Sinderis", "Takun", 0);
            addQuestion(db, contentValuesQuestions, "Which of these are not one of the four warring kingdoms of Akavir?", "Poo'Tang", "Tsaesci", "Ka Po' Tun", "Kamal", 0);
            addQuestion(db, contentValuesQuestions, "The continued existence of this continent is unknown and is claimed by the High Elves to be the birthplace of elves and mankind.", "Aldmeris", "Thras", "Brismeris", "Ildmeris", 0);
            addQuestion(db, contentValuesQuestions, "Which of these continents is characterized by its rocky, barren hills?", "Yokuda", "Thras", "Akavir", "Aldmeris", 0);
            addQuestion(db, contentValuesQuestions, "What does Nirn's name mean in the ancient, Elven language Ehlnofex?", "Arena", "World", "Mortal plane", "Life", 0);

            // Factions
            addQuestion(db, contentValuesQuestions, "This special operations group of bodyguards is dedicated to protecting the emperor.", "Penitus Oculatus", "Fighters Guild", "The Companions", "Order of Diagna", 1);
            addQuestion(db, contentValuesQuestions, "In which of these factions is the emperor known as the Champion of Cyrodiil?", "Order of the Dragon", "Imperial Cult", "Mythic Dawn", "The Blades", 1);
            addQuestion(db, contentValuesQuestions, "In which province is the crime syndicate, Camonna Tong, based?", "Morrowind", "Cyrodiil", "Elsweyr", "High Rock", 1);
            addQuestion(db, contentValuesQuestions, "Who was the leader of the \"Five Hundred Companions?\"", "Ysgramor", "Tiber Septim", "Reman Cyrodiil", "Martin Septim", 1);
            addQuestion(db, contentValuesQuestions, "Which faction was Vanus Galerion the founder of?", "Mages Guild", "Hannibal Traven", "Trebonius Artorius", "Savos Aren", 1);
            addQuestion(db, contentValuesQuestions, "Which of these factions began the Oblivion Crisis?", "Mythic Dawn", "Order of the Virtuous Blood", "Order of Diagna", "Morag Tong", 1);
            addQuestion(db, contentValuesQuestions, "Which faction is the Gray Fox associated with?", "Thieves Guild", "Dark Brotherhood", "Morag Tong", "Camonna Tong", 1);
            addQuestion(db, contentValuesQuestions, "Which of these is not a faction in the Elder Scrolls lore?", "House of Teloril", "Clan Volkihar", "Psijic Order", "Summerset Shadows", 1);
            addQuestion(db, contentValuesQuestions, "In which city are the Knight of the White Stallion based?", "Leyawiin", "Cheydinhal", "Skingrad", "Bruma", 1);
            addQuestion(db, contentValuesQuestions, "In which of the Elder Scrolls games can the Order of the Virtuous Blood be found?", "Oblivion", "Skyrim", "Arena", "Daggerfall", 1);
            addQuestion(db, contentValuesQuestions, "Where are the Fighters Guild's headquarters located?", "Chorrol", "Cheydinhal", "Bruma", "Anvil", 1);
            addQuestion(db, contentValuesQuestions, "What is the name of the prominent Argonian Fighters Guild member met in the Fighter Guild questline of Elder Scrolls online?", "Sees-All-Colors", "Smells-All-Spices", "Hears-Every-Step", "Feels-No-Pain", 1);
            addQuestion(db, contentValuesQuestions, "Which one of these factions serve Sithis?", "Dark Brotherhood", "Imperial Cult", "Tribunal Temple", "Buoyant Armigers", 1);
            addQuestion(db, contentValuesQuestions, "This faction is lead by Paarthurnax", "Greybeards", "Order of Diagna", "Morag Tong", "Imperial Cult", 1);
            addQuestion(db, contentValuesQuestions, "This faction was lead by Mankar Camoran.", "Mythic Dawn", "Mages Guild", "College of Winterhold", "Penitus Oculatus", 1);
            addQuestion(db, contentValuesQuestions, "The Dark Brotherhood formed from this formerly religious order.", "Morag Tong", "Mythic Dawn", "Blades", "Ordinators", 1);
            addQuestion(db, contentValuesQuestions, "Who founded the religious order named the Knights of the Nine?", "Sir Amiel Lannus", "Hero of Kvatch", "Pelinal Whitestrake", "Uriel Septim VII", 1);
            addQuestion(db, contentValuesQuestions, "Which of these traits characterizes the assassin cult the Crimson Scars?", "Vampirism", "Molag Bal worship", "Greed", "Red, torn armor", 1);

            // Races
            addQuestion(db, contentValuesQuestions, "Which of these races is not counted amongst the beastfolk?", "Orc", "Khajit", "Argonian", "Dragon", 2);
            addQuestion(db, contentValuesQuestions, "How many different breeds of Khajit have been verified to exist?", "17", "15", "22", "16", 2);
            addQuestion(db, contentValuesQuestions, "Which race has Morrowind as its homeland?", "Dark elves", "Argonians", "Bretons", "Wood elves", 2);
            addQuestion(db, contentValuesQuestions, "From which continent did the Nords originally hail from?", "Atmora", "Yokuda", "Akavir", "Thras", 2);
            addQuestion(db, contentValuesQuestions, "Which group of mer is also referred to as \"Highborn\"?", "Altmer", "Dunmer", "Ayleid", "Dwemer", 2);
            addQuestion(db, contentValuesQuestions, "Which of these races maintains a deep connection to the \"Hist\"?", "Argonians", "Wood Elves", "Khajit", "Redguard", 2);
            addQuestion(db, contentValuesQuestions, "This race is known to possess both elven and human blood, and is therefore also known as 'Manmeri'?", "Bretons", "Orcs", "Imperials", "Ayleid", 2);
            addQuestion(db, contentValuesQuestions, "This race was named 'Dwarves' by the Giant-folk.", "Dwemer", "Nords", "Orsimer", "Goblins", 2);
            addQuestion(db, contentValuesQuestions, "They are known for their skills as merchants and diplomats.", "Imperials", "Bretons", "High Elves", "Nords", 2);
            addQuestion(db, contentValuesQuestions, "Which of these races is known to often develop an addiction to Moon Sugar?", "Khajit", "Falmer", "Nords", "Bosmer", 2);
            addQuestion(db, contentValuesQuestions, "Which of these races is not known as being highly intelligent?", "Nords", "Bretons", "Altmer", "Dwemer", 2);
            addQuestion(db, contentValuesQuestions, "This race is known to often commit cannibalism", "Bosmer", "Falmer", "Goblins", "Orcs", 2);
            addQuestion(db, contentValuesQuestions, "This race is not presumed to be extinct.", "Snow Elves", "Dwemer", "Ayleid", "Atmoran", 2);
            addQuestion(db, contentValuesQuestions, "They are known for being naturally talented with blade and sword.", "Redguards", "Imperials", "Nords", "Goblins", 2);
            addQuestion(db, contentValuesQuestions, "Often consider themselves superior to other races.", "Altmer", "Dunmer", "Imperials", "Nords", 2);

            // History
            addQuestion(db, contentValuesQuestions, "Where is the White-Gold Tower situated?", "The Imperial City", "The College of Winterhold", "Mournhold", "Solstheim", 3);
            addQuestion(db, contentValuesQuestions, "Which era is also known as the 'Common Era'?", "Second", "First", "Third", "Fourth", 3);
            addQuestion(db, contentValuesQuestions, "Who was the first ruler of Skyrim?", "Harald", "Hjalmer", "Vrage", "Ysgramor", 3);
            addQuestion(db, contentValuesQuestions, "Which Reman Cyrodiil was assassinated by the Morag Tong, marking the end of the First Era?", "III", "I", "II", "IV", 3);
            addQuestion(db, contentValuesQuestions, "In which era did the Thrassian Plague begin?", "First", "Second", "Third", "Fourth", 3);
            addQuestion(db, contentValuesQuestions, "The Battle of Falconstar took place between Queen Potema and...?", "Magnus Septim", "Uriel Septim III", "Cephorus Septim", "Martin Septim", 3);
            addQuestion(db, contentValuesQuestions, "In which era did The Battle of Falconstar take place?", "Fourth", "Third", "Second", "First", 3);
            addQuestion(db, contentValuesQuestions, "Who finally defeated Queen Potema in the Siege of Solitude in the year 3E 137?", "Cephorus Septim", "Magnus Septim", "Uriel Septim III", "Pelagius Septim", 3);
            addQuestion(db, contentValuesQuestions, "Who was the last emperor of the Septim Dynasty?", "Martin Septim", "Uriel VII", "Pelagius IV", "Uriel VIII", 3);
            addQuestion(db, contentValuesQuestions, "Which of the following succeeded the Septim Dynasty as rulers of the Empire?", "Mede Dynasty", "Akaviri Potentates", "Camoran Dynasty", "Mantiarco Dynasty", 3);
            addQuestion(db, contentValuesQuestions, "Who was the second monarch to rule the Empire of Tamriel, the Third Empire?", "Pelagius Septim I", "Tiber Septim", "Uriel Septim I", "Kintyra Septim", 3);
            addQuestion(db, contentValuesQuestions, "Which era includes the creation of Nirn and Tamriel's races?", "Merethic Era", "Dawn Era", "Aurbis Era", "Interregnum", 3);
            addQuestion(db, contentValuesQuestions, "Which empire is known as the First Empire?", "Alessian Empire", "Reman", "Nordic", "Camoran", 3);
            addQuestion(db, contentValuesQuestions, "In the year 1E 242, the Cyrodiilic human uprising began. Who did the uprising rebel against?", "Ayleids", "High Elves", "Nordic King", "Bosmer Camoran Dynasty", 3);
            addQuestion(db, contentValuesQuestions, "In which century of the First Era did the Skyrim Conquests come to and end?", "5th", "4th", "6th", "7th", 3);



            // Culture
            addQuestion(db, contentValuesQuestions, "What is the name of the nominal head of Khajiti religion?", "The Mane", "The Seer", "The Claw", "The Heart", 4);
            addQuestion(db, contentValuesQuestions, "What do the prefixes J, Ja or Ji usually mean in Khajit names?", "Bachlor, young adult", "Thief, clever", "Wizard, scholar", "Warrior", 4);
            addQuestion(db, contentValuesQuestions, "Who or what determines a Khajit's breed?", "Lunar phases", "Mother's breed", "Season", "Kenarthi", 4);
            addQuestion(db, contentValuesQuestions, "What does Nord culture value the most?", "Honor", "Strength", "Compassion", "Sharp wit", 4);
            addQuestion(db, contentValuesQuestions, "Which of the following are essential to Nord culture?", "Mead", "Chest hair", "Beautiful weapons", "Gold", 4);
            addQuestion(db, contentValuesQuestions, "Who did the Thalmor ban the Nords from worshipping as part of the White-Gold Concordat?", "Talos", "Lorkhan", "Dragons", "Kyne", 4);
            addQuestion(db, contentValuesQuestions, "Who is known as 'The Trickster' in Mer mythology?", "Lorkhan", "Talos", "Sheogorath", "Hermaeus Mora", 4);
            addQuestion(db, contentValuesQuestions, "Which of the following are characteristic of Imperial culture?", "Mercantilism", "Farming", "Tailoring", "Humble nature", 4);
            addQuestion(db, contentValuesQuestions, "Imperial names are often...", "Latin", "British", "Scandinavian", "Asian", 4);
            addQuestion(db, contentValuesQuestions, "Argonians take home in the Black Marsh. Here they are said to be co-dependent on the...", "Trees", "Lakes", "Animals", "Dunmer", 4);
            addQuestion(db, contentValuesQuestions, "What are the Argonians native to the Black Marsh said to be experts in?", "Guerilla Warfare", "Smithing", "Diplomacy", "Hunting", 4);
            addQuestion(db, contentValuesQuestions, "What kind of jewelry is used in a traditional Argonian marriage proposal?", "Ring", "Necklace", "Earring", "Bracelet", 4);
            addQuestion(db, contentValuesQuestions, "Who is the chief deity of Orcish religion?", "Malacath", "Mephala", "Boethiah", "Molag Bal", 4);
            addQuestion(db, contentValuesQuestions, "What is the term used for an Orc chieftains second wife?", "Forge-wife", "Tribute-wife", "Stronghold", "Honor-wife", 4);
            addQuestion(db, contentValuesQuestions, "Orcs will change their last name in accordance with which event in their life?", "Leaving stronghold", "First war kill", "Killing father", "Choice of profession", 4);

            // Music
            addQuestion(db, contentValuesQuestions, "Which of the following races are in particular known to enjoy music?", "Nords", "Bretons", "Imperials", "Altmer", 5);
            addQuestion(db, contentValuesQuestions, "Which of the following is not an instrument commonly used in Nord music?", "Harp", "Flute", "Lute", "Drum", 5);
            addQuestion(db, contentValuesQuestions, "In which expansion to the Elder Scrolls series is it possible to find the alchemy skill increasing song, 'The Song of Uncle Sweetshare'?", "Bloodmoon", "Tribunal", "Knight of Nine", "Shivering Isles", 5);
            addQuestion(db, contentValuesQuestions, "The song 'King Olaf's Verse' claim him to be...", "a liar", "a hero", "a cunning politician", "a noble king", 5);
            addQuestion(db, contentValuesQuestions, "What was the name of the dragon mentioned in 'King Olaf's Verse'?", "Numinex", "Kahvozein", "Paarthunax", "Nafaalilargus", 5);
            addQuestion(db, contentValuesQuestions, "The bards of Skyrim once celebrated a festival, which was banned by Elisif the Fair. One of the main themes of the festival was an effigy. What was the effigy used for?", "Burning", "Hanging", "Beheading", "Throwing over a cliff", 5);
            addQuestion(db, contentValuesQuestions, "Which of the following is part of the first line of the song 'The Dragonborn Comes'?", "Our Hero", "I'll tell you", "There once was", "This is a tale", 5);
            addQuestion(db, contentValuesQuestions, "Fill in the missing word. 'There once was a hero named Ragnar the Red, who came to ... from ole Rorikstead!", "Whiterun", "Markarth", "Windhelm", "Falkreath", 5);
            addQuestion(db, contentValuesQuestions, "Which famous human is 'The Song of Return' about?", "Ysgramor", "Talos", "Ulfric Stormcloak", "Nazeem", 5);
            addQuestion(db, contentValuesQuestions, "'Tale of the Tongues' is about how the Tongues defeated Alduin for the first time. Which of the following members was not part of this event?", "Derek the Tall", "Hakon One-Eye", "Felldir the Old", "Gormlaith Golden-Hilt", 5);

            // Personalities
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 6);

            // Food
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);

            // Artifacts
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 8);

            // Wildlife
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);

            // Dragon
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);

            // Daedric Princes
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 11);

            // Nine Divines
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);

            // Skyrim items
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);

            // Skyrim story
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 14);

            // Skyrim characters
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 15);

            // Skyrim spells
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);

            // Skyrim character build
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);

            // Skyrim quotes
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);

            // Skyrim quests
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 19);

            // Oblivion items
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);

            // Oblivion story
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 21);

            // Oblivion Characters
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 22);

            // Oblivion Spells
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);

            // Oblivion Character build
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 24);

            // Oblivion quotes
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 25);

            // Oblivion quests
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);

            // Morrowind items
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 27);

            // Morrowind story
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 28);

            // Morrowind Characters
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 29);

            // Morrowind Spells
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 30);

            // Morrowind Character build
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 31);

            // Morrowind quotes
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 32);

            // Morrowind quests
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 33);

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
