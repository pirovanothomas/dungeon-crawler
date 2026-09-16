package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void enemyShouldHaveCorrectInitialValues() {

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                6,
                30,
                8,
                2
        );

        assertEquals("Gobelin", enemy.getName());
        assertEquals(5, enemy.getX());
        assertEquals(6, enemy.getY());

        assertEquals(30, enemy.getMaxHealth());
        assertEquals(30, enemy.getHealth());

        assertEquals(8, enemy.getAttack());
        assertEquals(2, enemy.getDefense());

        assertTrue(enemy.isAlive());
    }

    @Test
    void enemyShouldTakeDamage() {

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                6,
                30,
                8,
                2
        );

        enemy.takeDamage(10);

        assertEquals(20, enemy.getHealth());
        assertTrue(enemy.isAlive());
    }

    @Test
    void enemyShouldDieWhenHealthReachesZero() {

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                6,
                30,
                8,
                2
        );

        enemy.takeDamage(30);

        assertEquals(0, enemy.getHealth());
        assertFalse(enemy.isAlive());
    }

    @Test
    void enemyHealthShouldNotBecomeNegative() {

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                6,
                30,
                8,
                2
        );

        enemy.takeDamage(100);

        assertEquals(0, enemy.getHealth());
    }

    @Test
    void enemyShouldBeAbleToMove() {

        Enemy enemy = new Enemy(
                "Gobelin",
                5,
                6,
                30,
                8,
                2
        );

        enemy.move(1, -2);

        assertEquals(6, enemy.getX());
        assertEquals(4, enemy.getY());
    }
}