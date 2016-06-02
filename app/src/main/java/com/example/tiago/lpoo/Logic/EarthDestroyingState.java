package com.example.tiago.lpoo.Logic;

/**
 * A class that represents the Earth spell's destroying state
 */
public class EarthDestroyingState implements SpellState{

    //Attributes:

    /**
     * How many frames this state lasts
     */
    private final int STATE_DURATION = 30;

    /**
     * How many frames passed since state started
     */
    private int frameCount;


    //Methods:

    /**
     * Constructor
     */
    public EarthDestroyingState() {
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {

    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played signal spell as dead
        if (frameCount > STATE_DURATION)
            spell.setActive(false);
        return null;
    }
}
