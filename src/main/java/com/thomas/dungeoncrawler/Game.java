package com.thomas.dungeoncrawler;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        GameEngine engine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running && engine.getGameState() == GameState.PLAYING) {

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

            CombatResult combatResult = engine.consumeLastCombatResult();

            if (combatResult != null) {
                displayCombatResult(combatResult);
            }
        }

        if (engine.getGameState() == GameState.VICTORY) {
            System.out.println();
            System.out.println("=== VICTOIRE ===");
            System.out.println("Tous les ennemis ont été vaincus !");
        }

        if (engine.getGameState() == GameState.DEFEAT) {
            System.out.println();
            System.out.println("=== DEFAITE ===");
            System.out.println("Votre personnage est mort.");
        }

        scanner.close();

        System.out.println("Merci d'avoir joué !");
    }

    private static void displayCombatResult(
            CombatResult result
    ) {

        Enemy enemy = result.getEnemy();

        if (result.getPlayerDamage() > 0) {
            System.out.println(
                    "Vous attaquez " + enemy.getName()
                            + " et infligez "
                            + result.getPlayerDamage()
                            + " dégâts."
            );
        }

        if (result.getEnemyDamage() > 0) {
            System.out.println(
                    enemy.getName()
                            + " vous attaque et inflige "
                            + result.getEnemyDamage()
                            + " dégâts."
            );
        }

        if (result.isEnemyDefeated()) {
            System.out.println(
                    enemy.getName() + " est vaincu !"
            );
        }

        if (result.isPlayerDefeated()) {
            System.out.println("Vous êtes mort !");
        }
    }

    private static void displayDungeon(GameEngine engine) {

        Dungeon dungeon = engine.getDungeon();
        Player player = engine.getPlayer();

        System.out.println();
        System.out.println("=== DUNGEON CRAWLER ===");
        System.out.println(
                "PV : " + player.getHealth()
                        + "/" + player.getMaxHealth()
        );
        System.out.println(
                "ATK : " + player.getAttack()
                        + " | DEF : " + player.getDefense()
        );
        System.out.println(
                "Ennemis : " + engine.getAliveEnemyCount()
        );
        System.out.println();

        for (int y = 0; y < dungeon.getHeight(); y++) {

            for (int x = 0; x < dungeon.getWidth(); x++) {

                if (player.getX() == x && player.getY() == y) {
                    System.out.print("@");
                    continue;
                }

                Enemy enemy = findAliveEnemyAt(engine, x, y);

                if (enemy != null) {

                    if (enemy.getType() != null) {
                        System.out.print(enemy.getType().getSymbol());
                    } else {
                        System.out.print("G");
                    }

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

    private static Enemy findAliveEnemyAt(
            GameEngine engine,
            int x,
            int y
    ) {

        for (Enemy enemy : engine.getEnemies()) {

            if (!enemy.isAlive()) {
                continue;
            }

            if (enemy.getX() == x && enemy.getY() == y) {
                return enemy;
            }
        }

        return null;
    }
}