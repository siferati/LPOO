package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

/**
 * A class that represents an Earth Spell
 */
public class EarthSpell extends Spell{

    //Attributes:

    //position
    //spriteSheet
    //state
    //castingDuration

    //Methods:

    /**
     * Default Constructor
     */
    public EarthSpell()
    {
        super();
        castingDuration = 30;
    }

    public EarthSpell(int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet) {
        super(x, y, xSpeed, ySpeed, spriteSheet);
        castingDuration = 30;
        state = new CastingState(castingDuration);
    }

}
