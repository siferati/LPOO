package com.example.tiago.lpoo.Logic;

/**
 * A class that represents a spell's casting state
 */
public class CastingState extends SpellState {

    //Attributes:

    //frameCount
    //frameDuration

    //Methods:

    /**
     * Constructor
     *
     * @param frameDuration How many frames this state lasts
     */
    public CastingState(int frameDuration) {
        super(frameDuration);
    }

    @Override
    public SpellState update(Spell spell) {
        super.update(spell);
        //if the entire animation as been played
        if (frameCount > frameDuration)
            return new ActiveState(frameDuration);
        return null;
    }
}
