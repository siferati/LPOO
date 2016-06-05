package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.util.Log;

import com.example.tiago.lpoo.Activities.GameLoopActivity;
import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * A class that represents the Earth spell's casting state
 */
public class EarthCastingState implements SpellState {

    //Attributes:

    /**
     * How many frames this state lasts
     */
    private final int STATE_DURATION = 30;

    /**
     * How many frames passed since state started
     */
    private int frameCount;

    //-------------------------------------------------------------

    /**
     * How long this state lasts (in seconds)
     */
    private static final float DURATION;

    /**
     * Speed of the animation
     */
    private static final int FPS;

    /**
     * Order of the frames (index) of the animation
     */
    private static final int[] FRAMES;


    static {

        InputStream instream;
        float dur = 0;
        int framesSize = 0;
        int fps = 0;
        int[] frames = new int[50];
        try {
            instream = new FileInputStream("/data/data/com.example.tiago.lpoo/files/earthCastingState.txt");
            // prepare the file for reading
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            String descriptionLine, durationLine, fpsLine, spritesLine, spriteLine, trash;
            int nSprites = 0;
            descriptionLine = buffreader.readLine();
            Log.w("descr", "Description: " + descriptionLine);
            durationLine = buffreader.readLine();
            Log.w("duration", "Duration: " + durationLine);
            dur = Float.parseFloat(durationLine.split("-")[1]);
            fpsLine = buffreader.readLine();
            fps = Integer.parseInt(fpsLine.split("-")[1]);
            spritesLine = buffreader.readLine();
            framesSize = Integer.parseInt(spritesLine.split("-")[1]);
            do {
                spriteLine = buffreader.readLine();
                if (spriteLine == null) break;
                frames[nSprites] = Integer.parseInt(spriteLine.split("-")[1]);
                nSprites++;
            } while (spriteLine != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DURATION = dur;
        FPS = fps;
        FRAMES = new int[framesSize];


    }



    //--------------------------------------------------------------


    //Methods:

    /**
     * Constructor
     */
    public EarthCastingState() {
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {
        //set correct animation
        spell.getSprite().init(10, 0, 9);
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION)
            return new EarthActiveState();
        return null;
    }
}
