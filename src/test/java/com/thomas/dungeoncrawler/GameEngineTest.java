package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void gameEngineShouldCreateEnemies() {

        GameEngine engine = new GameEngine();

        assertFalse(engine.getEnemies().isEmpty());
    }

    @Test
    void playerShouldStartOnWalkableTile() {

        GameEngine engine = new GameEngine();

        Player player = engine.getPlayer();
        Dungeon dungeon = engine.getDungeon();

        assertTrue(
                dungeon.getTile(
                        player.getX(),
                        player.getY()
                ).isWalkable()
        );
    }

    @Test
    void playerShouldBeAbleToAttackEnemy() {

        GameEngine engine = new GameEngine();

        Enemy enemy = engine.getEnemies().get(0);

        int initialHealth = enemy.getHealth();

        int damage = engine.attackEnemy(enemy);

        assertTrue(damage > 0);
        assertEquals(
                initialHealth - damage,
                enemy.getHealth()
        );
    }

    @Test
    void movingTowardsEnemyShouldTriggerCombat() {

        Dungeon dungeon = new Dungeon(10, 10);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                dungeon.setTile(x, y, TileType.FLOOR);
            }
        }

        Player player = new Player(3, 5);

        Enemy enemy = new Enemy(
                "Gobelin",
                4,
                5,
                30,
                8,
                2
        );

        GameEngine engine = new GameEngine(
                dungeon,
                player,
                List.of(enemy)
        );

        engine.movePlayer(1, 0);

        assertEquals(3, player.getX());
        assertEquals(5, player.getY());

        assertEquals(22, enemy.getHealth());
    }

    @Test
    void playerShouldBeAbleToMoveToDeadEnemyPosition() {

        Dungeon dungeon = new Dungeon(10, 10);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                dungeon.setTile(x, y, TileType.FLOOR);
            }
        }

        Player player = new Player(3, 5);

        Enemy enemy = new Enemy(
                "Gobelin",
                4,
                5,
                30,
                8,
                2
        );

        enemy.takeDamage(30);

        GameEngine engine = new GameEngine(
                dungeon,
                player,
                List.of(enemy)
        );

        engine.movePlayer(1, 0);

        assertEquals(4, player.getX());
        assertEquals(5, player.getY());
    }

    @Test
    void movingTowardsEnemyShouldTriggerCompleteCombatTurn() {

        Dungeon dungeon = new Dungeon(10, 10);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                dungeon.setTile(x, y, TileType.FLOOR);
            }
        }

        Player player = new Player(
                3,
                5,
                100,
                10,
                5
        );

        Enemy enemy = new Enemy(
                "Gobelin",
                4,
                5,
                30,
                8,
                2
        );

        GameEngine engine = new GameEngine(
                dungeon,
                player,
                List.of(enemy)
        );

        engine.movePlayer(1, 0);

        assertEquals(3, player.getX());
        assertEquals(5, player.getY());

        assertEquals(22, enemy.getHealth());
        assertEquals(97, player.getHealth());
    }
}