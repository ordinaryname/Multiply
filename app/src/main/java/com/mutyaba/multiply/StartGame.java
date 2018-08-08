package com.mutyaba.multiply;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class StartGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#cccccc"));
    }

    public void Start(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void InfoClick(View v) {

    }

    public void StoreClick(View v) {
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    public void ListClick(View v) {
        Intent intent = new Intent(this, Scores.class);
        startActivity(intent);
    }
}
