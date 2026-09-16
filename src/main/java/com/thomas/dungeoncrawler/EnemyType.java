package com.thomas.dungeoncrawler;

public enum EnemyType {

    GOBLIN("Gobelin", 'G', 30, 8, 2),
    ORC("Orc", 'O', 50, 12, 4),
    TROLL("Troll", 'T', 80, 15, 7);

    private final String name;
    private final char symbol;
    private final int maxHealth;
    private final int attack;
    private final int defense;

    EnemyType(
            String name,
            char symbol,
            int maxHealth,
            int attack,
            int defense
    ) {
        this.name = name;
        this.symbol = symbol;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}