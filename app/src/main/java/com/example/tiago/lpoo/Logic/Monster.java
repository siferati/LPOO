package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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
    /*
     * How long to stay a corpse
     */
    protected int corpseDur;

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
        this.corpseDur = 20;
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
     * Getter
     *
     * @return How to long to stay a corpse (corpseDur)
     */
    public int getCorpseDur() {return corpseDur;}

    /**
     * Setter
     *
     * @param corpseDur new corpse duration
     */
    public void setCorpseDur(int corpseDur) {this.corpseDur = corpseDur;}

    /**
     * Hit the Monster
     * @param hit hp to hit
     */
    public void hit(int hit){
        this.health -= hit;
        if (this.health <= 0)
            this.health = 0;
    }

    public boolean checkDead(){ return this.health == 0; }

    public void decrementCorpseDur() {
        this.corpseDur --;
        if (this.corpseDur < 0)
            this.corpseDur = 0;
    }

    public boolean checkDoneCorpse() {return this.corpseDur == 0;}

    /**
     * Method to clone - to use in Spawners
     *
     * @return this monster
     */
    protected Monster cloneMonster() {
        return this;
    }

    /**
     * Method to clone - to use in spawners
     *
     * @param x new x
     * @param y new y
     * @return this monster, with new x, y
     */
    protected Monster cloneMonster(int x, int y){
        Monster clone = new Monster(this.position.x, this.position.y, this.position.xSpeed, this.position.ySpeed, this.spriteSheet, this.health);
        clone.setPosition(new Position(x, y, this.position.xSpeed, this.position.ySpeed));
        return clone;
    }

    @Override
    public void render(Canvas canvas) {
        //render monster
        super.render(canvas);
        int middle = this.position.x + this.spriteSheet.getWidth() / 2;
        int total = this.health * 5;
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(new Rect(middle - total / 2, this.position.y, middle + total / 2, this.position.y + 5), p);

    }

    public void setSpeedsToWizard(Position wizard_position){
        int dif_x = position.x - wizard_position.x;
        int dif_y = position.y - wizard_position.y;

        int factor = 50; // TODO

        this.position.xSpeed = - dif_x / factor;
        this.position.ySpeed = - dif_y / factor;
    }
}
