package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {

    @Test
    void playerShouldNotMoveThroughWall() {

        GameEngine engine = new GameEngine();

        // Le joueur commence en (1, 1)
        // Le mur vertical est en x = 9

        for (int i = 0; i < 20; i++) {
            engine.movePlayer(1, 0);
        }

        assertEquals(8, engine.getPlayer().getX());
        assertEquals(1, engine.getPlayer().getY());
    }
}