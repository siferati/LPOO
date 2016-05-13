package com.example.tiago.lpoo.Logic;

import javax.xml.datatype.Duration;

/**
 * An abstract class that represents a spell's state
 */
public abstract class SpellState {

    //Attributes:

    /**
     * How many frames have passed since the Spell started being cast
     */
    protected int frameCount;

    /**
     * How many frames this state lasts
     */
    protected int frameDuration;

    //Methods:

    /**
     * Constructor
     * @param frameDuration How many frames this state lasts
     */
    public SpellState(int frameDuration) {
        frameCount = 0;
        this.frameDuration = frameDuration;
    }

    /**
     * Updates the spell's state
     *
     * @param spell Spell
     */
    public SpellState update(Spell spell)
    {
        //add 1 frame to the count
        frameCount++;
        return null;
    }
}

