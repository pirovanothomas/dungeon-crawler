package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

    @Test
    void playerShouldHaveInitialPosition() {
        Player player = new Player(3, 4);

        assertEquals(3, player.getX());
        assertEquals(4, player.getY());
    }

    @Test
    void playerShouldMove() {
        Player player = new Player(3, 4);

        player.move(1, 0);

        assertEquals(4, player.getX());
        assertEquals(4, player.getY());
    }

    @Test
    void playerShouldMoveVertically() {
        Player player = new Player(3, 4);

        player.move(0, -1);

        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
    }

    @Test
    void playerShouldHaveInitialHealth() {

        Player player = new Player(3, 3);

        assertEquals(100, player.getMaxHealth());
        assertEquals(100, player.getHealth());
    }

    @Test
    void playerShouldTakeDamage() {

        Player player = new Player(3, 3);

        player.takeDamage(30);

        assertEquals(70, player.getHealth());
    }

    @Test
    void playerShouldNotHaveNegativeHealth() {

        Player player = new Player(3, 3);

        player.takeDamage(150);

        assertEquals(0, player.getHealth());
    }

    @Test
    void playerShouldBeDeadWhenHealthReachesZero() {

        Player player = new Player(3, 3);

        player.takeDamage(100);

        assertFalse(player.isAlive());
    }
}