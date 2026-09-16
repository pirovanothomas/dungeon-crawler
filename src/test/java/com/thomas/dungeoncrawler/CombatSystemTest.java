package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombatSystemTest {

    @Test
    void damageShouldBeAttackMinusDefense() {

        CombatSystem combatSystem = new CombatSystem();

        int damage = combatSystem.calculateDamage(10, 3);

        assertEquals(7, damage);
    }

    @Test
    void damageShouldNeverBeLowerThanOne() {

        CombatSystem combatSystem = new CombatSystem();

        int damage = combatSystem.calculateDamage(5, 10);

        assertEquals(1, damage);
    }

    @Test
    void playerShouldDamageEnemy() {

        Player player = new Player(3, 3, 100, 10, 5);

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        CombatSystem combatSystem = new CombatSystem();

        int damage = combatSystem.attack(player, enemy);

        assertEquals(8, damage);
        assertEquals(22, enemy.getHealth());
    }

    @Test
    void enemyShouldDamagePlayer() {

        Player player = new Player(3, 3, 100, 10, 5);

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        CombatSystem combatSystem = new CombatSystem();

        int damage = combatSystem.attack(enemy, player);

        assertEquals(3, damage);
        assertEquals(97, player.getHealth());
    }

    @Test
    void enemyShouldDieWhenPlayerDealsEnoughDamage() {

        Player player = new Player(3, 3, 100, 50, 2);

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        CombatSystem combatSystem = new CombatSystem();

        combatSystem.attack(player, enemy);

        assertEquals(0, enemy.getHealth());
    }

    @Test
    void deadPlayerShouldNotBeAbleToAttack() {

        Player player = new Player(3, 3, 100, 10, 5);

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        player.takeDamage(100);

        CombatSystem combatSystem = new CombatSystem();

        int damage = combatSystem.attack(player, enemy);

        assertEquals(0, damage);
        assertEquals(30, enemy.getHealth());
    }

    @Test
    void deadEnemyShouldNotBeAbleToAttack() {

        Player player = new Player(3, 3, 100, 10, 5);

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        enemy.takeDamage(30);

        CombatSystem combatSystem = new CombatSystem();

        int damage = combatSystem.attack(enemy, player);

        assertEquals(0, damage);
        assertEquals(100, player.getHealth());
    }

    @Test
    void fightTurnShouldMakePlayerAndEnemyAttack() {

        Player player = new Player(
                3,
                3,
                100,
                10,
                5
        );

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        CombatSystem combatSystem = new CombatSystem();

        combatSystem.fightTurn(player, enemy);

        assertEquals(22, enemy.getHealth());
        assertEquals(97, player.getHealth());
    }

    @Test
    void enemyShouldNotAttackAfterBeingKilled() {

        Player player = new Player(
                3,
                3,
                100,
                50,
                5
        );

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                5,
                30,
                8,
                2
        );

        CombatSystem combatSystem = new CombatSystem();

        combatSystem.fightTurn(player, enemy);

        assertEquals(0, enemy.getHealth());

        // Le Gobelin est mort avant de pouvoir riposter.
        assertEquals(100, player.getHealth());
    }
}