package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {

    @Test
    void playerShouldNotMoveThroughWall() {

        Dungeon dungeon = new Dungeon(10, 10);

        // On crée une zone entièrement accessible.
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                dungeon.setTile(x, y, TileType.FLOOR);
            }
        }

        // On place un mur devant le joueur.
        dungeon.setTile(4, 5, TileType.WALL);

        Player player = new Player(3, 5);

        GameEngine engine = new GameEngine(dungeon, player);

        engine.movePlayer(1, 0);

        assertEquals(3, engine.getPlayer().getX());
        assertEquals(5, engine.getPlayer().getY());
    }
}