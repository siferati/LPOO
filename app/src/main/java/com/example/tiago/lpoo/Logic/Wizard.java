package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.tiago.lpoo.R;

import java.util.ArrayList;
import java.util.Iterator;

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
        spells = new ArrayList<>();
        //load wizard's sprite sheet
        Bitmap wizardSpriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard);
        sprite = new Sprite(wizardSpriteSheet, 1, 1);
        sprite.init(30, new int[]{0});
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
    public void castEarthSpell(char direction) {
        //coordinates given are already in pxls (from wizard constructor), so boolean dps = false
        Spell earthSpell = null;
        switch (direction) {
            case 'N':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(-100), 0, 0, spellsSpriteSheet);
                break;
            case 'S':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(100), 0, 0, spellsSpriteSheet);
                break;
            case 'E':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(100), position.position.top + toPixels(0), 0, 0, spellsSpriteSheet);
                break;
            case 'W':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(-100), position.position.top + toPixels(0), 0, 0, spellsSpriteSheet);
                break;
            default:
                break;
        }
        if (earthSpell != null)
            spells.add(earthSpell);
    }

    @Override
    public void update() {
        //update wizard
        super.update();
        //get it for spells
        Iterator<Spell> it = spells.iterator();
        //update spells
        while (it.hasNext()) {
            Spell spell = it.next();
            spell.update();
            //if spell is dead, remove it
            if (!spell.isActive())
                it.remove();
        }
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
