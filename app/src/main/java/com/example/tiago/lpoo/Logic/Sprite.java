package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;

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
     * FPS at which this animation runs
     */
    private int fps;

    /**
     * How many frames each sprite lasts
     */
    private int frameDuration;

    /**
     * How many sprites(frames) this animation has
     */
    private int nSprites;

    /**
     * Index order of the animation
     */
    private int[] sprites;

    /**
     * Index of current Sprite
     */
    private int currentSprite;

    //Methods:

    /**
     * Constructor (init should called after constructor)
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
    }

    /**
     * (Re)set certain sprite attributes
     * @param fps
     */
    public void init(int fps, int[] sprites)
    {
        this.sprites = sprites;
        currentSprite = 0;
        //aux variables
        int x = spriteWidth * (sprites[currentSprite] % columns);
        int y = spriteHeight * (sprites[currentSprite] / columns);
        //set src
        src = new Rect(x, y, x + spriteWidth, y + spriteHeight);
        frameCount = 0;
        this.fps = fps;
        frameDuration = GameLoopActivityLayout.UPS / fps;
        nSprites = sprites.length;
        Log.w("fps", "" + fps);
        Log.w("frameDuration", "" + frameDuration);
    }


    public void update() {
        frameCount++;
        if (frameCount > frameDuration) {
            frameCount = 1;
            nextSprite();
        }
    }

    public void nextSprite() {
        //aux variables
        int x = spriteWidth * (sprites[currentSprite] % columns);
        int y = spriteHeight * (sprites[currentSprite] / columns);
        //set src
        src.offsetTo(x, y);
        currentSprite++;
        if (currentSprite >= nSprites - 1)
            currentSprite = 0;
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

    public int getNSprites() {
        return nSprites;
    }

    public void setNSprites(int nSprites) {
        this.nSprites = nSprites;
    }
}
