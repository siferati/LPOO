package com.example.tiago.lpoo.Logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tiago.lpoo.R;

/**
 * A class that represents the Monster running right state
 */
public class MonsterRunningRightState implements MonsterState {

    //Attributes:

    /**
     * Speed of the animation
     */
    private static final int FPS = 8;

    /**
     * Animation frames (ordered by index)
     */
    private static final int[] FRAMES = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

    /**
     * Number of rows of animation spriteSheet
     */
    private static final int ROWS = 8;

    /**
     * Number of columns of animation spriteSheet
     */
    private static final int COLS = 2;

    /**
     * Sprite sheet of Animation
     */
    private static Bitmap spriteSheet;

    //Methods:

    /**
     * Constructor
     *
     * @param monster Monster
     */
    public MonsterRunningRightState(Monster monster) {
        //spriteSheet is only initialized once!
        if (spriteSheet == null)
            spriteSheet = BitmapFactory.decodeResource(monster.context.getResources(), R.drawable.monster);
        monster.sprite = new Sprite(spriteSheet, ROWS, COLS);
        enter(monster);
    }


    @Override
    public void enter(Monster monster) {
        //set correct animation
        monster.sprite.init(FPS, FRAMES);
    }

    @Override
    public MonsterState update(Monster monster) {

        return null;
    }
}
