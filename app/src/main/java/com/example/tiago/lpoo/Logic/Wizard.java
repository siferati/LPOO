package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * A class that represents the Wizard (main char)
 */
public class Wizard extends Entity {

    //Attributes:

    //position
    //bitmap

    /**
     * ArrayList of active spells at any given moment
     */
    private ArrayList<Spell> spells;

    /**
     * Spell's bitmap
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
     * @param x                 X coordinate
     * @param y                 Y coordinate
     * @param xSpeed            Speed along the X axis
     * @param ySpeed            Speed along the Y axis
     * @param wizardSpriteSheet Sprite Sheet containing the Object's animations
     * @param spellsSpriteSheet Sprite Sheet containing the Spells' animations
     */
    public Wizard(int x, int y, int xSpeed, int ySpeed, Bitmap wizardSpriteSheet, Bitmap spellsSpriteSheet) {
        super(x, y, xSpeed, ySpeed, wizardSpriteSheet);
        spells = new ArrayList<>();
        this.spellsSpriteSheet = spellsSpriteSheet;
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

    public void render2(Canvas canvas) {
        //render wizard
        super.render2(canvas);
        //render spells
        for (Spell spell : spells)
            spell.render(canvas);
    }

    /**
     * Casts an Earth Spell
     */
    public void castEarthSpell() {
        Spell earthSpell = new Spell(position.x + 50, position.y + 50, 0, 0, spellsSpriteSheet);
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

    /**
     * Getter
     *
     * @return Spells' Sprite Sheet
     */
    public Bitmap getSpellsSpriteSheet() {
        return spellsSpriteSheet;
    }

    /**
     * Setter
     *
     * @param spellsSpriteSheet Spells' Sprite Sheet
     */
    public void setSpellsSpriteSheet(Bitmap spellsSpriteSheet) {
        this.spellsSpriteSheet = spellsSpriteSheet;
    }
}
