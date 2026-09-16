package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DungeonTest {

    @Test
    void dungeonShouldHaveCorrectDimensions() {
        Dungeon dungeon = new Dungeon(10, 5);

        assertEquals(10, dungeon.getWidth());
        assertEquals(5, dungeon.getHeight());
    }

    @Test
    void borderShouldBeWall() {
        Dungeon dungeon = new Dungeon(10, 5);

        assertEquals(
                TileType.WALL,
                dungeon.getTile(0, 0).getType()
        );

        assertEquals(
                TileType.WALL,
                dungeon.getTile(9, 4).getType()
        );
    }

    @Test
    void insideShouldBeFloor() {
        Dungeon dungeon = new Dungeon(10, 5);

        assertEquals(
                TileType.FLOOR,
                dungeon.getTile(5, 2).getType()
        );
    }

    @Test
    void positionShouldBeInsideDungeon() {
        Dungeon dungeon = new Dungeon(10, 5);

        assertTrue(dungeon.isInside(5, 2));
        assertFalse(dungeon.isInside(-1, 2));
        assertFalse(dungeon.isInside(10, 2));
    }

    @Test
    void interiorWallShouldNotBeWalkable() {
        Dungeon dungeon = new Dungeon(20, 8);

        assertFalse(dungeon.getTile(9, 2).isWalkable());
    }
}