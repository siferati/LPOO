package com.example.tiago.lpoo.Logic;

/**
 * A class that represents a spell's casting state
 */
public class CastingState extends SpellState {

    //Attributes:

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
    public SpellState update() {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > stateDuration)
            return new ActiveState(stateDuration);
        return null;
    }

    @Override
    public void enter(Spell spell) {
        //change sprite
        spell.getSprite().init(30, 0, 2);
    }
}
