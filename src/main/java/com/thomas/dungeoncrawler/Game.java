package com.thomas.dungeoncrawler;

public class Game {

    public static void main(String[] args) {

        Dungeon dungeon = new Dungeon(20, 8);

        for (int y = 0; y < dungeon.getHeight(); y++) {

            for (int x = 0; x < dungeon.getWidth(); x++) {

                Tile tile = dungeon.getTile(x, y);

                if (tile.getType() == TileType.WALL) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }

            System.out.println();
        }
    }
}