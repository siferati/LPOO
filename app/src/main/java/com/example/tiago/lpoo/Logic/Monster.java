package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

public class Monster extends Entity {

    // Attributes

    /*
     * Monster's Health
     */
    protected int health;

    /*
     * Constructor
     */
    public Monster(int x, int y, int xSpeed, int ySpeed, Bitmap bitmap, int health) {
        super(x, y, xSpeed, ySpeed, bitmap);
        this.health = health;
    }

    /*
     * Default Constructor
     */
    public Monster() {
        super();
        this.health = 0;
    }

    /*
     * Getter
     *
     * @return the monster's health
     */
    public int getHealth() {
        return health;
    }

    /*
     * Setter
     *
     * @param the monster's new health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /*
     * Method to clone - to use in Spawners
     */
    protected Monster clone() {
        return this;
    }

}
