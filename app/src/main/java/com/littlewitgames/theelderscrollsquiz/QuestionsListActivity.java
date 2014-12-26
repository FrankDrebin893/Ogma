package com.littlewitgames.theelderscrollsquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import com.littlewitgames.theelderscrollsquiz.Database.*;
import com.littlewitgames.theelderscrollsquiz.Models.StandardQuestion;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


public class QuestionsListActivity extends ListActivity {
    private QuestionsDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        datasource = new QuestionsDataSource (this);

        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<StandardQuestion> values = datasource.getAllStandardQuestions();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<StandardQuestion> adapter = new ArrayAdapter<StandardQuestion>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<StandardQuestion> adapter = (ArrayAdapter<StandardQuestion>) getListAdapter();
        StandardQuestion standardQuestion = null;

        StandardQuestion standardQuestionTest = new StandardQuestion("What is your name?", "Rasmus", "Michael", "John", "George", "Names");

        switch (view.getId()) {
            case R.id.add:
                String[] standardQuestions = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new standardQuestion to the database
                standardQuestion = datasource.createStandardQuestion("What is your name?", "Rasmus", "Michael", "John", "George", "Skyrim");
                adapter.add(standardQuestion);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    standardQuestion = (StandardQuestion) getListAdapter().getItem(0);
                    datasource.deleteStandardQuestion(standardQuestion);
                    adapter.remove(standardQuestion);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questions_list, menu);
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
}
