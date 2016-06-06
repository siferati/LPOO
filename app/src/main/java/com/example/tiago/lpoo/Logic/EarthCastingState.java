package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class that represents the Earth spell's casting state
 */
public class EarthCastingState implements SpellState {

    //Attributes:

    /**
     * How many frames passed since state started
     */
    private int frameCount;

    //-------------------------------------------------------------

    /**
     * How long this state lasts (in seconds)
     */
    private static final float STATE_DURATION;

    /**
     * Speed of the animation
     */
    private static final int FPS;

    /**
     * Order of the frames (index) of the animation
     */
    private static final int[] FRAMES;

    /**
     * spritesheet
     */
    private Bitmap spriteSheet;


    static {

        InputStream instream;
        float dur = 0;
        int framesSize = 0;
        int fps = 0;
        int columns = 0;
        int rows = 0;
        int[] frames = new int[50];
        try {
            instream = new FileInputStream("/data/data/com.example.tiago.lpoo/files/earthCastingState.txt");
            // prepare the file for reading
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            String descriptionLine, durationLine, fpsLine, spritesLine, spriteLine, rowsLine, colsLine;
            int nSprites = 0;
            descriptionLine = buffreader.readLine();
            durationLine = buffreader.readLine();
            dur = Float.parseFloat(durationLine.split("-")[1]);
            fpsLine = buffreader.readLine();
            fps = Integer.parseInt(fpsLine.split("-")[1]);
            rowsLine = buffreader.readLine();
            rows = Integer.parseInt(rowsLine.split("-")[1]);
            colsLine = buffreader.readLine();
            columns = Integer.parseInt(rowsLine.split("-")[1]);
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

        STATE_DURATION = dur;
        FPS = fps;
        FRAMES = new int[framesSize];
        System.arraycopy(frames, 0, FRAMES, 0, framesSize);
    }



    //--------------------------------------------------------------


    //Methods:

    /**
     * Constructor
     */
    public EarthCastingState(Spell spell, Bitmap spriteSheet) {
        frameCount = 0;
        this.spriteSheet = spriteSheet;
        spell.sprite = new Sprite(spriteSheet, 4,4);
        spell.sprite.init(FPS, FRAMES);
    }


    @Override
    public void enter(Spell spell) {
        //set correct animation
        spell.getSprite().init(FPS, FRAMES);
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION * GameLoopActivityLayout.UPS)
            return new EarthActiveState(spell,spriteSheet);
        return null;
    }
}
