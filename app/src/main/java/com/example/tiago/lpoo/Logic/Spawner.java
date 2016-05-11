package com.example.tiago.lpoo.Logic;

import android.graphics.Canvas;

public class Spawner {

    // Attributes

    /*
     * The monster prototype to copy (spawnling)
     */
    private Monster prototype;

    /*
     * Constructor
     */
    public Spawner(Monster prototype){
        this.prototype = prototype;
    }

    /*
     * Getter
     *
     * @return the monter that spawns.
     */
    public Monster getPrototype(){ return prototype; }

    /*
     * Method - Spawn a monster
     */
    public Monster spawnMonster(){
        return prototype.clone();
    }

}
