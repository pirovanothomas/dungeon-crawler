package com.thomas.dungeoncrawler;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        GameEngine engine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            displayDungeon(engine);

            System.out.println();
            System.out.println("Déplacement : Z Q S D");
            System.out.println("X pour quitter");
            System.out.print("Commande : ");

            String command = scanner.nextLine().toLowerCase();

            switch (command) {

                case "z":
                    engine.movePlayer(0, -1);
                    break;

                case "q":
                    engine.movePlayer(-1, 0);
                    break;

                case "s":
                    engine.movePlayer(0, 1);
                    break;

                case "d":
                    engine.movePlayer(1, 0);
                    break;

                case "x":
                    running = false;
                    break;

                default:
                    System.out.println("Commande inconnue.");
            }
        }

        scanner.close();

        System.out.println("Merci d'avoir joué !");
    }

    private static void displayDungeon(GameEngine engine) {

        Dungeon dungeon = engine.getDungeon();
        Player player = engine.getPlayer();

        for (int y = 0; y < dungeon.getHeight(); y++) {

            for (int x = 0; x < dungeon.getWidth(); x++) {

                if (player.getX() == x && player.getY() == y) {
                    System.out.print("@");
                    continue;
                }

                Tile tile = dungeon.getTile(x, y);

                if (tile.getType() == TileType.WALL) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }

            System.out.println();
        }
    }
}