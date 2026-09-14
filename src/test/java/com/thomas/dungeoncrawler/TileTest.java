package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void floorShouldBeWalkable() {
        Tile tile = new Tile(TileType.FLOOR);

        assertTrue(tile.isWalkable());
    }

    @Test
    void wallShouldNotBeWalkable() {
        Tile tile = new Tile(TileType.WALL);

        assertFalse(tile.isWalkable());
    }
}