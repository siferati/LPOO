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
    //TODO receber inicio e fim da selecçao da spritesheet no constructor
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
        if (frameCount > 30)
        {
            frameCount = 1;
            nextSprite();
        }
    }

    public void nextSprite() {
        //if it's the last sprite of a row
        if (src.right >= width - 1) {
            if (src.bottom >= height - 1)
                src.offsetTo(0, 0); //if it's the last sprite of the sprite sheet, move to start again
            else {
                int previousTop = src.top;
                //choose the 1st sprite of the next row
                src.offsetTo(0, previousTop + spriteHeight);
            }
        } else
            src.offset(spriteWidth, 0); //move to next sprite (on its right)
    }

    public void render(Canvas canvas, Rect dest) {
        canvas.drawBitmap(spriteSheet, src, dest, null);
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(Bitmap spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public Rect getSrc() {
        return src;
    }

    public void setSrc(Rect src) {
        this.src = src;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
