package com.example.tiago.lpoo.Logic;

/**
 * A class that represents the Earth spell's casting state
 */
public class EarthActiveState implements SpellState{

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
    public EarthActiveState() {
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {
        spell.getSprite().init(30, 9, 9);
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION)
            return new EarthDestroyingState();
        return null;
    }
}
