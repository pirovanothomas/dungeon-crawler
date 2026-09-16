package com.thomas.dungeoncrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonGenerator {

    private final Random random;
    private final List<Room> rooms;

    public DungeonGenerator() {
        this.random = new Random();
        this.rooms = new ArrayList<>();
    }

    public DungeonGenerator(long seed) {
        this.random = new Random(seed);
        this.rooms = new ArrayList<>();
    }

    public void generate(Dungeon dungeon) {

        int roomCount = 5;

        for (int i = 0; i < roomCount; i++) {

            Room room = generateRoom(dungeon);

            if (room != null) {
                rooms.add(room);
                dungeon.carveRoom(room);
            }
        }

        connectRooms(dungeon);
    }

    private Room generateRoom(Dungeon dungeon) {

        for (int attempt = 0; attempt < 50; attempt++) {

            int width = random.nextInt(4) + 4;
            int height = random.nextInt(3) + 3;

            int maxX = dungeon.getWidth() - width - 1;
            int maxY = dungeon.getHeight() - height - 1;

            if (maxX <= 0 || maxY <= 0) {
                return null;
            }

            int x = random.nextInt(maxX) + 1;
            int y = random.nextInt(maxY) + 1;

            Room candidate = new Room(x, y, width, height);

            if (!overlapsExistingRooms(candidate)) {
                return candidate;
            }
        }

        return null;
    }

    public List<Room> getRooms() {
        return List.copyOf(rooms);
    }

    private boolean overlapsExistingRooms(Room candidate) {

        for (Room room : rooms) {

            if (candidate.intersects(room)) {
                return true;
            }
        }

        return false;
    }

    private void connectRooms(Dungeon dungeon) {

        for (int i = 0; i < rooms.size() - 1; i++) {

            Room currentRoom = rooms.get(i);
            Room nextRoom = rooms.get(i + 1);

            int startX = currentRoom.getCenterX();
            int startY = currentRoom.getCenterY();

            int endX = nextRoom.getCenterX();
            int endY = nextRoom.getCenterY();

            dungeon.carveHorizontalCorridor(startX, endX, startY);
            dungeon.carveVerticalCorridor(startY, endY, endX);
        }
    }
}