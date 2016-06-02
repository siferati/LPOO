package com.example.tiago.lpoo.Logic;

/**
 * An interface that represents a spell's state
 * State order: Casting State > Active State > Destroying State
 */
public interface SpellState {

    /**
     * Update Method
     *
     * @param spell Spell to which this state belongs
     * @return next state
     */
    SpellState update(Spell spell);

    /**
     * Methods called to enter the new state
     *
     * @param spell Spell to which this state belongs
     */
    void enter(Spell spell);

}

