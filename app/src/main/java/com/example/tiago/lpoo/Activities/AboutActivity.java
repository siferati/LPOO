package com.example.tiago.lpoo.Activities;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.R;

/**
 * About Activity
 */
public class AboutActivity extends Activity {

    //Methods:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fetch the xml file for this activity layout and set it as the activity content
        setContentView(R.layout.activity_about);

        TextView txtElementalWars = (TextView) findViewById(R.id.elementalWars);
        TextView txtBy = (TextView) findViewById(R.id.by);
        TextView txtIdent = (TextView) findViewById(R.id.ruiSoaresTiagoSilva);
        TextView txtYear = (TextView) findViewById(R.id.year2016);
        Typeface font = Typeface.createFromAsset(getAssets(), "TubeOfCorn.ttf");

        txtElementalWars.setTypeface(font);
        txtBy.setTypeface(font);
        txtIdent.setTypeface(font);
        txtYear.setTypeface(font);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
