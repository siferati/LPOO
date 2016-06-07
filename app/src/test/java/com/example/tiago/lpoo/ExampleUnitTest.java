package com.example.tiago.lpoo;

import android.graphics.Rect;

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
    public void MonsterDiesTest() throws Exception {
        Monster m = new Monster();
        m.setHealth(1);

        m.hit(0);
        assertEquals(1, m.getHealth());
        m.hit(1);
        assertEquals(0, m.getHealth());

        assertTrue(m.checkDead());
    }

    @Test
    public void SpawnerSpawnsTest() throws Exception {
        Monster m = new Monster();
        Spawner sp = new Spawner(m, 10, 10);

        Monster sm = sp.spawnMonster();

        assertNotNull(sm);
    }

    @Test
    public void MonsterSpawnedWithinRadiusTest() throws Exception {
        Monster m = new Monster();
        m.setPosition(new Position(new Rect (0,0,0,0), 0, 0));
        Spawner sp = new Spawner(m, 10, 10);

        Monster sm = sp.spawnMonster();

        assertTrue( java.lang.Math.abs(sm.getPosition().position.left - m.getPosition().position.left) <= 1 );
        assertTrue( java.lang.Math.abs(sm.getPosition().position.top - m.getPosition().position.top) <= 1 );
        assertTrue( java.lang.Math.abs(sm.getPosition().position.right - m.getPosition().position.right) <= 1 );
        assertTrue( java.lang.Math.abs(sm.getPosition().position.bottom - m.getPosition().position.bottom) <= 1 );

    }

    @Test
    public void MonsterCorpseTest() throws Exception{
        Monster m = new Monster();
        m.setPosition(new Position(new Rect (0,0,0,0), 0, 0));
        m.setCorpseDur(1);
        m.setHealth(0);
        m.decrementCorpseDur();
        assertTrue(m.checkDoneCorpse());
    }

    @Test
    public void SpawnerRateTest() throws Exception {
        Monster m = new Monster();
        Spawner s = new Spawner(m, 10, 1);

        assertEquals(1, s.getSpawnRate());
        assertEquals(0, s.getSpawnCounter());

        int i = s.update();

        assertEquals(1, s.getSpawnRate());
        assertEquals(1, s.getSpawnCounter());
        assertEquals(1, i);
    }



}