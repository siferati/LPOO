package com.example.tiago.lpoo.Layouts;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.tiago.lpoo.Logic.Wizard;
import com.example.tiago.lpoo.R;

/**
 * A class that represents the View where the game is running (game loop is located here)
 */
public class GameLoopActivityLayout extends SurfaceView implements Runnable {

    //Attributes:

    /**
     * The separate thread where the game will run
     */
    Thread thread;

    /**
     * TRUE - the game is running | FALSE - the game is NOT running
     */
    boolean running;

    /**
     * Canvas where the rendering is happening
     */
    Canvas canvas;

    /**
     * Holder for the Surface where the rendering is happening
     */
    SurfaceHolder surfaceHolder;

    /**
     * Wizard (player's char)
     */
    Wizard wizard;

    //Methods:

    /**
     * Constructor
     *
     * @param context Context
     */
    public GameLoopActivityLayout(Context context) {
        super(context);
        thread = null;
        running = false;
        //load wizard's bitmap
        Bitmap wizardAnimation = BitmapFactory.decodeResource(getResources(), R.drawable.wizard_animation);
        wizard = new Wizard(50, 50, wizardAnimation, null);
        surfaceHolder = getHolder();
    }

    /**
     * Runs the game loop (thread.run())
     */
    @Override
    public void run() {
        //Updates Per Second (if the game ran at 30 FPS, it would be updated once every frame)
        final int UPS = 30;
        //how often (in milliseconds) an update is made
        final int MS_PER_UPDATE = 1000 / UPS;
        //initialize previous frame time
        long previous = SystemClock.uptimeMillis();
        //maximum number of updates one frame is allowed to process. MINIMUM FPS = (UPS / MAX_UPDATES_PER_FRAME) = 5
        //if FPS drops below MINIMUM FPS (= 5), the actual game will slow down
        final int MAX_UPDATES_PER_FRAME = 6;
        //lag measures how far the game’s clock is behind, compared to the real world
        long lag = 0;
        while (running) {
            //get current time
            long current = SystemClock.uptimeMillis();
            //get elapsed time since last frame
            long elapsed = current - previous;
            //set previous frame time for next iteration
            previous = current;
            //set lag to reflect the elapsed time since the last frame in the real world
            lag += elapsed;

            //processInput();

            //i measures the number of updates made for each frame
            int i = 0;
            //while game's clock is behind the real world
            while (lag >= MS_PER_UPDATE && i < MAX_UPDATES_PER_FRAME) {
                //update all game objects
                update();
                //set lag for next iteration
                lag -= MS_PER_UPDATE;
                //set i for next iteration
                i++;
            }
            //interpolation - lag is divided by MS_PER_UPDATE in order to normalize the value
            render(lag / MS_PER_UPDATE);
            //how much time to sleep, in order cap the game at 30 FPS
            long sleep = current + MS_PER_UPDATE - SystemClock.uptimeMillis();
            if (sleep > 0)
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }

    }

    /**
     * Updates all game objects
     */
    private void update() {

    }

    /**
     * Renders all game objects
     *
     * @param interpolation How far into the next frame we are (in percentage)
     */
    private void render(float interpolation) {
        //if the surface is NOT valid, exit rendering
        if (!surfaceHolder.getSurface().isValid())
            return;
        //lock the canvas
        canvas = surfaceHolder.lockCanvas();
        //draw all game objects to canvas
        canvas.drawBitmap(wizard.getBitmap(), wizard.getPosition().x, wizard.getPosition().y, null);
        //unlock and post the canvas
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    /**
     * Pause the game
     */
    public void pause() {
        //game is NOT running
        running = false;
        boolean control = true;
        while (control) {
            try {
                //stop the thread
                thread.join();
                control = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }

    /**
     * Resume the game
     */
    public void resume() {
        //game is running
        running = true;
        //creates a thread for the game to run
        thread = new Thread(this);
        //start the thread
        thread.start();
    }
}
