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

    /**
     * How many frames each sprite lasts
     */
    private int frameDuration;

    /**
     * First sprite of the animation requested
     */
    private Rect first;

    /**
     * Last sprite of the animation requested
     */
    private Rect last;

    /**
     * How many sprites(frames) this animation has
     */
    private int nSprites;

    //Methods:

    /**
     * Constructor
     *
     * @param spriteSheet The sprite sheet
     * @param rows        Number of rows in the sprite sheet
     * @param columns     Number of columns in the sprite sheet
     * @param first       Index of first sprite of spritesheet (first sprite has index 0!)
     * @param last        Index of last sprite of spritesheet
     */
    public Sprite(Bitmap spriteSheet, int frameDuration, int rows, int columns, int first, int last) {
        this.spriteSheet = spriteSheet;
        height = spriteSheet.getHeight();
        width = spriteSheet.getWidth();
        this.rows = rows;
        this.columns = columns;
        spriteHeight = height / rows;
        spriteWidth = width / columns;
        init(frameDuration, first, last);
    }

    /**
     * (Re)set certain sprite attributes
     * @param frameDuration
     * @param first
     * @param last
     */
    public void init(int frameDuration, int first, int last)
    {
        //aux variables
        int firstX = spriteWidth * (first % columns);
        int firstY = spriteHeight * (first / columns);
        int lastX = spriteWidth * (last % columns);
        int lastY = spriteHeight * (last / columns);
        //set first sprite
        this.first = new Rect(firstX, firstY, firstX + spriteWidth, firstY + spriteHeight);
        //set last sprite
        this.last = new Rect(lastX, lastY, lastX + spriteWidth, lastY + spriteHeight);
        //set the source rectangle (1st sprite of the animation requested)
        src = new Rect(this.first);
        frameCount = 0;
        this.frameDuration = frameDuration;
        nSprites = last - first + 1;
    }


    public void update() {
        frameCount++;
        if (frameCount > frameDuration) {
            frameCount = 1;
            nextSprite();
        }
    }

    public void nextSprite() {
        //if it's the last sprite
        if (src.right >= last.right && src.bottom >= last.bottom)
            src.set(first);
        else {
            //if it's the last sprite of a row
            if (src.right >= width - 1) {
                if (src.bottom >= height - 1)
                    src.set(first); //if it's the last sprite of the sprite sheet, move to start again
                else {
                    int previousTop = src.top;
                    //choose the 1st sprite of the next row
                    src.offsetTo(0, previousTop + spriteHeight);
                }
            } else
                src.offset(spriteWidth, 0); //move to next sprite (on its right)
        }
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

    public Rect getFirst() {
        return first;
    }

    public void setFirst(Rect first) {
        this.first = first;
    }

    public int getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(int frameDuration) {
        this.frameDuration = frameDuration;
    }

    public Rect getLast() {
        return last;
    }

    public void setLast(Rect last) {
        this.last = last;
    }

    public int getNSprites() {
        return nSprites;
    }

    public void setNSprites(int nSprites) {
        this.nSprites = nSprites;
    }
}
