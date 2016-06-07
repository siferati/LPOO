package com.example.tiago.lpoo;

import android.graphics.Rect;

import com.example.tiago.lpoo.Layouts.GameLoopActivityLayout;
import com.example.tiago.lpoo.Logic.Monster;
import com.example.tiago.lpoo.Logic.Position;
import com.example.tiago.lpoo.Logic.Spawner;
import com.example.tiago.lpoo.Logic.Sprite;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    
    @Test
    public void MonsterDies() throws Exception {
        Monster m = new Monster();
        m.setHealth(1);

        m.hit(0);
        assertEquals(1, m.getHealth());
        m.hit(1);
        assertEquals(0, m.getHealth());

        assertTrue(m.checkDead());
    }

    @Test
    public void SpawnerSpawns() throws Exception {
        Monster m = new Monster();
        Spawner sp = new Spawner(m, 10, 10);

        Monster sm = sp.spawnMonster();

        assertNotNull(sm);
    }



}