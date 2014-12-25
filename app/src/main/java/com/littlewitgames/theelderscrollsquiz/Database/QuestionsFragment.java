package com.littlewitgames.theelderscrollsquiz.Database;


import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.littlewitgames.theelderscrollsquiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsFragment extends Fragment implements DialogInterface.OnClickListener {


    public QuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
