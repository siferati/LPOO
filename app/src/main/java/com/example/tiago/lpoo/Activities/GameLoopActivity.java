package com.example.tiago.lpoo.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.R;

public class GameLoopActivity extends Activity {

    //Attributes:

    /**
     * Custom View where game loop is located
     */
    GameLoopActivityLayout layout;

    /**
     * Button for Testing
     */
    Button buttonTest;

    //Methods:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetch the xml file for this activity layout and set it as the view
        setContentView(R.layout.activity_game_loop);
        //initialize the custom view
        layout = (GameLoopActivityLayout) findViewById(R.id.game_loop_activity_layout);
        //set button listeners
        buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.addMotionEvent(event);
                return true;
            }
        });
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
