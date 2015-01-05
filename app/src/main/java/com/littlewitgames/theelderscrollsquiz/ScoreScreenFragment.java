package com.littlewitgames.theelderscrollsquiz;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoreScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoreScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreScreenFragment extends Fragment {
    private static final String ARG_TOTAL_QUESTIONS_NUM    = "totalQuestionsNum";
    private static final String ARG_CORRECT_QUESTIONS_NUM  = "correctQuestionsNum";

    private String resultText;

    private int totalQuestionsNum;
    private int correctQuestionsNum;

    private TextView resultTextView;
    private Button exitButton;

    private OnFragmentInteractionListener mListener;

    public static ScoreScreenFragment newInstance(int totalQuestions, int correctAnswers) {
        ScoreScreenFragment fragment = new ScoreScreenFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TOTAL_QUESTIONS_NUM, totalQuestions);
        args.putInt(ARG_CORRECT_QUESTIONS_NUM, correctAnswers);
        fragment.setArguments(args);
        return fragment;
    }

    public ScoreScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.totalQuestionsNum   = getArguments().getInt(ARG_TOTAL_QUESTIONS_NUM);
            this.correctQuestionsNum = getArguments().getInt(ARG_CORRECT_QUESTIONS_NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score_screen, container, false);
        assignValues(view);
        return view;
    }

    public void assignValues(View view) {
        resultTextView = (TextView) view.findViewById(R.id.scoreTextView);
        exitButton     = (Button) view.findViewById(R.id.okayButton);

        int one = this.correctQuestionsNum;
        int two = this.totalQuestionsNum;

        resultText = "Result: " + one + " / " + two + " correctly answered questions..";
        resultTextView.setText(resultText);
        exitButton.setText("To main menu");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

}
