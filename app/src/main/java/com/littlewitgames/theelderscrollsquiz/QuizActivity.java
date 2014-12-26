package com.littlewitgames.theelderscrollsquiz;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.littlewitgames.theelderscrollsquiz.Database.QuestionsDataSource;
import com.littlewitgames.theelderscrollsquiz.Models.QuizScoreHelper;
import com.littlewitgames.theelderscrollsquiz.Models.StandardQuestion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends FragmentActivity {
    private QuestionsDataSource datasource;
    private QuizScoreHelper quizScoreHelper;

    private TextView questionTextView;
    private Button answerOneButton;
    private Button answerTwoButton;
    private Button answerThreeButton;
    private Button answerFourButton;

    private String question;
    private String questionText;
    private String correctAnswer;
    private String wrong_answer_one;
    private String wrong_answer_two;
    private String wrong_answer_three;
    private ArrayList<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle b = getIntent().getExtras();
        String category = b.getString("category");

        datasource = new QuestionsDataSource(this);

        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<StandardQuestion> values = datasource.getQuestionsFromCategory(category);
        quizScoreHelper = new QuizScoreHelper(values.size(), 0);

        answers                         = new ArrayList<String>();
        StandardQuestion sq             = values.get(0);
        this.question                   = sq.getQuestion();
        this.correctAnswer              = sq.getCorrect_answer();
        this.wrong_answer_one           = sq.getWrong_answer_one();
        this.wrong_answer_two           = sq.getWrong_answer_two();
        this.wrong_answer_three         = sq.getWrong_answer_three();

        answers.add(correctAnswer);
        answers.add(wrong_answer_one);
        answers.add(wrong_answer_two);
        answers.add(wrong_answer_three);

        Collections.shuffle(answers);

        assignValues();

        //Collections.shuffle(answers);
/*
        Bundle fragBundle = new Bundle();
        fragBundle.putString("question", question);
        fragBundle.putString("correct", correctAnswer);
        fragBundle.putString("wrongOne", wrong_answer_one);
        fragBundle.putString("wrongTwo", wrong_answer_two);
        fragBundle.putString("wrongThree", wrong_answer_three);

        StandardQuestionFragment fragment = StandardQuestionFragment.newInstance(question, correctAnswer, wrong_answer_one, wrong_answer_two, wrong_answer_three);
        fragment.setArguments(fragBundle);
        getFragmentManager().beginTransaction().replace(R.id.standardQuestionFragment, fragment).commit();
*/

    }

    public void assignValues() {
        questionTextView  = (TextView) this.findViewById(R.id.standardQuestionTextView);
        answerOneButton   = (Button) this.findViewById(R.id.answerOne);
        answerTwoButton   = (Button) this.findViewById(R.id.answerTwo);
        answerThreeButton = (Button) this.findViewById(R.id.answerThree);
        answerFourButton  = (Button) this.findViewById(R.id.answerFour);

        questionText = "Question " + quizScoreHelper.getCurrentQuestionNum() + "/" + quizScoreHelper.getTotalQuestionsNum() + " - " + question;
        questionTextView.setText(questionText);
        answerOneButton.setText     (answers.get(0));
        answerTwoButton.setText     (answers.get(1));
        answerThreeButton.setText   (answers.get(2));
        answerFourButton.setText    (answers.get(3));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
