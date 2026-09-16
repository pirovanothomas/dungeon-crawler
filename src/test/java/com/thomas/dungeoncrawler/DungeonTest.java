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

    @Test
    void roomShouldBeCarvedIntoDungeon() {

        Dungeon dungeon = new Dungeon(20, 10);
        Room room = new Room(2, 2, 5, 4);

        dungeon.carveRoom(room);

        assertTrue(dungeon.getTile(2, 2).isWalkable());
        assertTrue(dungeon.getTile(4, 3).isWalkable());
    }

//    @Test
//    void tilesOutsideRoomShouldRemainUnchanged() {
//
//        Dungeon dungeon = new Dungeon(20, 10);
//        Room room = new Room(2, 2, 5, 4);
//
//        dungeon.carveRoom(room);
//
//        assertFalse(dungeon.getTile(10, 5).getType() == TileType.WALL);
//    }

    @Test
    void horizontalCorridorShouldBeWalkable() {

        Dungeon dungeon = new Dungeon(20, 10);

        dungeon.carveHorizontalCorridor(3, 8, 5);

        assertTrue(dungeon.getTile(3, 5).isWalkable());
        assertTrue(dungeon.getTile(5, 5).isWalkable());
        assertTrue(dungeon.getTile(8, 5).isWalkable());
    }

    @Test
    void verticalCorridorShouldBeWalkable() {

        Dungeon dungeon = new Dungeon(20, 10);

        dungeon.carveVerticalCorridor(3, 8, 5);

        assertTrue(dungeon.getTile(5, 3).isWalkable());
        assertTrue(dungeon.getTile(5, 5).isWalkable());
        assertTrue(dungeon.getTile(5, 8).isWalkable());
    }
}