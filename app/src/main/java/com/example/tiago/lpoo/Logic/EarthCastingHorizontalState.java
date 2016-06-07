package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.R;

/**
 * A class that represents the Earth spell's casting state
 */
public class EarthCastingHorizontalState implements SpellState {

    //Attributes:

    /**
     * How many frames passed since state started
     */
    private int frameCount;

    /**
     * Speed of the animation
     */
    private static final int FPS = 10;

    /**
     * Order of the frames (index) of the animation
     */
    private static final int[] FRAMES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * How long this state lasts (in frames)
     */
    private static final float STATE_DURATION = FRAMES.length * GameLoopActivityLayout.UPS / FPS;

    /**
     * Number of rows of animation spriteSheet
     */
    private static final int ROWS = 4;

    /**
     * Number of columns of animation spriteSheet
     */
    private static final int COLS = 4;

    /**
     * Sprite sheet of Animation
     */
    private static Bitmap spriteSheet;


    //Methods:

    /**
     * Construcotr
     *
     * @param spell
     */
    public EarthCastingHorizontalState(Spell spell) {
        //spriteSheet is only initialized once!
        if (spriteSheet == null)
            spriteSheet = BitmapFactory.decodeResource(spell.context.getResources(), R.drawable.earth_spell);
        spell.sprite = new Sprite(spriteSheet, ROWS, COLS);
        enter(spell);
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {
        //set correct animation
        spell.sprite.init(FPS, FRAMES);
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION)
            return new EarthActiveHorizontalState(spell);
        return null;
    }
}
