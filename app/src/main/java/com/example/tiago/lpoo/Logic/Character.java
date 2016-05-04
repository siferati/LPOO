package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;

/**
 * A class that represents a Character (monster, wizard, etc)
 */
public abstract class Character {

    //Attributes:

    /**
     * Coordinates of the Character on the screen (in dps)
     */
    protected Position position;

    /**
     * Sprite Animation
     */
    protected AnimationDrawable animation;

    /**
     * Bitmap
     */
    protected Bitmap bitmap;

    //Methods:

    /**
     * Constructor
     */
    public Character(int x, int y, Bitmap bitmap) {
        position = new Position(x, y);
        this.bitmap = bitmap;
    }

    /**
     * Default Constructor
     */
    public Character() {
        this(0, 0, null);
    }

    /**
     * Converter from DPs to PXs
     */
    /*private int toPixels(float dps)
    {
        return (int) (dps * getResources())
    }*/

    /**
     * Getter
     *
     * @return
     */
    public AnimationDrawable getAnimation() {
        return animation;
    }

    /**
     * Setter
     *
     * @param animation
     */
    public void setAnimation(AnimationDrawable animation) {
        this.animation = animation;
    }

    /**
     * Getter
     *
     * @return
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Setter
     *
     * @param bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Getter
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Setter
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
