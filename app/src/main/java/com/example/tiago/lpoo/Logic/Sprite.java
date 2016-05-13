package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * A class (works like a C++ struct) that represents a sprite to draw
 */
public class Sprite {

    //Attributes:

    /**
     * The sprite sheet
     */
    public Bitmap spriteSheet;

    /**
     * Region of the sprite sheet to draw (single sprite)
     */
    public Rect rect;

    //Methods:

    /**
     * Constructor
     *
     * @param spriteSheet Sprite Sheet
     * @param rect        Region of the sprite sheet to draw (single sprite)
     */
    public Sprite(Bitmap spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }
}
