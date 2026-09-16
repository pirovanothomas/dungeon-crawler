package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void roomShouldHaveCorrectDimensionsAndPosition() {

        Room room = new Room(2, 3, 6, 4);

        assertEquals(2, room.getX());
        assertEquals(3, room.getY());
        assertEquals(6, room.getWidth());
        assertEquals(4, room.getHeight());
    }

    @Test
    void roomsShouldDetectIntersection() {

        Room firstRoom = new Room(2, 2, 6, 4);
        Room secondRoom = new Room(5, 3, 6, 4);

        assertTrue(firstRoom.intersects(secondRoom));
    }

    @Test
    void roomsShouldNotIntersect() {

        Room firstRoom = new Room(2, 2, 4, 4);
        Room secondRoom = new Room(10, 2, 4, 4);

        assertFalse(firstRoom.intersects(secondRoom));
    }

    @Test
    void roomShouldHaveCorrectCenter() {

        Room room = new Room(2, 2, 6, 4);

        assertEquals(5, room.getCenterX());
        assertEquals(4, room.getCenterY());
    }
}