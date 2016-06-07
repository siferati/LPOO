package com.example.tiago.lpoo.Layouts;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.tiago.lpoo.Activities.GameLoopActivity;
import com.example.tiago.lpoo.Logic.Spawner;
import com.example.tiago.lpoo.Logic.Spell;
import com.example.tiago.lpoo.Logic.Wizard;
import com.example.tiago.lpoo.Logic.Monster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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

    /*
     * Current Wave
     */
    int wave;

    /**
     * Total monsters to spawn in this wave
     */
    int monstersToSpawn;

    /**
     * Monsters Spawned already in this wave
     */
    int spawnedCounter;

    /**
     * Check if it's a new wave of monsters
     */
    boolean newWave;

    /**
     * Wait time between waves
     */
    int waveTimeCounter;

    /*
     * Crititcal Area Radius
     */
    int criticalAreaRadius;

    /**
     * Max monsters allowed on the critical area
     */
    int criticalMonsters;

    /**
     * True if the player lost the game
     */
    boolean lost;

    /**
     * Queue of inputs to process
     */
    ArrayList<MotionEvent> motionEvents;

    /**
     * Updates Per Second (if the game ran at 30 FPS, it would be updated once every frame)
     */
    public static final int UPS = 30;

    /**
     * How often (in milliseconds) an update is made
     */
    private final int MS_PER_UPDATE = 1000 / UPS;

    /**
     * Maximum number of updates one frame is allowed to process. MINIMUM FPS = (UPS / MAX_UPDATES_PER_FRAME) = 5
     * If FPS drops below MINIMUM FPS (= 5), the actual game will slow down
     */
    final int MAX_UPDATES_PER_FRAME = 6;

    /**
     * Aux variable for processEvent
     */
    private float startX;

    /**
     * Aux variable for processEvent
     */
    private float startY;

    /**
     * Aux variable for processEvent
     */
    private int SLIDE_RANGE;

    /**
     * Aux variable for processEvent
     */
    private char direction;

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
        criticalAreaRadius = 65;
        criticalMonsters = 5;
        lost = false;
        //initialize wizard
        wizard = new Wizard(context, false, context.getResources().getDisplayMetrics().widthPixels / 2, context.getResources().getDisplayMetrics().heightPixels / 2, 0, 0);
        spawners = new ArrayList<Spawner>();
        createCardialSpawners();
        surfaceHolder = getHolder();
        motionEvents = new ArrayList<>();
        SLIDE_RANGE = toPixels(25);
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
        //initialize previous frame time
        long previous = SystemClock.uptimeMillis();
        //lag measures how far the game’s clock is behind, compared to the real world
        long lag = 0;
        while (running) {
            if (lost) {
                String scoreMsg = readScoreFile();
                int highScore = Integer.parseInt(scoreMsg.substring(12));
                if (score > highScore) writeToFile("highscore.txt", "High Score: " + score + "\n");
            }
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
                else {
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
            //how much time to sleep, in order cap the game at 30 FPS (not capping it would be wasting battery)
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
            //get event
            MotionEvent event = motionEvents.get(0);
            float x = (int) event.getRawX();
            float y = (int) event.getRawY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getRawX();
                    startY = event.getRawY();
                    direction = '\0';
                    Log.w("StartPointX", "" + startX);
                    Log.w("StartPointY", "" + startY);
                    Log.w("X", "" + x);
                    Log.w("Y", "" + y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.w("StartPointX", "" + startX);
                    Log.w("StartPointY", "" + startY);
                    Log.w("X", "" + x);
                    Log.w("Y", "" + y);
                    if (startX - x > SLIDE_RANGE) {
                        direction = 'W';
                    } else if (x - startX > SLIDE_RANGE) {
                        direction = 'E';
                    } else if (startY - y > SLIDE_RANGE) {
                        direction = 'N';
                    } else if (y - startY > SLIDE_RANGE) {
                        direction = 'S';
                    } else
                        direction = '\0';
                    break;
                case MotionEvent.ACTION_UP:
                    wizard.castEarthSpell(direction);
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
    private void update() {
        ArrayList<Monster> inCriticalArea = monstersInCriticalAreaList();
        if (criticalMonsters <= 0) {
            lost = true;
            return;
        }
        Random r = new Random();
        wizard.update();
        for (Spawner s : spawners) {
            score += s.removeDead();
            for (Monster m : s.getSpawned()) {
                m.hit(1);
                m.setSpeedsToWizard(wizard.getPosition());
            }
            s.updateHealth();
        }
        //check collisions
        checkCollisions();
        criticalMonsters -= loseLives(inCriticalArea, monstersInCriticalAreaList());
        if (newWave && waveTimeCounter < 5000) {
        } else {
            newWave = false;
            waveTimeCounter = 0;
            if (spawnedCounter < monstersToSpawn) {
                int index = r.nextInt(spawners.size());
                int spawned = 0;
                spawners.get(index).incrementCounter();
                if (spawners.get(index).getSpawnCounter() == spawners.get(index).getSpawnRate()) {
                    spawners.get(index).spawnMonster();
                    spawners.get(index).setSpawnCounter(0);
                    spawnedCounter++;
                }
            } else {
                wave++;
                spawnedCounter = 0;
                newWave = true;
                monstersToSpawn = toSpawn();
            }
        }
    }

    /**
     * Checks for collisions
     */
    private void checkCollisions() {
        //Compare every monster to every spell. It's not pretty, but gets the job done!
        for (Spell spell : wizard.getSpells()) {
            for (Spawner spawner : spawners) {
                for (Monster monster : spawner.getSpawned()) {
                    spell.checkCollision(monster);
                }
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
        if (!surfaceHolder.getSurface().isValid()) {
            return;
        }

        //lock the canvas
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (Spawner s : spawners) {
            for (Monster m : s.getSpawned()) {
                m.render(canvas);
            }
        }
        wizard.render(canvas);
        drawCriticalArea();
        Paint p = new Paint();
        int textSize = 30;
        p.setTextSize(toPixels(textSize));
        Typeface font = Typeface.createFromAsset(context.getAssets(), "TubeOfCorn.ttf");
        p.setTypeface(font);
        p.setColor(Color.LTGRAY);
        String scoreText = "Score: " + score;
        canvas.drawText(scoreText, (float) 0.2 * toPixels(scoreText.length() * textSize), toPixels(50), p);
        canvas.drawText("" + criticalMonsters, (float) (context.getResources().getDisplayMetrics().widthPixels / 2), toPixels(50), p);
        String waveText = "Wave: " + (wave - 1);
        canvas.drawText(waveText, (float) (context.getResources().getDisplayMetrics().widthPixels - 0.8 * toPixels(waveText.length() * textSize)), toPixels(50), p);
        String lostText = "YOU LOST!";
        if (lost)
            canvas.drawText(lostText, (float) (context.getResources().getDisplayMetrics().widthPixels / 2 - 0.5 * lostText.length() * textSize), (float) (context.getResources().getDisplayMetrics().heightPixels / 2), p);
        //unlock and post the canvas
        surfaceHolder.unlockCanvasAndPost(canvas);
        if (lost)
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((Activity) context).finish();
        }
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

    public void createRandomSpawners(int spawnerNumber) {
        int x, y;
        Random rand = new Random();
        for (int i = 0; i < spawnerNumber; i++) {
            x = rand.nextInt(500);
            y = rand.nextInt(200);
            Monster m = new Monster(context, true, x, y, 0, 0, 20, 'E');
            m.setSpeedsToWizard(this.wizard.getPosition()); // TODO
            spawners.add(new Spawner(m, 200, rand.nextInt(50)));
        }
    }

    public void createCardialSpawners() {
        int x, y;
        Random rand = new Random();

        // North
        Monster m = new Monster(context, true, 350, 0, 0, 0, 100, 'S');
        m.setSpeedsToWizard(this.wizard.getPosition());
        spawners.add(new Spawner(m, 200, rand.nextInt(50) + 20));

        // South
        Monster s = new Monster(context, true, 350, 400, 0, 0, 100, 'N');
        s.setSpeedsToWizard(this.wizard.getPosition());
        spawners.add(new Spawner(s, 200, rand.nextInt(50) + 20));

        // East
        m = new Monster(context, true, 700, 200, 0, 0, 100, 'W');
        m.setSpeedsToWizard(this.wizard.getPosition());
        spawners.add(new Spawner(m, 200, rand.nextInt(50) + 20));

        // West
        m = new Monster(context, true, 0, 200, 0, 0, 100, 'E');
        m.setSpeedsToWizard(this.wizard.getPosition());
        spawners.add(new Spawner(m, 200, rand.nextInt(50) + 20));

    }

    public int toSpawn() {
        if (wave < 5) {
            return wave;
        } else return wave + score % 4;
    }

    public int toPixels(float dps) {
        return (int) (dps * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public float toDps(int pixels) {
        return (float) ((pixels - 0.5f) / context.getResources().getDisplayMetrics().density);
    }

    public void drawCriticalArea() {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(toPixels(2));
        p.setColor(Color.RED);
        canvas.drawCircle(wizard.getPosition().position.centerX(), wizard.getPosition().position.centerY(), toPixels(criticalAreaRadius), p);
    }

    public ArrayList<Monster> monstersInCriticalAreaList() {
        ArrayList<Monster> retorno = new ArrayList<Monster>();
        for (Spawner s : spawners) {
            for (Monster m : s.getSpawned()) {
                if (m.getPosition().position.centerX() <= (wizard.getPosition().position.centerX() + toPixels(criticalAreaRadius)) && m.getPosition().position.centerX() >= (wizard.getPosition().position.centerX() - toPixels(criticalAreaRadius)))
                    if (m.getPosition().position.centerY() <= (wizard.getPosition().position.centerY() + toPixels(criticalAreaRadius)) && m.getPosition().position.centerY() >= (wizard.getPosition().position.centerY() - toPixels(criticalAreaRadius)))
                        retorno.add(m);
            }
        }
        return retorno;
    }

    public int loseLives(ArrayList<Monster> last, ArrayList<Monster> now) {
        int retorno = 0;
        for (Monster m : now) {
            boolean existent = false;
            for (Monster mm : last) {
                if (mm.equals(m)) {
                    existent = true;
                }
            }
            if (!existent) retorno++;
        }
        return retorno;
    }

    public void writeToFile(String filename, String message) {
        File path = context.getFilesDir();
        File file = new File(path, filename);
        try {
            FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
            outputStream.write(message.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readScoreFile() {
        FileInputStream instream;
        String scoreMessage = "";
        File path = context.getFilesDir();
        try {
            instream = new FileInputStream(path + "/highscore.txt");
            // prepare the file for reading
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            scoreMessage = buffreader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreMessage;
    }

}