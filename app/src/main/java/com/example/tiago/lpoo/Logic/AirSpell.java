package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * A class that represents an Earth Spell
 */
public class AirSpell extends Spell {

    //Attributes:

    //Methods:

    /**
     * Default Constructor
     */
    public AirSpell()
    {
        super();
    }

    public AirSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet, char direction) {
        super(context, dps, x, y, xSpeed, ySpeed, direction);
        state = new AirActiveState(this);
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with tornado, kill it
        monster.setHealth(0);
    }
}
