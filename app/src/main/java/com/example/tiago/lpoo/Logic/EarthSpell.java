package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * A class that represents an Earth Spell
 */
public class EarthSpell extends Spell{

    //Attributes:

    //Methods:

    /**
     * Default Constructor
     */
    public EarthSpell()
    {
        super();
    }

    public EarthSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet, char direction) {
        super(context, dps, x, y, xSpeed, ySpeed, direction);
        switch (direction)
        {
            case 'N':
                state = new EarthCastingHorizontalState(this);
                break;
            case 'S':
                state = new EarthCastingHorizontalState(this);
                break;
            case 'E':
                state = new EarthCastingVerticalState(this);
                break;
            case 'W':
                state = new EarthCastingVerticalState(this);
                break;
            default:
                break;
        }
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with earth wall, it can't move
        monster.setRooted(true);
    }
}
