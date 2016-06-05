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

    public EarthSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet) {
        super(context, dps, x, y, xSpeed, ySpeed);
        state = new EarthCastingState(this, spriteSheet);
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with earth wall, it can't move
        monster.setRooted(true);
    }
}
