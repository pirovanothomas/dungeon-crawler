package com.thomas.dungeoncrawler;

public class Dungeon {

    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];

        initializeWalls();

        DungeonGenerator generator = new DungeonGenerator();
        generator.generate(this);
    }

    private void initializeWalls() {

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                tiles[y][x] = new Tile(TileType.WALL);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInside(int x, int y) {
        return x >= 0 &&
                x < width &&
                y >= 0 &&
                y < height;
    }

    public void carveRoom(Room room) {

        for (int y = room.getY(); y < room.getY() + room.getHeight(); y++) {

            for (int x = room.getX(); x < room.getX() + room.getWidth(); x++) {

                if (isInside(x, y)) {
                    tiles[y][x] = new Tile(TileType.FLOOR);
                }
            }
        }
    }

    public void carveHorizontalCorridor(int x1, int x2, int y) {

        int start = Math.min(x1, x2);
        int end = Math.max(x1, x2);

        for (int x = start; x <= end; x++) {

            if (isInside(x, y)) {
                tiles[y][x] = new Tile(TileType.FLOOR);
            }
        }
    }

    public void carveVerticalCorridor(int y1, int y2, int x) {

        int start = Math.min(y1, y2);
        int end = Math.max(y1, y2);

        for (int y = start; y <= end; y++) {

            if (isInside(x, y)) {
                tiles[y][x] = new Tile(TileType.FLOOR);
            }
        }
    }
}