package com.thomas.dungeoncrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnemyTypeTest {

    @Test
    void goblinShouldHaveCorrectStatistics() {

        assertEquals("Gobelin", EnemyType.GOBLIN.getName());
        assertEquals('G', EnemyType.GOBLIN.getSymbol());
        assertEquals(30, EnemyType.GOBLIN.getMaxHealth());
        assertEquals(8, EnemyType.GOBLIN.getAttack());
        assertEquals(2, EnemyType.GOBLIN.getDefense());
    }

    @Test
    void orcShouldHaveCorrectStatistics() {

        assertEquals("Orc", EnemyType.ORC.getName());
        assertEquals('O', EnemyType.ORC.getSymbol());
        assertEquals(50, EnemyType.ORC.getMaxHealth());
        assertEquals(12, EnemyType.ORC.getAttack());
        assertEquals(4, EnemyType.ORC.getDefense());
    }

    @Test
    void trollShouldHaveCorrectStatistics() {

        assertEquals("Troll", EnemyType.TROLL.getName());
        assertEquals('T', EnemyType.TROLL.getSymbol());
        assertEquals(80, EnemyType.TROLL.getMaxHealth());
        assertEquals(15, EnemyType.TROLL.getAttack());
        assertEquals(7, EnemyType.TROLL.getDefense());
    }
}