package com.thomas.dungeoncrawler;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Dungeon dungeon;
    private final Player player;
    private final List<Enemy> enemies;
    private final CombatSystem combatSystem;

    public GameEngine() {

        DungeonGenerator generator = new DungeonGenerator();
        this.dungeon = new Dungeon(20, 8, generator);

        this.player = createPlayer(generator);
        this.enemies = createEnemies(generator);
        this.combatSystem = new CombatSystem();
    }

    public GameEngine(Dungeon dungeon, Player player) {
        this.dungeon = dungeon;
        this.player = player;
        this.enemies = new ArrayList<>();
        this.combatSystem = new CombatSystem();
    }

    public GameEngine(
            Dungeon dungeon,
            Player player,
            List<Enemy> enemies
    ) {
        this.dungeon = dungeon;
        this.player = player;
        this.enemies = new ArrayList<>(enemies);
        this.combatSystem = new CombatSystem();
    }

    private Player createPlayer(DungeonGenerator generator) {

        if (generator.getRooms().isEmpty()) {
            return new Player(1, 1);
        }

        Room firstRoom = generator.getRooms().get(0);

        return new Player(
                firstRoom.getCenterX(),
                firstRoom.getCenterY()
        );
    }

    private List<Enemy> createEnemies(DungeonGenerator generator) {

        List<Enemy> enemies = new ArrayList<>();

        List<Room> rooms = generator.getRooms();

        for (int i = 1; i < rooms.size(); i++) {

            Room room = rooms.get(i);

            enemies.add(
                    new Enemy(
                            "Gobelin",
                            room.getCenterX(),
                            room.getCenterY(),
                            30,
                            8,
                            2
                    )
            );
        }

        return enemies;
    }

    public void movePlayer(int deltaX, int deltaY) {

        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        if (!dungeon.isInside(newX, newY)) {
            return;
        }

        Tile destination = dungeon.getTile(newX, newY);

        if (!destination.isWalkable()) {
            return;
        }

        Enemy enemy = findEnemyAt(newX, newY);

        if (enemy != null) {
            fightEnemy(enemy);
            return;
        }

        player.move(deltaX, deltaY);
    }

    public void fightEnemy(Enemy enemy) {
        combatSystem.fightTurn(player, enemy);
    }

    private Enemy findEnemyAt(int x, int y) {

        for (Enemy enemy : enemies) {

            if (!enemy.isAlive()) {
                continue;
            }

            if (enemy.getX() == x && enemy.getY() == y) {
                return enemy;
            }
        }

        return null;
    }

    public int attackEnemy(Enemy enemy) {
        return combatSystem.attack(player, enemy);
    }

    public Player getPlayer() {
        return player;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }
}