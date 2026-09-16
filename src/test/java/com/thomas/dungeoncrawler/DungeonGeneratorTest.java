package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DungeonGeneratorTest {

    @Test
    void generatorShouldCreateRooms() {

        DungeonGenerator generator = new DungeonGenerator(12345);
        Dungeon dungeon = new Dungeon(40, 20, generator);

        List<Room> rooms = generator.getRooms();

        assertFalse(rooms.isEmpty());
    }

    @Test
    void generatedRoomsShouldNotOverlap() {

        DungeonGenerator generator = new DungeonGenerator(12345);
        Dungeon dungeon = new Dungeon(40, 20, generator);

        List<Room> rooms = generator.getRooms();

        for (int i = 0; i < rooms.size(); i++) {

            for (int j = i + 1; j < rooms.size(); j++) {

                assertFalse(
                        rooms.get(i).intersects(rooms.get(j))
                );
            }
        }
    }

//    @Test
//    void generatedRoomsShouldBeConnected() {
//
//        DungeonGenerator generator = new DungeonGenerator(12345);
//        Dungeon dungeon = new Dungeon(40, 20, generator);
//
//        List<Room> rooms = generator.getRooms();
//
//        assertTrue(rooms.size() >= 2);
//
//        for (int i = 0; i < rooms.size() - 1; i++) {
//
//            Room currentRoom = rooms.get(i);
//            Room nextRoom = rooms.get(i + 1);
//
//            int x = currentRoom.getCenterX();
//            int y = currentRoom.getCenterY();
//
//            int targetX = nextRoom.getCenterX();
//            int targetY = nextRoom.getCenterY();
//
//            assertTrue(dungeon.getTile(y == targetY ? targetX : x, y).isWalkable());
//        }
//    }

    @Test
    void generatedRoomsShouldBeConnected() {

        DungeonGenerator generator = new DungeonGenerator(12345);
        Dungeon dungeon = new Dungeon(40, 20, generator);

        List<Room> rooms = generator.getRooms();

        assertTrue(rooms.size() >= 2);

        for (int i = 0; i < rooms.size() - 1; i++) {

            Room currentRoom = rooms.get(i);
            Room nextRoom = rooms.get(i + 1);

            int startX = currentRoom.getCenterX();
            int startY = currentRoom.getCenterY();

            int endX = nextRoom.getCenterX();
            int endY = nextRoom.getCenterY();

            int minX = Math.min(startX, endX);
            int maxX = Math.max(startX, endX);

            for (int x = minX; x <= maxX; x++) {
                assertTrue(dungeon.getTile(x, startY).isWalkable());
            }

            int minY = Math.min(startY, endY);
            int maxY = Math.max(startY, endY);

            for (int y = minY; y <= maxY; y++) {
                assertTrue(dungeon.getTile(endX, y).isWalkable());
            }
        }
    }
}