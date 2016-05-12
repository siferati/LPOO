package com.example.tiago.lpoo.Logic;

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

    /**
     * Constructor
     *
     * @param prototype The monster that spawns.
     */
    public Spawner(Monster prototype) {
        this.prototype = prototype;
        this.spawned = new ArrayList<>();
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

    /**
     * Spawn a monster
     *
     * @return The spawned monster
     */
    public Monster spawnMonster() {
        Monster m = prototype.cloneMonster();
        spawned.add(m);
        return m;
    }

}
