package com.example.tiago.lpoo.Logic;

import android.graphics.Canvas;

import java.util.ArrayList;

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
     * Constructor
     */
    public Spawner(Monster prototype){
        this.prototype = prototype;
        this.spawned = new ArrayList<Monster>();
    }

    /*
     * Getter
     *
     * @return the monter that spawns.
     */
    public Monster getPrototype(){ return prototype; }

    /*
     * Getter
     *
     * @return the spawned monsters.
     */
    public ArrayList<Monster> getSpawned(){ return spawned; }

    /*
     * Setter
     * @param the spawned monsters.
     */
    public void setSpawned( ArrayList<Monster> spawned) { this.spawned = spawned; }

    /*
     * Setter
     *
     * @param the new monster
     */
    public void setPrototype(Monster prototype){
        this.prototype = prototype;
    }

    /*
     * Method - Spawn a monster
     */
    public Monster spawnMonster(){
        Monster m = prototype.clone();
        spawned.add(m);
        return m;
    }

}
