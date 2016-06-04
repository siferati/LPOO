package com.example.tiago.lpoo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import android.widget.Button;

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

        playButton.setY(metrics.heightPixels / 4);
        quitButton.setY(metrics.heightPixels / 2);
        aboutButton.setY(3 * metrics.heightPixels / 4);
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
     * Listener for playButton
     *
     * @param view View
     */
    public void ButtonQuitListener(View view) {
        System.exit(0);
    }
}
