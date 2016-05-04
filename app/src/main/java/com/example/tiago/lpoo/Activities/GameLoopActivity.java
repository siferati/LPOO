package com.example.tiago.lpoo.Activities;


import android.app.Activity;
import android.os.Bundle;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;

public class GameLoopActivity extends Activity{

    //Attributes:

    GameLoopActivityLayout layout;

    //Methods:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new GameLoopActivityLayout(this);
        setContentView(layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        layout.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.resume();
    }
}
