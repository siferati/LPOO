package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * A class that represents a Entity (monster, wizard, etc)
 */
public abstract class Entity {

    //Attributes:

    /**
     * Coordinates of the Entity on the screen and axis speed (in dps)
     */
    protected Position position;

    /**
     * Sprite Sheet containing the Object's animations
     */
    protected Bitmap spriteSheet;

    //Methods:

    /**
     * Constructor
     *
     * @param x           X coordinate
     * @param y           Y coordinate
     * @param xSpeed      Speed along the X axis
     * @param ySpeed      Speed along the Y axis
     * @param spriteSheet Sprite Sheet containing the Object's animations
     */
    public Entity(int x, int y, int xSpeed, int ySpeed, Bitmap spriteSheet) {
        position = new Position(x, y, xSpeed, ySpeed);
        this.spriteSheet = spriteSheet;
    }

    /**
     * Default Constructor
     */
    public Entity() {
        this(0, 0, 0, 0, null);
    }

    /**
     * Converter from DPs to PXs
     */
    /*private int toPixels(float dps)
    {
        return (int) (dps * getResources())
    }*/

    /**
     * Render this object onto the screen
     *
     * @param canvas Canvas to draw to
     */
    public void render(Canvas canvas) {
        canvas.drawBitmap(spriteSheet, position.x, position.y, null);
    }

    /**
     * Updates current X and Y positions according to its speed
     */
    public void update() {
        position.x += position.xSpeed;
        position.y += position.ySpeed;
    }

    /**
     * Getter
     *
     * @return Bitmap
     */
    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    /**
     * Setter
     *
     * @param spriteSheet Bitmap
     */
    public void setSpriteSheet(Bitmap spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    /**
     * Getter
     *
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Setter
     *
     * @param position Position
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
