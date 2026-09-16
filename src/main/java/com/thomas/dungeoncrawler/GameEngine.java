package com.thomas.dungeoncrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {

    private final Dungeon dungeon;
    private final Player player;
    private final List<Enemy> enemies;
    private final CombatSystem combatSystem;
    private final Random random;
    private CombatResult lastCombatResult;

    private GameState gameState;

    public GameEngine() {

        this.random = new Random();

        DungeonGenerator generator = new DungeonGenerator();
        this.dungeon = new Dungeon(20, 8, generator);

        this.player = createPlayer(generator);
        this.enemies = createEnemies(generator);
        this.combatSystem = new CombatSystem();
        this.gameState = GameState.PLAYING;
        this.lastCombatResult = null;
    }

    public GameEngine(Dungeon dungeon, Player player) {
        this(dungeon, player, new ArrayList<>());
    }

    public GameEngine(
            Dungeon dungeon,
            Player player,
            List<Enemy> enemies
    ) {
        this.random = new Random();

        this.dungeon = dungeon;
        this.player = player;
        this.enemies = new ArrayList<>(enemies);
        this.combatSystem = new CombatSystem();
        this.gameState = GameState.PLAYING;
        this.lastCombatResult = null;
    }

    public GameState getGameState() {
        return gameState;
    }

    private void updateGameState() {

        if (!player.isAlive()) {
            gameState = GameState.DEFEAT;
            return;
        }

        if (getAliveEnemyCount() == 0) {
            gameState = GameState.VICTORY;
        }
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

        EnemyType[] types = EnemyType.values();

        for (int i = 1; i < rooms.size(); i++) {

            Room room = rooms.get(i);

            EnemyType type = types[random.nextInt(types.length)];

            enemies.add(
                    new Enemy(
                            type,
                            room.getCenterX(),
                            room.getCenterY()
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

        if (gameState != GameState.PLAYING) {
            return;
        }

        lastCombatResult = combatSystem.fightTurn(player, enemy);

        updateGameState();
    }

    public CombatResult getLastCombatResult() {
        return lastCombatResult;
    }

    public CombatResult consumeLastCombatResult() {
        CombatResult result = lastCombatResult;
        lastCombatResult = null;
        return result;
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

        if (gameState != GameState.PLAYING) {
            return 0;
        }

        int damage = combatSystem.attack(player, enemy);

        updateGameState();

        return damage;
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

    public int getAliveEnemyCount() {

        int count = 0;

        for (Enemy enemy : enemies) {

            if (enemy.isAlive()) {
                count++;
            }
        }

        return count;
    }
}