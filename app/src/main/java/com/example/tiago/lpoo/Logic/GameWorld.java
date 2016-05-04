package com.example.tiago.lpoo.Logic;

import android.os.SystemClock;

import java.util.ArrayList;

/**
 * A class that represents the Game World (contains the game loop)
 */
public class GameWorld {

    //Attributes:

    /**
     * A collection of all the game chars (wizard, monsters and so on...)
     */
    private ArrayList<Character> characters;

    /**
     * TRUE - game is running | FALSE - game is NOT running
     */
    private boolean running;

    //---------------------------------------------------------------------

    //Methods:

    /**
     * Default Constructor
     */
    public GameWorld()
    {
        characters = new ArrayList<>();
        running = true;
    }

    /**
     * Game Loop
     */
    public void gameLopp()
    {
        //Updates Per Second (if the game ran at 30 FPS, it would be updated once every frame)
        final int UPS = 30;

        //how often (in milliseconds) an update is made
        final int MS_PER_UPDATE = 1000 / UPS;

        //initialize previous frame time
        long previous =  SystemClock.uptimeMillis();
        //lag measures how far the game’s clock is behind, compared to the real world
        long lag = 0;
        while (running)
        {
            //get current time
            long current =  SystemClock.uptimeMillis();
            //get elapsed time since last frame
            long elapsed = current - previous;
            //set previous frame time for next iteration
            previous = current;
            //set lag to reflect the elapsed time since the last frame in the real world
            lag += elapsed;

            //processInput();

            //while game's clock is behind the real world
            while (lag >= MS_PER_UPDATE)
            {
                //update();

                //set lag for next iteration
                lag -= MS_PER_UPDATE;
            }

            //interpolation - lag is divided by MS_PER_UPDATE in order to normalize the value
            //render(lag / MS_PER_UPDATE);
        }
    }

}
