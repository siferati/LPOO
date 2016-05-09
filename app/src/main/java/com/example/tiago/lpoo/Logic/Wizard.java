package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tiago.lpoo.R;

/**
 * A class that represents the Wizard (main char)
 */
public class Wizard extends Character {

    //Attributes:

    /**
     * Spell
     */
    private Spell spell;

    /**
     * Spell's bitmap
     */
    private Bitmap bitmap_spell;

    //Methods:

    /**
     * Default Constructor
     */
    public Wizard() {
        super();
        spell = null;
    }

    /**
     * Constructor
     */
    public Wizard(int x, int y, Bitmap bitmap, Bitmap bitmap_spell) {
        super(x, y, bitmap);
        this.bitmap_spell = bitmap_spell;
        spell = null;
    }

    /**
     * Casts an Earth Spell
     */
    public void castEarthSpell() {
        spell = new Spell(position.x + 50, position.y + 50, bitmap_spell);
    }

    /**
     * Getter
     *
     * @return
     */
    public Spell getSpell() {
        return spell;
    }

    /**
     * Setter
     *
     * @param spell
     */
    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    /**
     * Getter
     *
     * @return
     */
    public Bitmap getBitmap_spell() {
        return bitmap_spell;
    }

    /**
     * Setter
     *
     * @param bitmap_spell
     */
    public void setBitmap_spell(Bitmap bitmap_spell) {
        this.bitmap_spell = bitmap_spell;
    }
}
