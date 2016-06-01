package com.example.tiago.lpoo.Logic;

/**
 * A class that represents a spell's active state
 * A spell is considered active after being cast
 */
public class ActiveState extends SpellState {

    //Attributes:

    //Methods:

    /**
     * Constructor
     *
     * @param frameDuration How many frames this state lasts
     */
    public ActiveState(int frameDuration) {
        super(frameDuration);
    }

    @Override
    public void enter(Spell spell) {
        //change sprite
        spell.getSprite().init(30, 9, 9);
    }

    @Override
    public SpellState update() {
        return null;
    }
}
