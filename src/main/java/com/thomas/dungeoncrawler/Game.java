package com.thomas.dungeoncrawler;

public class Game {

    public static void main(String[] args) {

        Dungeon dungeon = new Dungeon(20, 8);
        Player player = new Player(1, 1);

        for (int y = 0; y < dungeon.getHeight(); y++) {

            for (int x = 0; x < dungeon.getWidth(); x++) {

                if (player.getX() == x && player.getY() == y) {
                    System.out.print("@");
                    continue;
                }

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

    private static void movePlayer(Player player, Dungeon dungeon, int deltaX, int deltaY) {

        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        if (!dungeon.isInside(newX, newY)) {
            return;
        }

        Tile destination = dungeon.getTile(newX, newY);

        if (!destination.isWalkable()) {
            return;
        }

        player.move(deltaX, deltaY);
    }
}