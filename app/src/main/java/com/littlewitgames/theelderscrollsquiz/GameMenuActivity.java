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
            case R.id.historyButton:
                startQuiz(1);
                break;
            case R.id.cultureButton:
                startQuiz(2);
                break;
            case R.id.musicButton:
                startQuiz(3);
                break;
            case R.id.personalitiesButton:
                startQuiz(4);
                break;
            case R.id.foodButton:
                startQuiz(5);
                break;
            case R.id.wildlifeButton:
                startQuiz(6);
                break;
            case R.id.dragonsButton:
                startQuiz(7);
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
