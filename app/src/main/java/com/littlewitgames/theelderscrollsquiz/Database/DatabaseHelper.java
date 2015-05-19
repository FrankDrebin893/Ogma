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
            addQuiz(db, contentValuesQuizes, 7, "Food and beverages");
            addQuiz(db, contentValuesQuizes, 8, "Artifacts");
            addQuiz(db, contentValuesQuizes, 34, "Quotes");

            // Creature quizes
            addQuiz(db, contentValuesQuizes, 9, "Creatures and beasts");
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
            addQuiz(db, contentValuesQuizes, 35, "Elder Scrolls IV - Oblivion - Knights of the Nine");
            addQuiz(db, contentValuesQuizes, 36, "Elder Scrolls IV - Oblivion - Shivering Isles");

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
            addQuestion(db, contentValuesQuestions, "What is the name of the continent that is located far to the north of Tamriel and with its cold climate has caused the Sea of Ghosts to become icy?", "Atmora", "Niden", "Elsweyr", "Bahamas", 0);
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
            addQuestion(db, contentValuesQuestions, "Which faction was Vanus Galerion the founder of?", "Mages Guild", "Psijic Order", "Mythic Dawn", "Fighters Guild", 1);
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
            addQuestion(db, contentValuesQuestions, "Which of the following are essential to Nord culture?", "Mead", "Chest hair", "Beautiful weaponry", "Gold", 4);
            addQuestion(db, contentValuesQuestions, "Who did the Thalmor ban the Nords from worshipping as part of the White-Gold Concordat?", "Talos", "Lorkhan", "Dragons", "Kyne", 4);
            addQuestion(db, contentValuesQuestions, "Who is known as 'The Trickster' in Mer mythology?", "Lorkhan", "Talos", "Sheogorath", "Hermaeus Mora", 4);
            addQuestion(db, contentValuesQuestions, "Which of the following are characteristic of Imperial culture?", "Mercantilism", "Farming", "Tailoring", "Humble nature", 4);
            addQuestion(db, contentValuesQuestions, "Imperial names are often...", "Latin", "British", "Scandinavian", "Asian", 4);
            addQuestion(db, contentValuesQuestions, "Argonians take home in the Black Marsh. Here they are said to be co-dependent on the...", "Trees", "Lakes", "Animals", "Dunmer", 4);
            addQuestion(db, contentValuesQuestions, "What are the Argonians native to the Black Marsh said to be experts in?", "Guerilla Warfare", "Smithing", "Diplomacy", "Hunting", 4);
            addQuestion(db, contentValuesQuestions, "What kind of jewelry is used in a traditional Argonian marriage proposal?", "Ring", "Necklace", "Earring", "Bracelet", 4);
            addQuestion(db, contentValuesQuestions, "Who is the chief deity of Orcish religion?", "Malacath", "Mephala", "Boethiah", "Molag Bal", 4);
            addQuestion(db, contentValuesQuestions, "What is the term used for an Orc chieftains second wife?", "Forge-wife", "Tribute-wife", "Stronghold", "Chieftain's Honor", 4);
            addQuestion(db, contentValuesQuestions, "Orcs will change their last name in accordance with which event in their life?", "Leaving stronghold", "First war kill", "Killing father", "Choice of profession", 4);

            // Music
            addQuestion(db, contentValuesQuestions, "Which of the following races are in particular known to enjoy music?", "Nords", "Bretons", "Imperials", "Altmer", 5);
            addQuestion(db, contentValuesQuestions, "Which of the following is not an instrument commonly used in Nord music?", "Harp", "Flute", "Lute", "Drum", 5);
            addQuestion(db, contentValuesQuestions, "In which expansion to the Elder Scrolls series is it possible to find the alchemy skill increasing song, 'The Song of Uncle Sweetshare'?", "Bloodmoon", "Tribunal", "Knight of Nine", "Shivering Isles", 5);
            addQuestion(db, contentValuesQuestions, "The song 'King Olaf's Verse' claim him to be...", "a liar", "a hero", "a cunning politician", "a noble king", 5);
            addQuestion(db, contentValuesQuestions, "What was the name of the dragon mentioned in 'King Olaf's Verse'?", "Numinex", "Kahvozein", "Paarthunax", "Nafaalilargus", 5);
            addQuestion(db, contentValuesQuestions, "The bards of Skyrim once celebrated a festival, which was banned by Elisif the Fair. One of the main themes of the festival was an effigy. What was the effigy used for?", "Burning", "Hanging", "Beheading", "Throwing over a cliff", 5);
            addQuestion(db, contentValuesQuestions, "Which of the following is part of the first line of the song 'The Dragonborn Comes'?", "Our Hero", "I'll tell you", "There once was", "This is a tale", 5);
            addQuestion(db, contentValuesQuestions, "Fill in the missing word. 'There once was a hero named Ragnar the Red, who came riding to ... from ole Rorikstead!", "Whiterun", "Markarth", "Windhelm", "Falkreath", 5);
            addQuestion(db, contentValuesQuestions, "Which famous man is 'The Song of Return' about?", "Ysgramor", "Talos", "Ulfric Stormcloak", "Nazeem", 5);
            addQuestion(db, contentValuesQuestions, "'Tale of the Tongues' is about how the Tongues defeated Alduin for the first time. Which of the following members was not part of this event?", "Derek the Tall", "Hakon One-Eye", "Felldir the Old", "Gormlaith Golden-Hilt", 5);

            // Personalities
            addQuestion(db, contentValuesQuestions, "Nobody likes him.", "Nazeem", "Baalgruf the Greater", "The Gray Fox", "Crazy Batou", 6);
            addQuestion(db, contentValuesQuestions, "This character has made appearances in Skyrim as well as Oblivion and Morrowind. His posses the title of 'the Liar'. What is his name?", "M'aiq", "Uriel Septim VII", "Bulfrek", "Golldir", 6);
            addQuestion(db, contentValuesQuestions, "Was known as 'The Slave Queen'.", "Alessia", "Potema", "Aliera", "Gysilla", 6);
            addQuestion(db, contentValuesQuestions, "What is the name of the living god that assists the player in The Elder Scrolls Online game, but becomes the antagonist in Morrowind: Tribunal?", "Almalexia", "Vivec", "Sotha Sil", "Indorial Nerevar", 6);
            addQuestion(db, contentValuesQuestions, "The end of the First Era was marked by the assassination of Reman Cyrodiil III as well as his son's assassination. What was the son's name?", "Juliek", "Kastav", "Brazollus", "Versidue", 6);
            addQuestion(db, contentValuesQuestions, "This Nord chieftain founded the Greybeards and can be found in Sovngarde. What was his lastname?", "Windcaller", "Stormshield", "Firetongue", "Earthmender", 6);
            addQuestion(db, contentValuesQuestions, "This battlemage was notorious for the kidnapping and banishing of Uriel Septim VII to Oblivion. What was his name?", "Jagar Tharn", "Jagar Karn", "Sethis Karn", "Sethis Tharn", 6);
            addQuestion(db, contentValuesQuestions, "This High King of Skyrim from the First Era was known for outlawing the old Nordic pantheon. The War of Succession followed his death.", "Borgas", "Hanse", "Galmar", "Atlos", 6);
            addQuestion(db, contentValuesQuestions, "This prophet led the Chimer people to what is now known as Morrowind from the northern regions.", "Veloth", "Miraak", "Oz'dayn", "Salos", 6);
            addQuestion(db, contentValuesQuestions, "This emperor most notably signed the 'White-Gold Concordat' treaty.", "Titus Mede II", "Titus Mede I", "Attrebus Mede", "Titus Mede III", 6);
            addQuestion(db, contentValuesQuestions, "This man first appears to the player in Elder Scrolls Online as 'The Prophet'", "Varen Aquilaros", "Vanus Galerion", "Cammeron", "Mannimarco", 6);
            addQuestion(db, contentValuesQuestions, "Who stopped the rock, Baar Dau, sent by Sheogorath to cause destruction and kept it suspended in midair until his disappearance?", "Vivec", "Veloth", "Vanus Galerio", "Abnur Tharn", 6);
            addQuestion(db, contentValuesQuestions, "What was the name of the king of Falkreath who Tiber Septim allied himself with in his early conquests?", "Cuhlecain", "Igmund", "Wulfharth", "Ysmir", 6);
            addQuestion(db, contentValuesQuestions, "Tiber Septim was given many names during his life. What was his first name at birth?", "Hjalti", "Igmund", "Skjaaler", "Rorkan", 6);
            addQuestion(db, contentValuesQuestions, "After a certain victory, Tiber Septim was given the name of 'Talos'. What does it actually mean?", "Stormcrown", "Fireheart", "Ironhand", "Victor", 6);

            // Food and beverages
            addQuestion(db, contentValuesQuestions, "Mead is very essential to which race's culture?", "Nord", "Khajit", "Dunmer", "Altmer", 7);
            addQuestion(db, contentValuesQuestions, "Guards can be mean. Fill in the word: 'Let me guess. Someone stole your...?'", "Sweet Roll", "Dog Meat", "Carrot", "Ale", 7);
            addQuestion(db, contentValuesQuestions, "In the Skyrim quest 'To kill an Empire' you help cooking a gourmet's signature dish. What's its name?", "Potage le Magnifique", "Omelette du Fromage", "Pain perdu", "Soufflé", 7);
            addQuestion(db, contentValuesQuestions, "Which of the following used to be a very tasty eat, and used to require express permission from the Emperor himself to gather?", "Jazbay Grapes", "Chaurus Eggs", "Juniper Berries", "Jarrin Root", 7);
            addQuestion(db, contentValuesQuestions, "Which of the following is not an existing beverage in Elder Scrolls V: Skyrim?", "Khajiti Rum", "Cyrodiilic Brandy", "Argonian Ale", "Balmora Blue", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 7);

            // Artifacts
            addQuestion(db, contentValuesQuestions, "The Amulet of Kings was given by Akatosh to whom?", "Alessia", "Talos", "Uriel Septim I", "Jurgen Windcaller", 8);
            addQuestion(db, contentValuesQuestions, "Three of these are aedric artifacts. Which one is daedric?", "Goldbrand", "Lord's Mail", "Auriel's Bow", "Stendarr's Hammer", 8);
            addQuestion(db, contentValuesQuestions, "How many Black Books are known to exist?", "Seven", "Three", "Five", "Thirteen", 8);
            addQuestion(db, contentValuesQuestions, "Which race forged the warhammer, Volendrung?", "Dwemer", "Orcs", "Dunmer", "Nords", 8);
            addQuestion(db, contentValuesQuestions, "In Skyrim and Oblivion you receive Volendrung from which daedric prince?", "Malacath", "Sheogorath", "Boethiah", "Azura", 8);
            addQuestion(db, contentValuesQuestions, "Which of the Elder Scrolls games from Arena to Skyrim has the Staff of Magnus not appeared in?", "Oblivion", "Skyrim", "Morrowind", "Arena", 8);
            addQuestion(db, contentValuesQuestions, "This crown belonged to King Harald, and was worn by High Kings of Skyrim.", "Jagged Crown", "Storm Crown", "Frozen Crown", "Dragon Crown", 8);
            addQuestion(db, contentValuesQuestions, "Which place does the Mace of Aevar Stone-Singer origin from?", "Solstheim", "Markarth", "Camlorn", "Orsinium", 8);
            addQuestion(db, contentValuesQuestions, "The Masque of Clavicus Vile carries a heavy tragedy behind it. Which effect does it increase upon the wearer?", "Popularity", "Insanity", "Diplomacy", "Amnesia", 8);
            addQuestion(db, contentValuesQuestions, "Which of the following words describe an elder scroll best?", "Prophecy", "Spell", "Map", "Burrito", 8);

            // Creatures
            addQuestion(db, contentValuesQuestions, "Which creature do Giants live in companionship with?", "Mammoth", "Bear", "Sabre Cat", "Bristleback", 9);
            addQuestion(db, contentValuesQuestions, "These creatures take home in Hermauus Mora's realm, Apocrypha.", "Lurker", "Bristleback", "Gargoyle", "Riekling", 9);
            addQuestion(db, contentValuesQuestions, "Which region are the Silt Striders a common sight in?", "Morrowind", "Elsweyr", "Valenwood", "High Rock", 9);
            addQuestion(db, contentValuesQuestions, "The Dreugh are fearsome creatures. A dreugh is part-human and part-?", "Crab", "Tiger", "Bear", "Snake", 9);
            addQuestion(db, contentValuesQuestions, "Spriggans are spirits, which can be found in...", "Forests", "Graveyards", "Lakes", "Caves", 9);
            addQuestion(db, contentValuesQuestions, "A normal troll has a natural weakness towards...", "Fire", "Ice", "Silver", "Poison", 9);
            addQuestion(db, contentValuesQuestions, "Which daedric prince is said to have created the vampires?", "Molag Bal", "Dagon", "Nocturnal", "Sanguine", 9);
            addQuestion(db, contentValuesQuestions, "In Akavir exists a vampiric folk, the Tsaesci, said to carry traits from a certain animal. Which?", "Serpent", "Eagle", "Tiger", "Tortoise", 9);
            addQuestion(db, contentValuesQuestions, "Which daedric prince is credited for the creation of lycanthropy(werewolfism)?", "Hircine", "Dagon", "Molag Bal", "Mephala", 9);
            addQuestion(db, contentValuesQuestions, "The Draugr draw on inspiration from real-life mythology. Which one?", "Norse", "Celtic", "Egyptian", "Arabic", 9);
            addQuestion(db, contentValuesQuestions, "The Elytra are mantis-like creatures. Where can they be found?", "Shivering Isles", "Elsweyr", "Solstheim", "Akavir", 9);
            addQuestion(db, contentValuesQuestions, "Which of the following groups do Atronachs belong to?", "Daedra", "Spriggans", "Vampires", "", 9);
            addQuestion(db, contentValuesQuestions, "The cross of bird and human, the Hagraven, is worshipped as a matriarch by which faction?", "Forsworn", "Blades", "Imperial Cult", "Dark Brotherhood", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 9);

            // Dragon history

            addQuestion(db, contentValuesQuestions, "Which continent are the dragons native to?", "Akavir", "Atmora", "Yokuda", "Tamriel", 10);
            addQuestion(db, contentValuesQuestions, "Which deity was said to have created the dragons?", "Akatosh", "Anu", "Auri-El", "Hermaeus Mora", 10);
            addQuestion(db, contentValuesQuestions, "The dragons were worshipped in particular by a certain people during the Merethic Era. Who were they?", "The Atmorans", "The Redguards", "The Snow Elves", "The Dwemer", 10);
            addQuestion(db, contentValuesQuestions, "Amongst the worshippers existed a group known as the Dragon Cult, consisting of powerful members. Who were they?", "Dragon Priests", "Dragonborn", "Dragon Kings", "Dragon Chief", 10);
            addQuestion(db, contentValuesQuestions, "What was the name of the dragon considered to be the strongest of them all?", "Alduin", "Odahviing", "Numinex", "Skakmat", 10);
            addQuestion(db, contentValuesQuestions, "The iron rule of dragons led to a rebellion known as the Dragon War. Which divine sent the dragon, Paarthunax, to aid the rebellion?", "Kyne", "Akatosh", "Talos", "Mara", 10);
            addQuestion(db, contentValuesQuestions, "The rebellion eventually created a shout to defeat the dragons. What was its name?", "Dragonrend", "Dragonruin", "Dragonwilt", "Dragonfade", 10);
            addQuestion(db, contentValuesQuestions, "During the dragon rule, a dragonborn, possibly the first one, attempted to seize power for himself. What was his name?", "Miraak", "Aarfolas", "Gehkul", "Lokahzid", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 10);

            // Daedric Princes
            addQuestion(db, contentValuesQuestions, "This daedric prince was formerly known as Jyggalag.", "Sheogorath", "Peryite", "Clavicus Vile", "Hircine", 11);
            addQuestion(db, contentValuesQuestions, "Which daedric prince appears as a mass of tentacles and values knowledge?", "Hermaeus Mora", "Clavicus Vile", "Vaermina", "Boethiah", 11);
            addQuestion(db, contentValuesQuestions, "This daedric prince is the patron of everything repulsive.", "Namira", "Peryite", "Vaermina", "Mephala", 11);
            addQuestion(db, contentValuesQuestions, "Which is known as the 'Prince of Madness'?", "Sheogorath", "Clavicus Vile", "Boethiah", "Mephala", 11);
            addQuestion(db, contentValuesQuestions, "This prince is often depicted together with the talking dog, Barbas", "Clavicus Vile", "Hircine", "Sheogorath", "Mephala", 11);
            addQuestion(db, contentValuesQuestions, "The realm, the 'Hunting Grounds', is the realm of which daedric prince?", "Hircine", "Mephala", "Vaermina", "Peryite", 11);
            addQuestion(db, contentValuesQuestions, "The most known artifact of this daedric prince is his razor.", "Mehrunes Dagon", "Molag Bal", "Hircine", "Sanguine", 11);
            addQuestion(db, contentValuesQuestions, "According to the book, 'Varieties of Faith', this daedric prince went by the name of 'The Woodland Man' in Atmoran myths.", "Hermaeus Mora", "Hircine", "Sheogorath", "Peryite", 11);
            addQuestion(db, contentValuesQuestions, "This daedric prince is considered the god-ancestor of dark elves. When the dark elves founded their nation, they particularly worshipped this daedric prince.", "Boethiah", "Mehrunes Dagon", "Azura", "Meridia", 11);
            addQuestion(db, contentValuesQuestions, "The 'Skull of Corruption' is this prince's primary artifact, with the ability to clone whoever it is used upon. Who is it?", "Vaermina", "Azura", "Meridia", "Namira", 11);

            // Nine Divines
            addQuestion(db, contentValuesQuestions, "Originally there were eight divines. Which was added later on?", "Talos", "Kynareth", "Stendarr", "Zenithar", 12);
            addQuestion(db, contentValuesQuestions, "Who was the person to institute the eight divines as a religion?", "Alessia", "Tiber Septim", "Reman Cyrodiil", "King Harald", 12);
            addQuestion(db, contentValuesQuestions, "There are many aedras in existense. Which action committed by the Nine Divines defined how they were chosen to be worshipped?", "Self-sacrifice", "Defiance of order", "Proven loyalty", "Banishing daedra", 12);
            addQuestion(db, contentValuesQuestions, "Which of the nine divines is considered the chief deity?", "Akatosh", "Talos", "Mara", "Arkay", 12);
            addQuestion(db, contentValuesQuestions, "There exist many different pantheons across Tamriel. Which is the most prevalent?", "Imperial", "Nordic", "Altmeri", "Dunmeri", 12);
            addQuestion(db, contentValuesQuestions, "Which of the divines is considered to be the 'mother-deity'?", "Mara", "Dibella", "Kynareth", "Ilara", 12);
            addQuestion(db, contentValuesQuestions, "Who did the eight divines help in creating Nirn?", "Lorkhan", "Anu", "Auri-El", "Sithis", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 12);

