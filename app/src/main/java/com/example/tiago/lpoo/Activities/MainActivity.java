package com.example.tiago.lpoo.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import com.example.tiago.lpoo.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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

        String score = readScoreFile();
        TextView txtScore = (TextView) findViewById(R.id.score);
        txtScore.setTypeface(font);
        txtScore.setText(score);
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

    public String readScoreFile() {
        FileInputStream instream;
        String scoreMessage = "";
        try {
            instream = new FileInputStream("/data/data/com.example.tiago.lpoo/files/highscore.txt");
            if (instream == null) return "High Score: 0";
            // prepare the file for reading
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            scoreMessage = buffreader.readLine();
            if (scoreMessage.equals("")) return "High Score: 0";
            else return scoreMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "High Score: 0";
    }
}

