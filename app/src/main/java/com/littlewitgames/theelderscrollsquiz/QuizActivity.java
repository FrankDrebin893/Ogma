package com.littlewitgames.theelderscrollsquiz;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebBackForwardList;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tagmanager.Container;
import com.littlewitgames.theelderscrollsquiz.Database.QuestionsDataSource;
import com.littlewitgames.theelderscrollsquiz.Models.Quiz;
import com.littlewitgames.theelderscrollsquiz.Models.QuizScoreHelper;
import com.littlewitgames.theelderscrollsquiz.Models.StandardQuestion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends FragmentActivity implements StandardQuestionFragment.OnFragmentInteractionListener {
    private final String SAVESTATE_CURRENT_QUESTION = "currentQuestion";
    private final String SAVESTATE_CORRECT_ANSWERS = "correctAnswers";

    private QuestionsDataSource datasource;
    private QuizScoreHelper quizScoreHelper;

    private TextView questionTextView;
    private Button answerOneButton;
    private Button answerTwoButton;
    private Button answerThreeButton;
    private Button answerFourButton;

    private int totalQuestionsNum;
    private int currentQuestionsNum;
    private int correctQuestionsNum;
    private int quiz_id;

    private String question;
    private String questionText;
    private String correctAnswer;
    private String wrong_answer_one;
    private String wrong_answer_two;
    private String wrong_answer_three;
    private ArrayList<String> answers;
    private List<StandardQuestion> values;

    private StandardQuestionFragment fragment;
    private ScoreScreenFragment scoreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        findViewById(R.id.scoreScreenFragment).setVisibility(View.GONE);



        Bundle b = getIntent().getExtras();
        quiz_id = b.getInt("quiz_id");

        datasource      = new QuestionsDataSource(this);

        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        values          = datasource.getQuestionsFromQuizId(quiz_id);
        quizScoreHelper = new QuizScoreHelper(values.size(), 0);

        this.totalQuestionsNum          = quizScoreHelper.getTotalQuestionsNum();
        this.correctQuestionsNum        = quizScoreHelper.getCorrectQuestions();
        this.currentQuestionsNum        = quizScoreHelper.getCurrentQuestionNum();

        if (savedInstanceState != null) {
            currentQuestionsNum = savedInstanceState.getInt(SAVESTATE_CURRENT_QUESTION) - 1;
            correctQuestionsNum = savedInstanceState.getInt(SAVESTATE_CORRECT_ANSWERS);
        }

        datasource.close();

        getNextQuestion();

/*        initializeValues();
        Bundle fragBundle = createBundle();

        fragment = StandardQuestionFragment.newInstance(question, correctAnswer, wrong_answer_one, wrong_answer_two, wrong_answer_three,
                totalQuestionsNum, correctQuestionsNum, currentQuestionsNum);
        fragment.setArguments(fragBundle);
        getFragmentManager().beginTransaction().replace(R.id.standardQuestionFragment, fragment).commit();*/
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentQuestionsNum = savedInstanceState.getInt(SAVESTATE_CURRENT_QUESTION);
        correctQuestionsNum = savedInstanceState.getInt(SAVESTATE_CORRECT_ANSWERS);
    }

    public void answerListener(boolean isCorrect) {
        if(isCorrect) correctQuestionsNum++;

        if(currentQuestionsNum < totalQuestionsNum) {
            getNextQuestion();
        } else {
            try {
                updateQuizScore();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            startScoreScreen();
        }

    }

    public void getNextQuestion() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        initializeValues();
        fragment = StandardQuestionFragment.newInstance(question, correctAnswer, wrong_answer_one, wrong_answer_two, wrong_answer_three,
                totalQuestionsNum, correctQuestionsNum, currentQuestionsNum);

        ft.replace(R.id.standardQuestionFragment, fragment);
        ft.commit();
    }

    public void startScoreScreen() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        findViewById(R.id.standardQuestionFragment).setVisibility(View.GONE);
        findViewById(R.id.scoreScreenFragment).setVisibility(View.VISIBLE);
        scoreFragment = ScoreScreenFragment.newInstance(totalQuestionsNum, correctQuestionsNum);
        ft.replace(R.id.scoreScreenFragment, scoreFragment);
        ft.commit();
    }

    public void updateQuizScore() throws SQLException {
        Quiz checkScoreQuiz;
        int checkCorrectAnswers;

        datasource.open();
        checkScoreQuiz = datasource.getQuiz(quiz_id);
        checkCorrectAnswers = checkScoreQuiz.getCorrectly_answered();

        if (checkCorrectAnswers < correctQuestionsNum) {
        datasource.updateQuizScore(correctQuestionsNum, totalQuestionsNum, quiz_id);
        }

        datasource.close();
    }

    public void initializeValues() {
        this.currentQuestionsNum++;
        answers                         = new ArrayList<String>();
        StandardQuestion sq             = values.get(this.currentQuestionsNum-1);

        this.question                   = sq.getQuestion();
        this.correctAnswer              = sq.getCorrect_answer();
        this.wrong_answer_one           = sq.getWrong_answer_one();
        this.wrong_answer_two           = sq.getWrong_answer_two();
        this.wrong_answer_three         = sq.getWrong_answer_three();

    }

    public Bundle createBundle() {
        Bundle fragBundle = new Bundle();
        fragBundle.putString("question", question);
        fragBundle.putString("correct", correctAnswer);
        fragBundle.putString("wrongOne", wrong_answer_one);
        fragBundle.putString("wrongTwo", wrong_answer_two);
        fragBundle.putString("wrongThree", wrong_answer_three);
        fragBundle.putInt("totalQuestionsNum", totalQuestionsNum);
        fragBundle.putInt("totalCorrectNum", correctQuestionsNum);
        fragBundle.putInt("currentQuestionNum", currentQuestionsNum);
        return fragBundle;
    }

    public void assignValues() {
        questionTextView  = (TextView) this.findViewById(R.id.standardQuestionTextView);
        answerOneButton   = (Button) this.findViewById(R.id.answerOne);
        answerTwoButton   = (Button) this.findViewById(R.id.answerTwo);
        answerThreeButton = (Button) this.findViewById(R.id.answerThree);
        answerFourButton  = (Button) this.findViewById(R.id.answerFour);

        questionText = "Question " + quizScoreHelper.getCurrentQuestionNum() + "/" + quizScoreHelper.getTotalQuestionsNum() + " - " + question;
        questionTextView.setText(questionText);
        answerOneButton.setText(answers.get(0));
        answerTwoButton.setText(answers.get(1));
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

    public void receiveFragmentIntel(String correctAnswer, String chosenAnswer) {
        System.out.println("INTEL!");
        System.out.println(correctAnswer);
        System.out.println(chosenAnswer);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("Fragment Interaction");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SAVESTATE_CURRENT_QUESTION, currentQuestionsNum);
        savedInstanceState.putInt(SAVESTATE_CORRECT_ANSWERS, correctQuestionsNum);
            super.onSaveInstanceState(savedInstanceState);
    }

    public void returnToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
