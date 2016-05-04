package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;

/**
 * A class that represents the Wizard (main char)
 */
public class Wizard extends Character{

    //Attributes:

    /**
     * Spell
     */
    private Spell spell;

    //Methods:

    /**
     * Default Constructor
     */
    public Wizard()
    {
        super();
        spell = null;
    }

    /**
     * Constructor
     */
    public Wizard(int x, int y, Bitmap bitmap, Spell spell)
    {
        super(x, y, bitmap);
        this.spell = spell;
    }
}
