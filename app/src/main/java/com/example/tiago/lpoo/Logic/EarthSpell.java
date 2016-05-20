package com.example.tiago.lpoo.Logic;

import android.content.Context;
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

    public EarthSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Sprite sprite) {
        super(context, dps, x, y, xSpeed, ySpeed, sprite);
        castingDuration = 30;
        state = new CastingState(castingDuration);
    }

}
