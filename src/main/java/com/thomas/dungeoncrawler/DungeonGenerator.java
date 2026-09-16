package com.thomas.dungeoncrawler;

public class DungeonGenerator {

    public void generate(Dungeon dungeon) {

        // Pour l'instant, on reprend notre génération actuelle.
        // Elle sera rendue aléatoire dans l'étape suivante.

        Room firstRoom = new Room(2, 2, 6, 4);
        dungeon.carveRoom(firstRoom);

        Room secondRoom = new Room(12, 2, 6, 4);
        dungeon.carveRoom(secondRoom);

        Room thirdRoom = new Room(7, 6, 6, 2);
        dungeon.carveRoom(thirdRoom);

        dungeon.carveHorizontalCorridor(7, 12, 3);
        dungeon.carveHorizontalCorridor(7, 9, 6);
        dungeon.carveVerticalCorridor(5, 6, 9);
    }
}