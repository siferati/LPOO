package com.example.tiago.lpoo.Logic;

import android.content.Context;

/**
 * A class that represents a Spell
 */
public abstract class Spell extends Entity {

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
     * @param dps    TRUE if coordinates are in dps, FALSE if they are in pxls
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param xSpeed Speed along the X axis
     * @param ySpeed Speed along the Y axis
     * @param sprite Sprite Sheet containing the Object's animations
     */
    public Spell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Sprite sprite) {
        super(context);
        castingDuration = 0;
        this.sprite = sprite;
        state = null;
        //initialize positions
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    @Override
    public void update() {
        super.update();
        SpellState nextState = state.update();
        if (nextState != null) {
            //free(state);
            state = nextState;
            //call the enter action of the new state
            state.enter(this);
        }
    }
}
