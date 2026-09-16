package com.thomas.dungeoncrawler;

public class GameEngine {

    private final Dungeon dungeon;
    private final Player player;

    public GameEngine() {
        this.dungeon = new Dungeon(20, 8);
        this.player = new Player(3, 3);
    }

    public void movePlayer(int deltaX, int deltaY) {

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

    public Player getPlayer() {
        return player;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }
}