package com.thomas.dungeoncrawler;

public class Dungeon {

    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];

        generateBasicDungeon();
    }

    private void generateBasicDungeon() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                boolean isBorder =
                        x == 0 ||
                                x == width - 1 ||
                                y == 0 ||
                                y == height - 1;

                if (isBorder) {
                    tiles[y][x] = new Tile(TileType.WALL);
                } else {
                    tiles[y][x] = new Tile(TileType.FLOOR);
                }
            }
        }

        // Mur vertical
        if (width > 9 && height > 5) {
            for (int y = 1; y < 5; y++) {
                tiles[y][9] = new Tile(TileType.WALL);
            }
        }

        // Mur horizontal
        if (width > 14 && height > 4) {
            for (int x = 9; x < 15; x++) {
                tiles[4][x] = new Tile(TileType.WALL);
            }
        }

        // Petit mur horizontal
        if (width > 8 && height > 6) {
            for (int x = 4; x < 9; x++) {
                tiles[6][x] = new Tile(TileType.WALL);
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
}