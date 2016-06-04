package com.example.tiago.lpoo.Logic;

import java.util.ArrayList;

/**
 * A class that represents the Earth spell's casting state
 */
public class EarthCastingState implements SpellState {

    //Attributes:

    /**
     * How many frames this state lasts
     */
    private final int STATE_DURATION = 30;

    /**
     * How many frames passed since state started
     */
    private int frameCount;

    //-------------------------------------------------------------

    /**
     * How long this state lasts (in seconds)
     */
    private static final float DURATION;

    /**
     * Speed of the animation
     */
    private static final int FPS;

    /**
     * Order of the frames (index) of the animation
     */
    private static final int[] FRAMES;


    static {
        //esta inicializaçao e so para tirar o erro a dizer q as variaveis n foram inicializadas. apaga isto depois
        DURATION = 2;
        FPS = 2;
        FRAMES = new int[] {0,1,2,3,4};
        //TODO Faz aqui a inicializaçao das 3 variaveis static: DURATION, FPS e FRAMES
    }



    //--------------------------------------------------------------


    //Methods:

    /**
     * Constructor
     */
    public EarthCastingState() {
        frameCount = 0;
    }


    @Override
    public void enter(Spell spell) {
        //set correct animation
        spell.getSprite().init(10, 0, 9);
    }

    @Override
    public SpellState update(Spell spell) {
        //update frameCount
        frameCount++;
        //if the entire animation as been played
        if (frameCount > STATE_DURATION)
            return new EarthActiveState();
        return null;
    }
}
