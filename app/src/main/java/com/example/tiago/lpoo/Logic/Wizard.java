package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.tiago.lpoo.R;

import java.util.ArrayList;

/**
 * A class that represents the Wizard (main char)
 */
public class Wizard extends Entity {

    //Attributes:

    /**
     * ArrayList of active spells at any given moment
     */
    private ArrayList<Spell> spells;

    /**
     * Context
     */
    Context context;

    /**
     * Spell's sprite
     */
    private Bitmap spellsSpriteSheet;

    //Methods:

    /**
     * Default Constructor
     */
    public Wizard() {
        super();
        spells = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param context Context
     * @param dps     TRUE if coordinates are in dps, FALSE if they are in pxls
     * @param x       X coordinate
     * @param y       Y coordinate
     * @param xSpeed  Speed along the X axis
     * @param ySpeed  Speed along the Y axis
     */
    public Wizard(Context context, boolean dps, int x, int y, int xSpeed, int ySpeed) {
        super(context);
        Log.w("Wizard Constructor", "finished super");
        spells = new ArrayList<>();
        //load wizard's sprite sheet
        Bitmap wizardSpriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.teste);
        sprite = new Sprite(wizardSpriteSheet, 1, 3);
        //load spell's sprite sheet
        spellsSpriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.earth_spell);
        //initialize positions
        initPosition(dps, x, y, xSpeed, ySpeed);
    }

    /**
     * Renders both Wizard and its active Spells onto the provided canvas
     *
     * @param canvas Canvas to draw to
     */
    @Override
    public void render(Canvas canvas) {
        //render wizard
        super.render(canvas);
        //render spells
        for (Spell spell : spells)
            spell.render(canvas);
    }

    /**
     * Casts an Earth Spell
     */
    public void castEarthSpell() {
        Sprite earthSpellSprite = new Sprite(spellsSpriteSheet, 1, 1);
        //coordinates given are already in pxls (from wizard constructor), so boolean dps = false
        Spell earthSpell = new EarthSpell(context, false, position.position.left + toPixels(50), position.position.top + toPixels(50), 0, 0, earthSpellSprite);
        spells.add(earthSpell);
    }

    /**
     * Getter
     *
     * @return Active spells
     */
    public ArrayList<Spell> getSpells() {
        return spells;
    }

    /**
     * Setter
     *
     * @param spells Active spells
     */
    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }
}
