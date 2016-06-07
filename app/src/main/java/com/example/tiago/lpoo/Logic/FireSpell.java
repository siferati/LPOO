package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * A class that represents an Fire Spell
 */
public class FireSpell extends Spell {

    //Attributes:

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
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with fire wall, it dies
        monster.setHealth(0);
    }
}
