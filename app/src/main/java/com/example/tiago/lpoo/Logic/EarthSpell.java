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
        castingDuration = 0;
    }

    public EarthSpell(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed, Sprite sprite) {
        super(context, dps, x, y, xSpeed, ySpeed, sprite);
        castingDuration = sprite.getNSprites() * sprite.getFrameDuration();
        state = new CastingState(castingDuration);
    }

}
