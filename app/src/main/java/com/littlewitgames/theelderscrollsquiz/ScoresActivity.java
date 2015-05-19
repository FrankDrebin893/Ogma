package com.littlewitgames.theelderscrollsquiz;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.littlewitgames.theelderscrollsquiz.Database.QuestionsDataSource;
import com.littlewitgames.theelderscrollsquiz.Models.Quiz;
import com.littlewitgames.theelderscrollsquiz.R;
import com.littlewitgames.theelderscrollsquiz.Utils.ScoresArrayAdapter;

import java.sql.SQLException;
import java.util.List;

public class ScoresActivity extends Activity {
    private QuestionsDataSource dataSource;
    private List<Quiz> quizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        dataSource = new QuestionsDataSource(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        quizes = dataSource.getAllQuizes();

        dataSource.close();



        //ArrayAdapter<Quiz> adapter = new ArrayAdapter<Quiz>(this, R.layout.scores_list_item);
        //this.setListAdapter(adapter);

        ListView listView = (ListView) findViewById(R.id.listview);

        ScoresArrayAdapter adapter = new ScoresArrayAdapter(this, R.layout.scores_list_view, quizes);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
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
