package com.thomas.dungeoncrawler;

public class Enemy {

    private final String name;

    private int x;
    private int y;

    private final int maxHealth;
    private int health;

    private final int attack;
    private final int defense;

    public Enemy(String name, int x, int y, int maxHealth, int attack, int defense) {
        this.name = name;
        this.x = x;
        this.y = y;

        this.maxHealth = maxHealth;
        this.health = maxHealth;

        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }
}