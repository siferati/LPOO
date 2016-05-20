package com.example.tiago.lpoo.Logic;

import android.content.Context;

/**
 * Class that represents a Monster
 */
public class Monster extends Entity {

    // Attributes

    /**
     * Monster's Health
     */
    protected int health;

    //TODO fix all javadocs
    /**
     * Constructor
     *
     * @param x       X coordinate (in dps)
     * @param y       Y coordinate (in dps)
     * @param xSpeed  Speed along the X axis (in dps)
     * @param ySpeed  Speed along the Y axis (in dps)
     * @param context Context
     * @param health  Monster's health
     */
    public Monster(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, int health) {
        super(context);
        this.health = health;
        //initialize positions
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    /**
     * Default Constructor
     */
    public Monster() {
        super();
        this.health = 0;
    }

    /**
     * Getter
     *
     * @return The monster's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter
     *
     * @param health the monster's health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to clone - to use in Spawners
     *
     * @return this monster
     */
    protected Monster cloneMonster() {
        return this;
    }

}
