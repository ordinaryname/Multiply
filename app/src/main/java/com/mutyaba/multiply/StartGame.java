package com.mutyaba.multiply;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
    }

    public void Start(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void InfoClick(View v) {

    }

    public void StoreClick(View v) {

    }

    public void ListClick(View v) {
        Intent intent = new Intent(this, Scores.class);
        startActivity(intent);
    }
}
