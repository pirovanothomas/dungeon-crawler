package com.thomas.dungeoncrawler;

public class CombatSystem {

    public int calculateDamage(int attack, int defense) {
        return Math.max(1, attack - defense);
    }

    public int attack(Player player, Enemy enemy) {

        if (!player.isAlive() || !enemy.isAlive()) {
            return 0;
        }

        int damage = calculateDamage(
                player.getAttack(),
                enemy.getDefense()
        );

        enemy.takeDamage(damage);

        return damage;
    }

    public int attack(Enemy enemy, Player player) {

        if (!enemy.isAlive() || !player.isAlive()) {
            return 0;
        }

        int damage = calculateDamage(
                enemy.getAttack(),
                player.getDefense()
        );

        player.takeDamage(damage);

        return damage;
    }

    public void fightTurn(Player player, Enemy enemy) {

        if (!player.isAlive() || !enemy.isAlive()) {
            return;
        }

        attack(player, enemy);

        if (enemy.isAlive()) {
            attack(enemy, player);
        }
    }
}