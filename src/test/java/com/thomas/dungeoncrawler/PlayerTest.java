package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}