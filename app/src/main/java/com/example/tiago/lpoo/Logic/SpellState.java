package com.example.tiago.lpoo.Logic;

/**
 * An abstract class that represents a spell's state
 * State order: Casting State > Active State
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
    protected int stateDuration;

    //Methods:

    /**
     * Constructor
     *
     * @param stateDuration How many frames this state lasts
     */
    public SpellState(int stateDuration) {
        frameCount = 0;
        this.stateDuration = stateDuration;
    }

    /**
     * Update Method
     *
     * @return next state
     */
    public abstract SpellState update();

    /**
     * Methods called to enter the new state
     *
     * @param spell Spell to which this state belongs
     */
    public abstract void enter(Spell spell);

}

