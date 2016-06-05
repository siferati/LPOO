package com.example.tiago.lpoo.Logic;

import android.content.Context;

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
        castingDuration = sprite.getNSprites() * sprite.getFps();
        state = new EarthCastingState();
    }

    @Override
    protected void handleCollision(Monster monster) {
        //if monster intersects with earth wall, it can't move
        monster.setRooted(true);
    }
}
