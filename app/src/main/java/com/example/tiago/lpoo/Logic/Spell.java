package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

/**
 * A class that represents a Spell
 */
public class Spell extends Entity {

    //Attributes:

    //position
    //spriteSheet

    //Methods:

    /**
     * Default Constructor
     */
    public Spell()
    {
        super();
    }

    /**
     *
     * @param x
     * @param y
     * @param xSpeed
     * @param ySpeed
     * @param bitmap
     */
    public Spell(int x, int y, int xSpeed, int ySpeed, Bitmap bitmap)
    {
        super(x, y, xSpeed, ySpeed, bitmap);
    }
}
