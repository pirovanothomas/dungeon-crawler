package com.thomas.dungeoncrawler;

public class CombatResult {

    private final Enemy enemy;
    private final int playerDamage;
    private final int enemyDamage;
    private final boolean enemyDefeated;
    private final boolean playerDefeated;

    public CombatResult(
            Enemy enemy,
            int playerDamage,
            int enemyDamage,
            boolean enemyDefeated,
            boolean playerDefeated
    ) {
        this.enemy = enemy;
        this.playerDamage = playerDamage;
        this.enemyDamage = enemyDamage;
        this.enemyDefeated = enemyDefeated;
        this.playerDefeated = playerDefeated;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public boolean isEnemyDefeated() {
        return enemyDefeated;
    }

    public boolean isPlayerDefeated() {
        return playerDefeated;
    }
}