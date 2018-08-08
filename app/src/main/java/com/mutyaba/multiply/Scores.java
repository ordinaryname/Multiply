package com.mutyaba.multiply;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Scores extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(100, 50, 100, 50);

        SharedPreferences sharedPreferences = getSharedPreferences("com.mutyaba.multiply.scores", Context.MODE_PRIVATE);
        String time = sharedPreferences.getString("times", "");
        String date = sharedPreferences.getString("dates", "");

        if(!time.equals("")) {
            String[] times = time.split(",");
            String[] dates = date.split(",");
            for(int i = 0; i < times.length; i++) {
                TextView textViews = new TextView(this);
                textViews.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textViews.setPadding(30, 30, 30, 30);
                textViews.setTextSize(24f);
                String text = dates[i] + " | " + times[i];
                textViews.setText(text);
                if((i % 2) == 0) {
                    textViews.setBackgroundColor(Color.parseColor("#eeeeee"));
                }
                linearLayout.addView(textViews);
            }
        }

        setContentView(linearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#cccccc"));
    }
}
