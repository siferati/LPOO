package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

/**
 * A class that represents a Spell
 */
public class Spell extends Entity {

    //Attributes:

    //position
    //spriteSheet

    /**
     * This spell's current state
     */
    protected SpellState state;

    /**
     * How many frames this spell takes to cast
     */
    protected int castingDuration;

    //Methods:

    /**
     * Default Constructor
     */
    public Spell() {
        super();
        castingDuration = 0;
    }

    /**
     * Constructor
     *
     * @param x           X coordinate
     * @param y           Y coordinate
     * @param xSpeed      Speed along the X axis
     * @param ySpeed      Speed along the Y axis
     * @param spriteSheet Sprite Sheet containing the Object's animations
     */
    public Spell(int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet) {
        super(x, y, xSpeed, ySpeed, spriteSheet);
        castingDuration = 0;
    }

    @Override
    public void update() {
        super.update();
        SpellState nextState = state.update(this);
        if (nextState != null)
        {
            //free(state);
            //state = nextState;
        }
    }
}
