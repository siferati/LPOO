package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

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
        if (spriteSheet != null) height = spriteSheet.getHeight();
        else height = 0;
        if (spriteSheet != null) width = spriteSheet.getWidth();
        else width = 0;
        this.rows = rows;
        this.columns = columns;
        spriteHeight = height / rows;
        spriteWidth = width / columns;
    }

    /**
     * (Re)set certain sprite attributes
     *
     * @param fps     FPS
     * @param sprites Indexes of Sprites
     */
    public void init(int fps, int[] sprites) {
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
    }

    /**
     * Update this Sprite
     */
    public void update() {
        frameCount++;
        if (frameCount > frameDuration) {
            frameCount = 1;
            nextSprite();
        }
    }

    /**
     * Move onto next sprite
     */
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

    /**
     * render sprite
     *
     * @param canvas Canvas
     * @param dest   Destination rectangle
     */
    public void render(Canvas canvas, Rect dest) {
        canvas.drawBitmap(spriteSheet, src, dest, null);
    }

    /**
     * Getter
     *
     * @return Columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Setter
     *
     * @param columns Columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Getter
     *
     * @return How many frames passed
     */
    public int getFrameCount() {
        return frameCount;
    }

    /**
     * Setter
     *
     * @param frameCount How many frames passed
     */
    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    /**
     * Getter
     *
     * @return Height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter
     *
     * @param height Height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter
     *
     * @return Rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Setter
     *
     * @param rows Rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Getter
     *
     * @return Sprite's Height
     */
    public int getSpriteHeight() {
        return spriteHeight;
    }

    /**
     * Setter
     *
     * @param spriteHeight Sprite's Height
     */
    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    /**
     * Getter
     *
     * @return Sprite Sheet
     */
    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    /**
     * Setter
     *
     * @param spriteSheet Sprite Sheet
     */
    public void setSpriteSheet(Bitmap spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    /**
     * Getter
     *
     * @return Sprite's Width
     */
    public int getSpriteWidth() {
        return spriteWidth;
    }

    /**
     * Setter
     *
     * @param spriteWidth Sprite's Width
     */
    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    /**
     * Getter
     *
     * @return Source rectangle
     */
    public Rect getSrc() {
        return src;
    }

    /**
     * Setter
     *
     * @param src Source Rectangle
     */
    public void setSrc(Rect src) {
        this.src = src;
    }

    /**
     * Getter
     *
     * @return Width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter
     *
     * @param width Width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter
     *
     * @return Number of Sprites
     */
    public int getNSprites() {
        return nSprites;
    }

    /**
     * Setter
     *
     * @param nSprites Number of Sprites
     */
    public void setNSprites(int nSprites) {
        this.nSprites = nSprites;
    }
}
