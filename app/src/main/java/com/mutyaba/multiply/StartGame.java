package com.mutyaba.multiply;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class StartGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, getString(R.string.admob_id));
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
