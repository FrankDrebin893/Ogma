package com.littlewitgames.theelderscrollsquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.littlewitgames.theelderscrollsquiz.Database.QuestionsDataSource;
import com.littlewitgames.theelderscrollsquiz.Models.Quiz;

import java.sql.SQLException;
import java.util.List;


public class GameMenuActivity extends ActionBarActivity implements View.OnClickListener {
    private QuestionsDataSource dataSource;
    private List<Quiz> quizes;

    private Button geographyButton;
    private Button historyButton;
    private Button cultureButton;

    private Button skyrimButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        dataSource = new QuestionsDataSource(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        quizes = dataSource.getAllQuizes();

        dataSource.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void quizButtonListener(View v) {
        switch (v.getId()) {
            case R.id.geographyButton:
                startQuiz(0);
                break;
            case R.id.factionsButton:
                startQuiz(1);
                break;
            case R.id.racesButton:
                startQuiz(2);
                break;
            case R.id.historyButton:
                startQuiz(3);
                break;
            case R.id.cultureButton:
                startQuiz(4);
                break;
            case R.id.musicButton:
                startQuiz(5);
                break;
            case R.id.personalitiesButton:
                startQuiz(6);
                break;
            case R.id.foodButton:
                startQuiz(7);
                break;
            case R.id.artifactsButton:
                startQuiz(8);
                break;
            case R.id.wildlifeButton:
                startQuiz(9);
                break;
            case R.id.dragonsButton:
                startQuiz(10);
                break;
            case R.id.daedricButton:
                startQuiz(11);
                break;
            case R.id.ninedivinesButton:
                startQuiz(12);
                break;
            case R.id.skyrimItemsButton:
                startQuiz(13);
                break;
            case R.id.skyrimStoryButton:
                startQuiz(14);
                break;
            case R.id.skyrimCharactersButton:
                startQuiz(15);
                break;
            case R.id.skyrimSpellsButton:
                startQuiz(16);
                break;
            case R.id.skyrimCharacterBuildButton:
                startQuiz(17);
                break;
            case R.id.skyrimQuotesButton:
                startQuiz(18);
                break;
            case R.id.skyrimQuestsButton:
                startQuiz(19);
                break;
            // Oblivion
            case R.id.oblivionItemsButton:
                startQuiz(20);
                break;
            case R.id.oblivionStoryButton:
                startQuiz(21);
                break;
            case R.id.oblivionCharactersButton:
                startQuiz(22);
                break;
            case R.id.oblivionSpellsButton:
                startQuiz(23);
                break;
            case R.id.oblivionCharacterBuildButton:
                startQuiz(24);
                break;
            case R.id.oblivionQuotesButton:
                startQuiz(25);
                break;
            case R.id.oblivionQuestsButton:
                startQuiz(26);
                break;
            // Morrowind cases
            case R.id.morrowindItemsButton:
                startQuiz(27);
                break;
            case R.id.morrowindStoryButton:
                startQuiz(28);
                break;
            case R.id.morrowindCharactersButton:
                startQuiz(29);
                break;
            case R.id.morrowindSpellsButton:
                startQuiz(30);
                break;
            case R.id.morrowindCharacterBuildButton:
                startQuiz(31);
                break;
            case R.id.morrowindQuotesButton:
                startQuiz(32);
                break;
            case R.id.morrowindQuestsButton:
                startQuiz(33);
                break;


        }
    }

    public void startQuiz(int id) {
        Intent intent = new Intent(this, QuizActivity.class);
        Bundle b = new Bundle();
        b.putInt("quiz_id", id); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.geographyButton:
                startQuiz(0);
                break;
        }
    }
}