/*            // Skyrim items
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 13);*/

            // Skyrim story
            addQuestion(db, contentValuesQuestions, "Easy start: Which city is the Dragonborn taken to at the start of the game?", "Helgen", "Rorikstead", "Windhelm", "Whiterun", 14);
            addQuestion(db, contentValuesQuestions, "Which year does the game begin in?", "4E 201", "3E 398", "4E 457", "4E 244", 14);
            addQuestion(db, contentValuesQuestions, "What is the name of the first dragon to be slain by the Dragonborn?", "Mirmulnir", "Durnehviir", "Kruziikrel", "Odahviing", 14);
            addQuestion(db, contentValuesQuestions, "What is the name of the ruin that the Dragonborn is sent to in order to recover the Dragonstone?", "Bleak Falls Barrow", "Ansilvund", "Korvanjund", "Volskygge", 14);
            addQuestion(db, contentValuesQuestions, "What is the name of the Wood Elf that helps smuggle the Dragonborn into the Thalmor embassy?", "Malborn", "Ardwin", "Anuriel", "Nimriel", 14);
            addQuestion(db, contentValuesQuestions, "Which city does the Dragonborn travel to in order to locate Esbern?", "Riften", "Windhelm", "Solitude", "Whiterun", 14);
            addQuestion(db, contentValuesQuestions, "What is the name of the temple, where the Dragonborn finds Alduin's Wall?", "Sky Haven", "Tribunal Temple", "Cloud Ruler Temple", "Temple of Miraak", 14);
            addQuestion(db, contentValuesQuestions, "What is the name of the huge Dwemer ruin, where the Dragonborn finds an Elder Scroll?", "Blackreach", "Mzulft", "Tower of Mzark", "Shimmermist Grotto", 14);
            addQuestion(db, contentValuesQuestions, "Who is the giant Nord that guards the whalebonebridge to the hall of Valor?", "Tsun", "Tzu", "Yasu", "Taro", 14);
            addQuestion(db, contentValuesQuestions, "Where does the Dragonborn bring Alduin to his final demise?", "Sovngarde", "Skuldafn", "Throat of the World", "Helgen", 14);


            // Skyrim characters
            addQuestion(db, contentValuesQuestions, "What was the name of the High King killed by Ulfric Stormcloak?", "Torygg", "Igmund", "Falknir", "Istlod", 15);
            addQuestion(db, contentValuesQuestions, "Who is the Harbinger of the Companions in Whiterun?", "Kodlak", "Ragnar", "Ivan", "Karthus", 15);
            addQuestion(db, contentValuesQuestions, "Who is the head of the Black-Briar family?", "Maven", "Draven", "Draven!", "Draven", 15);
            addQuestion(db, contentValuesQuestions, "You idiot. Don't you know which subject Calcelmo is a recognized expert in?", "Dwemer", "Potency Potions", "Dragon history", "Illusion magic", 15);
            addQuestion(db, contentValuesQuestions, "Something's off about that Sinding guy in Falkreath Jail. He's a...", "Werewolf", "Smuggler", "Spy", "Vampire", 15);
            addQuestion(db, contentValuesQuestions, "In Morthal lives the secretive Falion. Which of the following is he very knowledgeable on?", "Vampirism", "Illusion magic", "Daedra", "Alchemy", 15);
            addQuestion(db, contentValuesQuestions, "Why was Nelacar expelled from the college of Winterhold?", "\"Bad research\"", "Drinking", "Harassment", "Cat calling", 15);
            addQuestion(db, contentValuesQuestions, "What's the name of the item that Nurelion has spent his entire life chasing?", "White Phial", "Black Book", "Silver Key", "Great Kadath", 15);
            addQuestion(db, contentValuesQuestions, "This merchant seems to have a strnage interest in buying and selling relatives.", "Belethor", "Farengar", "Ulfberth", "Elrindir", 15);
            addQuestion(db, contentValuesQuestions, "This Restoration trainer at the College is desperate to be taken seriously... without luck.", "Colette Marence", "Aphia Velothi", "Carcette", "Baenius", 15);

            // Skyrim spells
