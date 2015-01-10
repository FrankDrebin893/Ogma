package com.littlewitgames.theelderscrollsquiz.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.littlewitgames.theelderscrollsquiz.Models.Quiz;
import com.littlewitgames.theelderscrollsquiz.R;

import java.util.List;

/**
 * Created by Rasmus on 10-01-2015.
 */
public class ScoresArrayAdapter extends ArrayAdapter<Quiz> {
    private final Context context;
    private final List<Quiz> quizes;

    public ScoresArrayAdapter(Context context, int resource, List<Quiz> quizes) {
        super(context, resource, R.layout.scores_list_item, quizes);
        this.context = context;
        this.quizes = quizes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.scores_list_item, null);

        Quiz q = getItem(position);

        String scoreString;

        if (q.getTotal_questions() != 0) {
         scoreString = q.getCorrectly_answered() + " / " + q.getTotal_questions();
        } else {
            scoreString = "Score hidden. Quiz not played yet.";
        }
        View rowView = vi.inflate(R.layout.scores_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.categoryName);
        textView.setText(q.getName());

        TextView scoresTextView = (TextView) rowView.findViewById((R.id.score));
        scoresTextView.setText(scoreString);

        return rowView;
    }
}
