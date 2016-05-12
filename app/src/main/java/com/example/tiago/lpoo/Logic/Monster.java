package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

/**
 * Class that represents a Monster
 */
public class Monster extends Entity {

    // Attributes

    /**
     * Monster's Health
     */
    protected int health;

    /**
     * Constructor
     *
     * @param x           X coordinate
     * @param y           Y coordinate
     * @param xSpeed      Speed along the X axis
     * @param ySpeed      Speed along the Y axis
     * @param spriteSheet Sprite Sheet containing the Object's animations
     * @param health      Monster's health
     */
    public Monster(int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet, int health) {
        super(x, y, xSpeed, ySpeed, spriteSheet);
        this.health = health;
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
