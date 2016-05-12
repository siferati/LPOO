package com.example.tiago.lpoo.Logic;

/**
 * A class (works like a C++ struct) that represents the Coordinates of an Object on the screen (in dps)
 */
public class Position {

    //Attributes:

    /**
     * X Coordinate of an Object's Position on the screen
     */
    public int x;

    /**
     * Y Coordinate of an Object's Position on the screen
     */
    public int y;

    /**
     * Speed along the X axis
     */
    public int xSpeed;

    /**
     * Speed along the Y axis
     */
    public int ySpeed;

    //Methods:

    /**
     * Default Constructor
     */
    public Position() {
        this(0, 0, 0, 0);
    }

    /**
     * Constructor
     */
    public Position(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
}
