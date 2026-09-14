package com.thomas.dungeoncrawler;

public class Tile {

    private final TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public boolean isWalkable() {
        return type == TileType.FLOOR;
    }
}