package com.example.tiago.lpoo.Logic;

/**
 * A class that represents a spell's active state
 * A spell is considered active after being cast
 */
public class ActiveState extends SpellState {

    //Attributes:

    //frameCount
    //frameDuration

    //Methods:

    /**
     * Constructor
     *
     * @param frameDuration How many frames this state lasts
     */
    public ActiveState(int frameDuration) {
        super(frameDuration);
    }
}
