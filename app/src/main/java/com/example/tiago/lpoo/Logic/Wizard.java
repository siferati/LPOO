package com.example.tiago.lpoo.Logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

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
     * Casts a spell of nature type
     * @param type
     * @param direction
     */
    public void castSpell(char type, char direction)
    {
        switch (type)
        {
            case 'E':
                castEarthSpell(direction);
                break;
            case 'A':
                castAirSpell(direction);
                break;
            case 'F':
                castFireSpell(direction);
                break;
            case 'W':
                castWaterSpell(direction);
                break;
            default:
                break;
        }
    }

    /**
     * Casts an Earth Spell
     */
    private void castEarthSpell(char direction) {
        //coordinates given are already in pxls (from wizard constructor), so boolean dps = false
        Spell earthSpell = null;
        switch (direction) {
            case 'N':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(-100), 0, 0, spellsSpriteSheet, direction);
                break;
            case 'S':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(100), 0, 0, spellsSpriteSheet, direction);
                break;
            case 'E':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(100), position.position.top + toPixels(0), 0, 0, spellsSpriteSheet, direction);
                break;
            case 'W':
                earthSpell = new EarthSpell(context, false, position.position.left + toPixels(-100), position.position.top + toPixels(0), 0, 0, spellsSpriteSheet, direction);
                break;
            default:
                break;
        }
        if (earthSpell != null)
            spells.add(earthSpell);
    }

    /**
     * Casts an Air Spell
     */
    private void castAirSpell(char direction) {
        //coordinates given are already in pxls (from wizard constructor), so boolean dps = false
        Spell airSpell = null;
        switch (direction) {
            case 'N':
                airSpell = new AirSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), 0, toPixels(-4), spellsSpriteSheet, direction);
                break;
            case 'S':
                airSpell = new AirSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), 0, toPixels(4), spellsSpriteSheet, direction);
                break;
            case 'E':
                airSpell = new AirSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), toPixels(4), 0, spellsSpriteSheet, direction);
                break;
            case 'W':
                airSpell = new AirSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), toPixels(-4), 0, spellsSpriteSheet, direction);
                break;
            default:
                break;
        }
        if (airSpell != null)
            spells.add(airSpell);
    }

    /**
     * Casts an Water Spell
     */
    private void castWaterSpell(char direction) {
        //coordinates given are already in pxls (from wizard constructor), so boolean dps = false
        Spell waterSpell = null;
        switch (direction) {
            case 'N':
                waterSpell = new WaterSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), 0, toPixels(-5), spellsSpriteSheet, direction);
                break;
            case 'S':
                waterSpell = new WaterSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), 0, toPixels(5), spellsSpriteSheet, direction);
                break;
            case 'E':
                waterSpell = new WaterSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), toPixels(5), 0, spellsSpriteSheet, direction);
                break;
            case 'W':
                waterSpell = new WaterSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), toPixels(-5), 0, spellsSpriteSheet, direction);
                break;
            default:
                break;
        }
        if (waterSpell != null)
            spells.add(waterSpell);
    }

    /**
     * Casts an Fire Spell
     */
    private void castFireSpell(char direction) {
        //coordinates given are already in pxls (from wizard constructor), so boolean dps = false
        Spell fireSpell = null;
        switch (direction) {
            case 'N':
                fireSpell = new FireSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), 0, toPixels(-4), spellsSpriteSheet, direction);
                break;
            case 'S':
                fireSpell = new FireSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), 0, toPixels(4), spellsSpriteSheet, direction);
                break;
            case 'E':
                fireSpell = new FireSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), toPixels(4), 0, spellsSpriteSheet, direction);
                break;
            case 'W':
                fireSpell = new FireSpell(context, false, position.position.left + toPixels(0), position.position.top + toPixels(0), toPixels(-4), 0, spellsSpriteSheet, direction);
                break;
            default:
                break;
        }
        if (fireSpell != null)
            spells.add(fireSpell);
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
