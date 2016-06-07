package com.example.tiago.lpoo.Logic;

import android.content.Context;

/**
 * A class that represents a Spell
 */
public abstract class Spell extends Entity {

    //Attributes:

    /**
     * This spell's current state
     */
    protected SpellState state;

    /**
     * TRUE if spell is active, FALSE otherwise (When set to FALSE, means object is dead)
     */
    protected boolean active;

    /**
     * Direction of the Spell N, S, E, W
     */
    protected char direction;

    //Methods:

    /**
     * Default Constructor
     */
    public Spell() {
        super();
    }

    /**
     * Constructor
     *
     * @param context   Context
     * @param dps       TRUE if coordinates are in dps, FALSE if they are in pxls
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param xSpeed    Speed along the X axis
     * @param ySpeed    Speed along the Y axis
     * @param direction Direction
     */
    public Spell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, char direction) {
        super(context);
        this.direction = direction;
        state = null;
        sprite = null;
        //initialize positions
        //initPosition(dps, x, y, xSpeed, ySpeed);
        active = true;
    }

    @Override
    public void update() {
        super.update();
        SpellState nextState = state.update(this);
        if (nextState != null) {
            //free(state);
            state = nextState;
            //call the enter action of the new state
            state.enter(this);
        }
    }

    /**
     * Checks collision with the given monster
     *
     * @param monster Monster to check collisions with
     */
    public void checkCollision(Monster monster) {
        if (position.intersects(monster.getPosition()))
            handleCollision(monster);
    }

    /**
     * Handles the collision of given Monster with this Spell
     *
     * @param monster Monster to handle
     */
    protected abstract void handleCollision(Monster monster);

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
