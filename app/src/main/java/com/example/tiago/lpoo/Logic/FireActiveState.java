package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.R;

/**
 * A class that represents the Fire spell's active state
 */
public class FireActiveState implements SpellState {

    //Attributes:

    /**
     * How many frames passed since state started
     */
    private int frameCount;

    /**
     * Speed of the animation
     */
    private static final int FPS = 5;

    /**
     * Order of the frames (index) of the animation
     */
    private static final int[] FRAMES = new int[]{3, 4, 5, 6};

    /**
     * Number of rows of animation spriteSheet
     */
    private static final int ROWS = 1;

    /**
     * Number of columns of animation spriteSheet
     */
    private static final int COLS = 7;

    /**
     * Sprite sheet of Animation
     */
    private static Bitmap spriteSheet;

    /**
     * How long this state lasts (in seconds!)
     */
    private static final float STATE_DURATION = (float) 3.0;

    //Methods:

    /**
     * Constructor
     *
     * @param spell Spell
     */
    public FireActiveState(Spell spell) {
        //spriteSheet is only initialized once!
        if (spriteSheet == null)
            spriteSheet = BitmapFactory.decodeResource(spell.context.getResources(), R.drawable.fire_spell_right);
        spell.sprite = new Sprite(spriteSheet, ROWS, COLS);
        enter(spell);
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {
        //set correct animation
        spell.sprite.init(FPS, FRAMES);
        spell.position.xSpeed = 0;
        spell.position.ySpeed = 0;
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION * GameLoopActivityLayout.UPS)
            spell.setActive(false);
        return null;
    }
}
