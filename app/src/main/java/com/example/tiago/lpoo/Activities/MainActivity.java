package com.example.tiago.lpoo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.lpoo.R;

/**
 * Main Activity (opens when game is launched)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
