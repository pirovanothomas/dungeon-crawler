package com.thomas.dungeoncrawler;

public class Room {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean intersects(Room other) {

        return x < other.getX() + other.getWidth()
                && x + width > other.getX()
                && y < other.getY() + other.getHeight()
                && y + height > other.getY();
    }

    public int getCenterX() {
        return x + width / 2;
    }

    public int getCenterY() {
        return y + height / 2;
    }
}