/*            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 16);*/

            // Skyrim character build
/*            addQuestion(db, contentValuesQuestions, "Which skill does the book, 'The Doors of Oblivion', increase?", "Conjuration", "Destruction", "Illusion", "Restoration", 17);
            addQuestion(db, contentValuesQuestions, "Which of the following skills do not belong to 'The Thief'-sign?", "One-Handed", "Alchemy", "Speech", "Sneak", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 17);*/

            // Skyrim quotes
/*            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 18);*/

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

/*            // Oblivion items
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 20);*/

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

/*            // Oblivion Spells
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 23);*/

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
            addQuestion(db, contentValuesQuestions, "The Thieves Guild has the guard captain, Hieronymous Lex, transferred from the Imperial City to where?", "Anvil", "Bruma", "Cheydinhal", "Kvatch", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 26);

            // Knights of the Nine
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 35);

            // Shivering Isles
            addQuestion(db, contentValuesQuestions, "", "", "", "", "", 36);
/*
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
*/

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
            addQuestion(db, contentValuesQuestions, "The orc warlock, Ra'Gruzgob, is quite delusional. He belives that he is...", "a Khajit", "an Orc", "a Woman", "a Dog", 29);
            addQuestion(db, contentValuesQuestions, "Which is not one of the three immortal god-kings of Morrowind?", "Indoril", "Vivec", "Sotha Sil", "Almalexia", 29);
            addQuestion(db, contentValuesQuestions, "Which race is Lord Cluttermonkey?", "Argonian", "Khajit", "Nord", "Imperial", 29);
            addQuestion(db, contentValuesQuestions, "Which of the following Morrowind characters was the former lover of Tiber Septim?", "Barenziah", "Almalexia", "Dralsi Indoril", "Lord Cluttermonkey", 29);
            addQuestion(db, contentValuesQuestions, "This Morrowind character authored the famous, 'The Lusty Argonian Maid', and other adult novels.", "Crassius Curio", "Dram Bero", "Velanda Omani", "Nevena Ules", 29);
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

/*
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
*/

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
            addQuestion(db, contentValuesQuestions, "Cassius Olcinius suffers from a problem, and is afraid of asking his father for help. What is it?", "Invisibility", "Teleportation", "Green Skin", "Paranoia", 33);
            addQuestion(db, contentValuesQuestions, "In 'Sleepers Awake' the player suffers from dreams of a tall figure wearing a mask. Which kind of mask is it?", "Golden", "Tribal", "Bone", "Flesh", 33);
            addQuestion(db, contentValuesQuestions, "Who needs help establishing a mine at Raven Rock in the Bloodmon DLC?", "East Empire Company", "Vivec", "Dark Elf Settlement", "Blackwood Company", 33);
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
