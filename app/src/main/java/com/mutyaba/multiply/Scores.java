package com.mutyaba.multiply;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

public class Scores extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String time = sharedPreferences.getString("times", "");
        String date = sharedPreferences.getString("dates", "");

        if(!time.equals("")) {
            String[] times = time.split(",");
            String[] dates = date.split(",");
            for(int i = 0; i < times.length; i++) {
                TextView textViews = new TextView(this);
                textViews.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textViews.setPadding(20, 20, 20, 20);
                textViews.setTextSize(24f);
                String text = dates[i] + " | " + times[i];
                textViews.setText(text);
            }
        }
    }
}
