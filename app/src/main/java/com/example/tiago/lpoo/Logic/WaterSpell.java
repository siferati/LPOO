package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * A class that represents an Water Spell
 */
public class WaterSpell extends Spell{

    //Attributes:

    //Methods:

    /**
     * Default Constructor
     */
    public WaterSpell()
    {
        super();
    }

    public WaterSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet, char direction) {
        super(context, dps, x, y, xSpeed, ySpeed, direction);
        switch (direction)
        {
            case 'N':
                state = new WaterActiveRightState(this);
                break;
            case 'S':
                state = new WaterActiveRightState(this);
                break;
            case 'E':
                state = new WaterActiveRightState(this);
                break;
            case 'W':
                state = new WaterActiveLeftState(this);
                break;
            default:
                break;
        }
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with water wall, it gets pushed back
        monster.setPushedYSpeed(toPixels(position.ySpeed));
        monster.setPushedXSpeed(toPixels(position.xSpeed));
    }
}
