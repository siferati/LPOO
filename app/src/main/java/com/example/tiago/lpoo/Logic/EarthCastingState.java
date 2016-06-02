package com.example.tiago.lpoo.Logic;

/**
 * A class that represents the Earth spell's casting state
 */
public class EarthCastingState implements SpellState {

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
    public EarthCastingState() {
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {
        //set correct animation
        spell.getSprite().init(5, 0, 8);
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION)
            return new EarthActiveState();
        return null;
    }
}
