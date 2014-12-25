package com.littlewitgames.theelderscrollsquiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
    private static final String ARG_QUESTION            = "param1";
    private static final String ARG_CORRECT_ANSWER      = "param2";
    private static final String ARG_WRONG_ANSWER_ONE    = "param3";
    private static final String ARG_WRONG_ANSWER_TWO    = "param4";
    private static final String ARG_WRONG_ANSWER_THREE  = "param5";


    // TODO: Rename and change types of parameters
    private String question;
    private String correct_answer;
    private String wrong_answer_one;
    private String wrong_answer_two;
    private String wrong_answer_three;


    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static StandardQuestionFragment newInstance(String question, String correct_answer, String wrong_answer_one, String wrong_answer_two, String wrong_answer_three) {
        StandardQuestionFragment fragment = new StandardQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putString(ARG_CORRECT_ANSWER, correct_answer);
        args.putString(ARG_WRONG_ANSWER_ONE, wrong_answer_one);
        args.putString(ARG_WRONG_ANSWER_TWO, wrong_answer_two);
        args.putString(ARG_WRONG_ANSWER_THREE, wrong_answer_three);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standard_question, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
