package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {

    @Test
    void playerShouldNotMoveThroughWall() {

        GameEngine engine = new GameEngine();

        // Le joueur commence en (3, 3).
        // On monte en (3, 2), où aucun couloir horizontal
        // ne permet de traverser le mur entre les salles.
        engine.movePlayer(0, -1);

        // On tente ensuite de traverser le mur.
        for (int i = 0; i < 20; i++) {
            engine.movePlayer(1, 0);
        }

        // La première salle s'arrête en x = 7.
        assertEquals(7, engine.getPlayer().getX());
        assertEquals(2, engine.getPlayer().getY());
    }
}