package com.example.tiago.lpoo.Logic;


import java.util.ArrayList;
import java.util.Random;

public class Spawner {

    // Attributes

    /*
     * The monster prototype to copy (spawnling)
     */
    private Monster prototype;

    /*
     * Holds the spawned monsters
     */
    private ArrayList<Monster> spawned;

    /*
     * Spawn radius
     */
    private int spawnRadius;

    /**
     * Fixed spawn rate
     */
    private int spawnRate;

    /**
     * Counter to rate
     */
    private int spawnCounter;

    private boolean canSpawn;

    /**
     * Constructor
     *
     * @param prototype The monster that spawns.
     */
    public Spawner(Monster prototype, int spawnRadius, int spawnRate) {
        this.prototype = prototype;
        this.spawnRadius = spawnRadius;
        this.spawnRate = spawnRate;
        this.spawnCounter = 0;
        this.spawned = new ArrayList<>();
        this.canSpawn = false;
    }

    /**
     * Getter
     *
     * @return The monster that spawns.
     */
    public Monster getPrototype() {
        return prototype;
    }

    /**
     * Getter
     *
     * @return The spawned monsters
     */
    public ArrayList<Monster> getSpawned() {
        return spawned;
    }

    /**
     * Getter
     *
     * @return The Spawn Radius
     */
    public int getSpawnRadius(){return  spawnRadius;}

    /**
     * Getter
     *
     * @return the spawn rate
     */
    public int getSpawnRate(){return spawnRate;}

    /**
     * Getter
     *
     * @return Current spawn counter
     */
    public int getSpawnCounter(){return spawnCounter;}

    public boolean getCanSpawn() {return canSpawn;}

    /**
     * Setter
     *
     * @param spawnRadius new radius
     */
    public void setSpawnRadius(int spawnRadius){ this.spawnRadius = spawnRadius;}

    /**
     * Setter
     *
     * @param spawnRate new rate
     */
    public void setSpawnRate(int spawnRate) {this.spawnRate = spawnRate; }

    /**
     * Setter
     *
     * @param spawnCounter new counter
     */
    public void setSpawnCounter(int spawnCounter) {this.spawnCounter = spawnCounter; }

    /**
     * Setter
     *
     * @param spawned The spawned monsters.
     */
    public void setSpawned(ArrayList<Monster> spawned) {
        this.spawned = spawned;
    }

    /**
     * Setter
     *
     * @param prototype The new monster
     */
    public void setPrototype(Monster prototype) {
        this.prototype = prototype;
    }

    public void setCanSpawn(boolean didSpawn) { this.canSpawn = didSpawn; }

    public void incrementCounter(){
        this.spawnCounter ++;
        if (this.spawnCounter > this.spawnRate) this.spawnCounter = 0;
    }

    /**
     * Spawn a monster in the spawnRadius radius
     *
     * @return The spawned monster
     */
    public Monster spawnMonster() {
        Random r = new Random();
        int newx = 0;
        int newy = 0;
        if (this.spawnRadius == 0){
            newx = 0;
            newy = 0;
        }
        else {
            newx = r.nextInt(this.spawnRadius);
            newy = r.nextInt(this.spawnRadius);
        }
        Monster m = prototype.cloneMonster(this.prototype.getPosition().position.left + newx, this.prototype.getPosition().position.top + newy);
        spawned.add(m);
        return m;
    }

    /**
     * Update Spawner
     */
    public int update(){
        this.incrementCounter();
        if (this.spawnCounter == this.spawnRate) {
            this.canSpawn = true;
            this.spawnMonster();
            return 1;
        }
        return 0;
    }

    public void updateHealth(){
        for (Monster m : spawned){
            if (m.getHealth() != 0) m.update();
        }
    }

    /**
     * Remove any dead monsters
     */
    public int removeDead(){
        int retorno = 0;
        for (int i = 0; i < spawned.size(); i++){
            if (spawned.get(i).checkDead()) {
                if (spawned.get(i).checkDoneCorpse()) {
                    retorno++;
                    spawned.remove(i);
                } else spawned.get(i).decrementCorpseDur();
            }
        }
        return retorno;
    }
}
