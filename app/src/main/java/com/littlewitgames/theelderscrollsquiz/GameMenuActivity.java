package com.littlewitgames.theelderscrollsquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class GameMenuActivity extends ActionBarActivity {
    private Button skyrimButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        addListeners();
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

    public void addListeners() {
        skyrimButton = (Button) this.findViewById(R.id.skyrimCategoryButton);
        addButton    = (Button) this.findViewById(R.id.addButton);

        skyrimButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View v) {
                startAddList();

            }
        });
    }

    public void startAddList() {
        Intent intent = new Intent(this, QuestionsListActivity.class);
        startActivity(intent);
    }

    public void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        Bundle b = new Bundle();
        b.putString("category", "Skyrim"); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();
    }
}
