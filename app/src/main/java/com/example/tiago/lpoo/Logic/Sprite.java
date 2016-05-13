package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * A class (works like a C++ struct) that represents a sprite to draw
 */
public class Sprite {

    //Attributes:

    /**
     * The sprite sheet
     */
    private Bitmap spriteSheet;

    /**
     * Region of the sprite sheet to draw (single sprite)
     */
    private Rect src;

    /**
     * Sprite sheet's height
     */
    private int height;

    /**
     * Sprite sheet's width
     */
    private int width;

    /**
     * Number of rows in the sprite sheet
     */
    private int rows;

    /**
     * Number of columns in the sprite sheet
     */
    private int columns;

    /**
     * Height of a single sprite of the sprite sheet
     */
    private int spriteHeight;

    /**
     * Width of a single sprite of the sprite sheet
     */
    private int spriteWidth;

    /**
     * How many frames passed since this Sprite was created
     */
    private int frameCount;

    //Methods:

    /**
     * Constructor
     *
     * @param spriteSheet The sprite sheet
     * @param rows        Number of rows in the sprite sheet
     * @param columns     Number of columns in the sprite sheet
     */
    public Sprite(Bitmap spriteSheet, int rows, int columns) {
        this.spriteSheet = spriteSheet;
        height = spriteSheet.getHeight();
        width = spriteSheet.getWidth();
        this.rows = rows;
        this.columns = columns;
        spriteHeight = height / rows;
        spriteWidth = width / columns;
        //set the source rectangle (1st sprite of the sprite sheet)
        src = new Rect(0, 0, spriteWidth, spriteHeight);
        frameCount = 0;
    }


    public void update() {
        frameCount++;
    }

    public void nextSprite() {
        //if src.bottom yada yada

        //if it's the last sprite of a row
        if (src.right == width - 1) {
            int previousTop = src.top;
            //choose the 1st sprite of the next row
            src.offsetTo(0, previousTop + spriteHeight);
        } else
            src.offset(spriteWidth, 0);

    }
}
