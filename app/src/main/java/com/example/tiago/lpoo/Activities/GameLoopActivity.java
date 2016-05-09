package com.example.tiago.lpoo.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
     * Image View of the Earth Spell
     */
    ImageView earthSpell;

    //Methods:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetch the xml file for this activity layout and set it as the activity content
        setContentView(R.layout.activity_game_loop);
        //fetch the custom view (where the game loop is running)
        layout = (GameLoopActivityLayout) findViewById(R.id.game_loop_activity_layout);
        //set spell listeners
        earthSpell = (ImageView) findViewById(R.id.imageViewEarth);
        earthSpell.setOnTouchListener(new View.OnTouchListener() {
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
