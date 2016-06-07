package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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

    /*
     * How long to stay a corpse
     */
    protected int corpseDur;

    /**
     * TRUE if Monster can't move, FALSE otherwise
     */
    protected boolean rooted;

    /**
     * Speed of push back
     */
    protected int pushedXSpeed;

    /**
     * Speed of push back
     */
    protected int pushedYSpeed;

    /**
     * Direction: N, S, E, W
     */
    protected char direction;

    /**
     * This monster's current state
     */
    protected MonsterState state;

    /**
     * @param context   Context
     * @param dps       TRUE if coords are in dps, FALSE otherwise
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param xSpeed    Speed on X axis
     * @param ySpeed    Speed on Y axis
     * @param health    Health
     * @param direction Direction
     */
    public Monster(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, int health, char direction) {
        super(context);
        this.direction = direction;
        this.health = health;
        this.corpseDur = 0;
        rooted = false;
        pushedXSpeed = 0;
        pushedYSpeed = 0;
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
        this.corpseDur = 0;
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

    /**
     * Check if monster is dead
     *
     * @return TRUE if monster is dead, FALSE otherwise
     */
    public boolean checkDead() {
        return this.health <= 0;
    }

    /**
     * Update corpse duration
     */
    public void decrementCorpseDur() {
        this.corpseDur--;
        if (this.corpseDur < 0)
            this.corpseDur = 0;
    }

    /**
     * Check corpse duration (almost like a getter)
     *
     * @return corpse duration
     */
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

    /**
     * Set Speed toward Wizard
     *
     * @param wizard_position Wizard position
     */
    public void setSpeedsToWizard(Position wizard_position) {
        float dif_x = position.position.centerX() - wizard_position.position.centerX();
        float dif_y = position.position.centerY() - wizard_position.position.centerY();

        Random rand = new Random();
        int factor = rand.nextInt(50) + 75;

        this.position.xSpeed = (int) (-dif_x / factor);
        this.position.ySpeed = (int) (-dif_y / factor);
    }

    @Override
    public void update() {
        boolean updated = false;
        if (pushedYSpeed != 0 || pushedXSpeed != 0) {
            int oldXSpeed = position.xSpeed;
            int oldYSpeed = position.ySpeed;
            position.xSpeed = pushedXSpeed;
            position.ySpeed = pushedYSpeed;
            super.update();
            updated = true;
            position.xSpeed = oldXSpeed;
            position.ySpeed = oldYSpeed;
        }
        if (!rooted && !updated)
            super.update();
        //reset rooted
        rooted = false;
        pushedXSpeed = 0;
        pushedYSpeed = 0;
        state.update(this);
        sprite.update();
    }

    /**
     * Getter
     *
     * @return PushedXSPeed
     */
    public int getPushedXSpeed() {
        return pushedXSpeed;
    }

    /**
     * Setter
     *
     * @param pushedXSpeed PushedXSpeed
     */
    public void setPushedXSpeed(int pushedXSpeed) {
        this.pushedXSpeed = pushedXSpeed;
    }

    /**
     * Getter
     *
     * @return PushedYSpeed
     */
    public int getPushedYSpeed() {
        return pushedYSpeed;
    }

    /**
     * Setter
     *
     * @param pushedYSpeed PushedYSpeed
     */
    public void setPushedYSpeed(int pushedYSpeed) {
        this.pushedYSpeed = pushedYSpeed;
    }

    /**
     * Getter
     *
     * @return Direction
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Setter
     *
     * @param direction Direction
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }

    /**
     * Getter
     *
     * @return State
     */
    public MonsterState getState() {
        return state;
    }

    /**
     * Setter
     *
     * @return Rooted
     */
    public boolean isRooted() {
        return rooted;
    }

    /**
     * Setter
     *
     * @param rooted Rooted
     */
    public void setRooted(boolean rooted) {
        this.rooted = rooted;
    }

    /**
     * Setter
     *
     * @param state State
     */
    public void setState(MonsterState state) {
        this.state = state;
    }
}
