package com.littlewitgames.theelderscrollsquiz;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StandardQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StandardQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandardQuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_QUESTION            = "question";
    private static final String ARG_CORRECT_ANSWER      = "correct";
    private static final String ARG_WRONG_ANSWER_ONE    = "wrongOne";
    private static final String ARG_WRONG_ANSWER_TWO    = "wrongTwo";
    private static final String ARG_WRONG_ANSWER_THREE  = "wrongThree";

    private static final String ARG_TOTAL_QUESTIONS_NUM    = "totalQuestionsNum";
    private static final String ARG_CURRENT_QUESTION_NUM   = "currentQuestionNum";
    private static final String ARG_CORRECT_QUESTIONS_NUM  = "correctQuestionsNum";



    // TODO: Rename and change types of parameters
    private String question;
    private String questionText;
    private String correct_answer;
    private String wrong_answer_one;
    private String wrong_answer_two;
    private String wrong_answer_three;

    private int totalQuestionsNum;
    private int currentQuestionsNum;
    private int correctQuestionsNum;

    private TextView questionTextView;
    private Button answerOneButton;
    private Button answerTwoButton;
    private Button answerThreeButton;
    private Button answerFourButton;
    private ArrayList<String> answers;
    private String chosenAnswer;
    private boolean isCorrect;

    private Animation animAlpha;


    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static StandardQuestionFragment newInstance(String question, String correct_answer, String wrong_answer_one, String wrong_answer_two, String wrong_answer_three,
                                                       int totalQuestionsNum, int totalCorrectQuestionsNum, int currentQuestionNum) {
        StandardQuestionFragment fragment = new StandardQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putString(ARG_CORRECT_ANSWER, correct_answer);
        args.putString(ARG_WRONG_ANSWER_ONE, wrong_answer_one);
        args.putString(ARG_WRONG_ANSWER_TWO, wrong_answer_two);
        args.putString(ARG_WRONG_ANSWER_THREE, wrong_answer_three);
        args.putInt(ARG_TOTAL_QUESTIONS_NUM, totalQuestionsNum);
        args.putInt(ARG_CORRECT_QUESTIONS_NUM, totalCorrectQuestionsNum);
        args.putInt(ARG_CURRENT_QUESTION_NUM, currentQuestionNum);
        fragment.setArguments(args);
        return fragment;
    }

    public StandardQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.question = getArguments().getString(ARG_QUESTION);
            this.correct_answer = getArguments().getString(ARG_CORRECT_ANSWER);
            this.wrong_answer_one = getArguments().getString(ARG_WRONG_ANSWER_ONE);
            this.wrong_answer_two = getArguments().getString(ARG_WRONG_ANSWER_TWO);
            this.wrong_answer_three = getArguments().getString(ARG_WRONG_ANSWER_THREE);
            this.totalQuestionsNum = getArguments().getInt(ARG_TOTAL_QUESTIONS_NUM);
            this.correctQuestionsNum = getArguments().getInt(ARG_CORRECT_QUESTIONS_NUM);
            this.currentQuestionsNum = getArguments().getInt(ARG_CURRENT_QUESTION_NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_standard_question, container, false);
        assignValues(view);
        animAlpha = AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_alpha);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public void assignValues(View view) {
        questionTextView  = (TextView) view.findViewById(R.id.standardQuestionTextView);
        answerOneButton   = (Button) view.findViewById(R.id.answerOne);
        answerTwoButton   = (Button) view.findViewById(R.id.answerTwo);
        answerThreeButton = (Button) view.findViewById(R.id.answerThree);
        answerFourButton  = (Button) view.findViewById(R.id.answerFour);

        shuffleAnswers();

        questionText = "Question " + currentQuestionsNum + "/" + totalQuestionsNum + " - " + question;
        questionTextView.setText    (questionText);
        answerOneButton.setText     (answers.get(0));
        answerTwoButton.setText     (answers.get(1));
        answerThreeButton.setText   (answers.get(2));
        answerFourButton.setText(answers.get(3));

        answerOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deactivateButtons();
                recolorButton(v);
                chosenAnswer = answers.get(0);
                System.out.println("Button 1 pressed and answer = " + chosenAnswer);
                ((QuizActivity)getActivity()).receiveFragmentIntel(correct_answer, chosenAnswer);
                returnResult(v);
            }
        });

        answerTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deactivateButtons();
                recolorButton(v);
                chosenAnswer = answers.get(1);
                System.out.println("Button 2 pressed and answer = " + chosenAnswer);
                ((QuizActivity)getActivity()).receiveFragmentIntel(correct_answer, chosenAnswer);
                returnResult(v);
            }
        });

        answerThreeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deactivateButtons();
                recolorButton(v);
                chosenAnswer = answers.get(2);
                System.out.println("Button 3 pressed and answer = " + chosenAnswer);
                ((QuizActivity)getActivity()).receiveFragmentIntel(correct_answer, chosenAnswer);
                returnResult(v);
            }
        });

        answerFourButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deactivateButtons();
                recolorButton(v);
                chosenAnswer = answers.get(3);
                System.out.println("Button 4 pressed and answer = " + chosenAnswer);
                ((QuizActivity)getActivity()).receiveFragmentIntel(correct_answer, chosenAnswer);
                returnResult(v);
            }
        });
    }

    public void returnResult(View v) {
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((QuizActivity)getActivity()).answerListener(isCorrect());
            }
        }, 400);
    }

    public void recolorButton(View v) {
        Button b = (Button)v;
        String btn_text = b.getText().toString();
        b.setTextColor(Color.WHITE);


        if(btn_text.equals(correct_answer)) {
            v.setActivated(true);
        }
        else {
            v.setSelected(true);
        }

        v.startAnimation(animAlpha);
    }


    public void shuffleAnswers() {
        answers = new ArrayList<String>();
        answers.add(correct_answer);
        answers.add(wrong_answer_one);
        answers.add(wrong_answer_two);
        answers.add(wrong_answer_three);

        Collections.shuffle(answers);
    }

    public boolean isCorrect () {
        if(chosenAnswer.equals(correct_answer)) { return true; }
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void deactivateButtons() {
        answerOneButton.setEnabled(false);
        answerOneButton.setTextColor(Color.BLACK);
        answerTwoButton.setEnabled(false);
        answerTwoButton.setTextColor(Color.BLACK);
        answerThreeButton.setEnabled(false);
        answerThreeButton.setTextColor(Color.BLACK);
        answerFourButton.setEnabled(false);
        answerFourButton.setTextColor(Color.BLACK);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getFragmentManager().findFragmentById(this.getId()).setRetainInstance(true);
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getFragmentManager().findFragmentById(this.getId()).getRetainInstance();

    }





}
