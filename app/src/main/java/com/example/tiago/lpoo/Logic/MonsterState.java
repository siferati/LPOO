package com.example.tiago.lpoo.Logic;

/**
 * An interface that represents a spell's state
 * State order: Casting State > Active State > Destroying State
 */
public interface MonsterState {

    /**
     * Update Method
     *
     * @param monster Monster to which this state belongs
     * @return next state
     */
    MonsterState update(Monster monster);

    /**
     * Methods called to enter the new state
     *
     * @param monster Monster to which this state belongs
     */
    void enter(Monster monster);

}
