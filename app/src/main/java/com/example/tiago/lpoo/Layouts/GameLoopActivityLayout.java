package com.example.tiago.lpoo.Layouts;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.tiago.lpoo.Logic.Entity;
import com.example.tiago.lpoo.Logic.Position;
import com.example.tiago.lpoo.Logic.Spawner;
import com.example.tiago.lpoo.Logic.Wizard;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents the Custom View where the game is running (game loop is located here)
 */
public class GameLoopActivityLayout extends SurfaceView implements Runnable {

    //Attributes:

    /**
     * Context of this view
     */
    Context context;

    /**
     * The separate thread where the game will run
     */
    Thread thread = null;

    /**
     * TRUE - the game is running | FALSE - the game is NOT running
     */
    boolean running = false;

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

    /**
     * Spawner for a monster
     */
    ArrayList<Spawner> spawners;

    /**
     * Total score
     */
    int score;

    int wave;
    int monstersToSpawn;
    int spawnedCounter;
    boolean newWave;
    int waveTimeCounter;

    /**
     * Queue of inputs to process
     */
    ArrayList<MotionEvent> motionEvents;

    //Methods:

    /**
     * Constructor
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    public GameLoopActivityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        thread = null;
        running = false;
        score = 0;
        wave = 1;
        newWave = true;
        monstersToSpawn = toSpawn();
        spawnedCounter = 0;
        waveTimeCounter = 0;
        //initialize wizard
        wizard = new Wizard(context, true, 100, 50, 0, 0);
        spawners = new ArrayList<Spawner>();
        createRandomSpawners(3);
        surfaceHolder = getHolder();
        motionEvents = new ArrayList<>();
    }

    /**
     * Adds a Motion Event to the queue so it can be processed
     *
     * @param event Motion Event to be added to the queue
     */
    public void addMotionEvent(MotionEvent event) {
        motionEvents.add(event);
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
            //process motion events
            processEvents();
            //i measures the number of updates made for each frame
            int i = 0;
            //while game's clock is behind the real world
            while (lag >= MS_PER_UPDATE && i < MAX_UPDATES_PER_FRAME) {
                if (newWave) waveTimeCounter += elapsed;
                else{
                    newWave = false;
                    waveTimeCounter = 0;
                }
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
     * Process motion events
     */
    private void processEvents() {
        while (!motionEvents.isEmpty()) {
            //get action
            int action = motionEvents.get(0).getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    //cast an earth spell
                    wizard.castEarthSpell();
                    break;
                default:
                    break;
            }
            //remove processed event from queue
            motionEvents.remove(0);
        }
    }

    /**
     * Updates all game objects
     */
    private void update(){
        Random r = new Random();
        wizard.update();
        for (Spawner s: spawners) {
            for (Monster m : s.getSpawned()) {
                m.hit(1);
            }
            s.updateHealth();
            score += s.removeDead();
        }
        if (newWave && waveTimeCounter < 5000) {}
        else{
            newWave = false;
            waveTimeCounter = 0;
            if (spawnedCounter < monstersToSpawn) {
                int index = r.nextInt(spawners.size());
                while (spawners.get(index).update() != 1) {}
                spawnedCounter++;
            }
            else{
                wave++;
                spawnedCounter = 0;
                newWave = true;
                monstersToSpawn = toSpawn();
            }
        }
    }

    /**
     * Renders all game objects
     *
     * @param interpolation How far into the next frame we are (in percentage)
     */
    private void render(float interpolation) {
        //if the surface is NOT valid, exit rendering
        if (!surfaceHolder.getSurface().isValid()){
            return;
        }

        //lock the canvas
        canvas = surfaceHolder.lockCanvas();
        //draw white canvas
        canvas.drawColor(Color.WHITE);
        //draw all game objects to canvas

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (Spawner s: spawners){
            for (Monster m: s.getSpawned()){
                m.render(canvas);
            }
        }
        wizard.render(canvas);
        Paint p = new Paint();
        p.setTextSize(100);
        p.setColor(Color.LTGRAY);
        canvas.drawText("Score: " + score, 100, 100, p);
        canvas.drawText("Wave: " + (wave - 1), 1350, 100, p);

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

    public void createRandomSpawners(int spawnerNumber){
        int x, y;
        Random rand = new Random();
        for (int i = 0; i < spawnerNumber; i++) {
            x = rand.nextInt(1000);
            y = rand.nextInt(800);
            Monster m = new Monster(x, y, 0, 0, wizard.getSpriteSheet(), 20);
            m.setSpeedsToWizard(this.wizard.getPosition()); // TODO
            spawners.add(new Spawner(m, 200, rand.nextInt(100) + 50));
        }
    }

    public int toSpawn(){
        if (wave < 5){
            return wave;
        }
        else return wave + score % 4;
    }

}
