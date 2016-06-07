package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;

/**
 * A class that represents an Earth Spell
 */
public class AirSpell extends Spell {

    //Attributes:

    /**
     * Cooldown in seconds!
     */
    private static final float COOLDOWN = (float) 3.0;

    /**
     * Cooldown in frames!!
     */
    public static int cooldown = 0;

    /**
     * TRUE if off cooldow, FALSE otherwise
     */
    public static boolean canCast = true;

    //Methods:

    /**
     * Default Constructor
     */
    public AirSpell() {
        super();
    }

    public AirSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet, char direction) {
        super(context, dps, x, y, xSpeed, ySpeed, direction);
        state = new AirActiveState(this);
        initPosition(dps, x, y, xSpeed, ySpeed);
        cooldown = (int) COOLDOWN * GameLoopActivityLayout.UPS;
        canCast = false;
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with tornado, kill it
        monster.setHealth(0);
    }
}
