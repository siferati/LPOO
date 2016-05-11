package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

public class Monster extends Character {

    // Attributes

    /*
     * Monster's Health
     */
    protected int health;

    /*
     * Monster's Horizontal Speed
     */
    protected int xspeed;

    /*
     * Monster's Vertical Speed
     */
    protected int yspeed;

    /*
     * Constructor
     */
    public Monster(int x, int y, Bitmap bitmap, int health, int xspeed, int yspeed){
        super(x, y, bitmap);
        this.health = health;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }

    /*
     * Default Constructor
     */
    public Monster(){
        super();
        this.health = 0;
        this.xspeed = 0;
        this.yspeed = 0;
    }

    /*
     * Getter
     *
     * @return the monster's health
     */
    public int getHealth(){ return health; }

    /*
     * Getter
     *
     * @return the monster's Horizontal Speed
     */
    public int getXSpeed(){ return xspeed; }

    /*
     * Getter
     *
     * @return the monster's Vertical Speed
     */
    public int getYSpeed(){ return yspeed; }

    /*
     * Setter
     *
     * @param the monster's new health
     */
    public void setHealth(int health) { this.health = health; }

    /*
     * Setter
     *
     * @param the monster's new horizontal speed
     */
    public void setXSpeed(int xspeed){ this.xspeed = xspeed; }

    /*
    * Setter
    *
    * @param the monster's new horizontal speed
    */
    public void setYSpeed(int yspeed){ this.yspeed = yspeed; }

    /*
     * Method to clone - to use in Spawners
     */
    protected Monster clone() {
        return this;
    }

    /*
     * Method to update position according to monster's speed
     */
    public void update(){
        this.updatePosition(this.xspeed, this.yspeed);
    }

}
