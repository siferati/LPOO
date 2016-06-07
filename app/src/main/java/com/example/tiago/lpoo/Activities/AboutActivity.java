package com.example.tiago.lpoo.Activities;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import com.example.tiago.lpoo.R;

/**
 * The About screen's Activity
 */
public class AboutActivity extends Activity {

    //Methods:

    /**
     * Create activity and show it
     * @param savedInstanceState Last Instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fetch the xml file for this activity layout and set it as the activity content
        setContentView(R.layout.activity_about);

        TextView txtElementalWars = (TextView) findViewById(R.id.elementalWars);
        TextView txtBy = (TextView) findViewById(R.id.by);
        TextView txtIdent = (TextView) findViewById(R.id.ruiSoaresTiagoSilva);
        TextView txtYear = (TextView) findViewById(R.id.year2016);
        TextView txtInstructions = (TextView) findViewById(R.id.instructions);
        Typeface font = Typeface.createFromAsset(getAssets(), "TubeOfCorn.ttf");

        txtElementalWars.setTypeface(font);
        txtBy.setTypeface(font);
        txtIdent.setTypeface(font);
        txtYear.setTypeface(font);
        txtInstructions.setTypeface(font);

    }

    /**
     * About Screen's Pause Behavior
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * About Screen's Resume Behavior
     */
    @Override
    protected void onResume() {
        super.onResume();
    }
}
