package com.example.tiago.lpoo.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.Logic.CustomEvent;
import com.example.tiago.lpoo.R;

/**
 * Main Game Activity. Manages the main loop activity.
 */
public class GameLoopActivity extends Activity {

    //Attributes:

    /**
     * Custom View where game loop is located
     */
    GameLoopActivityLayout layout;

    /**
     * Image View of the Earth Spell "Button"
     */
    ImageView earthButton;

    /**
     * Image View of the Fire Spell "Button"
     */
    ImageView fireButton;

    /**
     * Image View of the Air Spell "Button"
     */
    ImageView airButton;

    /**
     * Image View of the Water Spell "Button"
     */
    ImageView waterButton;

    //Methods:

    /**
     * On create method. Sets behavior to show spell buttons
     *
     * @param savedInstanceState SavedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetch the xml file for this activity layout and set it as the activity content
        setContentView(R.layout.activity_game_loop);
        //fetch the custom view (where the game loop is running)
        layout = (GameLoopActivityLayout) findViewById(R.id.game_loop_activity_layout);
        //set spell listeners
        earthButton = (ImageView) findViewById(R.id.imageViewEarth);
        earthButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.addMotionEvent(new CustomEvent(event, 'E'));
                return true;
            }
        });
        fireButton = (ImageView) findViewById(R.id.imageViewFire);
        fireButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.addMotionEvent(new CustomEvent(event, 'F'));
                return true;
            }
        });
        airButton = (ImageView) findViewById(R.id.imageViewAir);
        airButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.addMotionEvent(new CustomEvent(event, 'A'));
                return true;
            }
        });
        waterButton = (ImageView) findViewById(R.id.imageViewWater);
        waterButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.addMotionEvent(new CustomEvent(event, 'W'));
                return true;
            }
        });
        layout.setAirButton(airButton);
        layout.setFireButton(fireButton);
        layout.setEarthButton(earthButton);
        layout.setWaterButton(waterButton);
    }

    /**
     * GameLoop Activity pause behavior
     */
    @Override
    protected void onPause() {
        super.onPause();
        layout.pause();
    }

    /**
     * GameLoop Activity resume behavior
     */
    @Override
    protected void onResume() {
        super.onResume();
        layout.resume();
    }
}
