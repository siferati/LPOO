package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

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
     * TRUE if Monster can't move, FALSE otherwise
     */
    protected boolean rooted;

    /**
     * Direction: N, S, E, W
     */
    protected char direction;

    /**
     * This monster's current state
     */
    protected MonsterState state;

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
    public Monster(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, int health, char direction) {
        super(context);
        this.direction = direction;
        this.health = health;
        this.corpseDur = 20;
        rooted = false;
        //initialize state
        switch (direction) {
            case 'E':
                state = new MonsterRunningRightState(this);
                break;
            case 'W':
                state = new MonsterRunningLeftState(this);
                break;
            case 'N':
                state = new MonsterRunningUpState(this);
                break;
            case 'S':
                state = new MonsterRunningDownState(this);
                break;
            default:
                break;
        }
        //initialize positions
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    /**
     * Default Constructor
     */
    public Monster() {
        super();
        this.health = 0;
        this.corpseDur = 20;
        rooted = false;
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
    public int getCorpseDur() {
        return corpseDur;
    }

    /**
     * @param context the context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Setter
     *
     * @param corpseDur new corpse duration
     */
    public void setCorpseDur(int corpseDur) {
        this.corpseDur = corpseDur;
    }

    /**
     * Hit the Monster
     *
     * @param hit hp to hit
     */
    public void hit(int hit) {
        this.health -= hit;
        if (this.health <= 0)
            this.health = 0;
    }

    public boolean checkDead() {
        return this.health <= 0;
    }

    public void decrementCorpseDur() {
        this.corpseDur--;
        if (this.corpseDur < 0)
            this.corpseDur = 0;
    }

    public boolean checkDoneCorpse() {
        return this.corpseDur == 0;
    }

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
    protected Monster cloneMonster(int x, int y) {
        Monster clone = new Monster(context, false, position.position.left, position.position.top, position.xSpeed, position.ySpeed, health, direction);
        /*clone.setContext(this.context);
        clone.setSprite(this.sprite);
        clone.setPosition(this.position);
        clone.initPosition(false, x, y, this.position.xSpeed, this.position.ySpeed);
        clone.setHealth(this.health);*/
        clone.setRooted(this.rooted);
        //clone.setState(this.state);
        return clone;
    }

    @Override
    public void render(Canvas canvas) {
        //render monster
        super.render(canvas);
        int middle = this.getPosition().position.centerX();
        int total = toPixels(this.health);
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(new Rect(middle - total / 2, this.position.position.top, middle + total / 2, this.position.position.top + toPixels(2)), p);

    }

    public void setSpeedsToWizard(Position wizard_position) {
        float dif_x = position.position.centerX() - wizard_position.position.centerX();
        float dif_y = position.position.centerY() - wizard_position.position.centerY();

        Random rand = new Random();
        int factor = rand.nextInt(50) + 50;

        this.position.xSpeed = (int) (-dif_x / factor);
        this.position.ySpeed = (int) (-dif_y / factor);
    }

    @Override
    public void update() {
        //TODO this won't work cause of sprite.update on super.update. Change sprite.update onto state.update!!
        if (!rooted)
            super.update();
        //reset rooted
        rooted = false;
        state.update(this);
        sprite.update();
    }

    public boolean isRooted() {
        return rooted;
    }

    public void setRooted(boolean rooted) {
        this.rooted = rooted;
    }

    public void setState(MonsterState state) {
        this.state = state;
    }
}
