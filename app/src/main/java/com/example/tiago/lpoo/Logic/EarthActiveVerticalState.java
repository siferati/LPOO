package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.R;

/**
 * A class that represents the Earth spell's active state
 */
public class EarthActiveVerticalState implements SpellState {
    //Attributes:

    /**
     * How many frames passed since state started
     */
    private int frameCount;

    /**
     * Speed of the animation
     */
    private static final int FPS = 1;

    /**
     * Order of the frames (index) of the animation
     */
    private static final int[] FRAMES = new int[]{7};

    /**
     * Number of rows of animation spriteSheet
     */
    private static final int ROWS = 2;

    /**
     * Number of columns of animation spriteSheet
     */
    private static final int COLS = 4;

    /**
     * Sprite sheet of Animation
     */
    private static Bitmap spriteSheet;

    /**
     * How long this state lasts (in seconds!)
     */
    private static final float STATE_DURATION = (float) 2.0;

    //Methods:

    /**
     * Constructor
     *
     * @param spell Spell
     */
    public EarthActiveVerticalState(Spell spell) {
        //spriteSheet is only initialized once!
        if (spriteSheet == null)
            spriteSheet = BitmapFactory.decodeResource(spell.context.getResources(), R.drawable.earth_spell_vertical);
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
        if (frameCount > STATE_DURATION * GameLoopActivityLayout.UPS)
            return new EarthDestroyingVerticalState(spell);
        return null;
    }
}
