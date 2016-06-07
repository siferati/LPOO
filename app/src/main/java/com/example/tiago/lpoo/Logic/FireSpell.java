package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;

/**
 * A class that represents an Fire Spell
 */
public class FireSpell extends Spell {

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
    public FireSpell()
    {
        super();
    }

    public FireSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet, char direction) {
        super(context, dps, x, y, xSpeed, ySpeed, direction);
        switch (direction)
        {
            case 'N':
                state = new FireTravellingRightState(this);
                break;
            case 'S':
                state = new FireTravellingRightState(this);
                break;
            case 'E':
                state = new FireTravellingRightState(this);
                break;
            case 'W':
                state = new FireTravellingLeftState(this);
                break;
            default:
                break;
        }
        initPosition(dps, x, y, xSpeed, ySpeed);
        cooldown = (int) COOLDOWN * GameLoopActivityLayout.UPS;
        canCast = false;
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with fire wall, it dies
        monster.setHealth(0);
    }
}
