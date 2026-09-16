package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTest {

    @Test
    void roomShouldHaveCorrectDimensionsAndPosition() {

        Room room = new Room(2, 3, 6, 4);

        assertEquals(2, room.getX());
        assertEquals(3, room.getY());
        assertEquals(6, room.getWidth());
        assertEquals(4, room.getHeight());
    }
}