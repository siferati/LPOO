package com.example.tiago.lpoo.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.TextView;

import com.example.tiago.lpoo.R;

/**
 * Main Activity (opens when game is launched)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Button playButton =  (Button) findViewById(R.id.buttonPlay);
        Button quitButton =  (Button) findViewById(R.id.buttonQuit);
        Button aboutButton = (Button) findViewById(R.id.buttonAbout);

        TextView txtElementalWars = (TextView) findViewById(R.id.elementalWars);
        Typeface font = Typeface.createFromAsset(getAssets(), "TubeOfCorn.ttf");
        txtElementalWars.setTypeface(font);
        playButton.setTypeface(font);
        quitButton.setTypeface(font);
        aboutButton.setTypeface(font);
    }

    /**
     * Listener for playButton
     *
     * @param view View
     */
    public void ButtonPlayListener(View view) {
        Intent intent = new Intent(this, GameLoopActivity.class);
        startActivity(intent);
    }

    /**
     * Listener for quitButton
     *
     * @param view View
     */
    public void ButtonQuitListener(View view) {
        System.exit(0);
    }

    public void ButtonAboutListener(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
