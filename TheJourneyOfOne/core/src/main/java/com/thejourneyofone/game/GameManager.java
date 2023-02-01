package com.thejourneyofone.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.thejourneyofone.game.characters.Enemy;
import com.thejourneyofone.game.characters.Player;
import com.thejourneyofone.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameManager {
    private static Queue<Rectangle> attacks;
    private static Queue<Boolean> isPlayerAttack;
    private static ArrayList<Enemy> enemies;
    private static Player player;

    public static void init(Player _player) {
        enemies = new ArrayList<>();
        attacks = new LinkedList<>();
        isPlayerAttack = new LinkedList<>();
        player = _player;
    }

    public static void update(float dt) {
        updateAttack(dt);
    }

    public static void updateAttack(float dt) {
        while(!attacks.isEmpty()) {
            Rectangle cur = attacks.poll();
            if(isPlayerAttack.poll()) {
                for(Enemy enemy : enemies) {
                    if(enemy.getHitBox().overlaps(cur)) {
                        enemy.takeDamage(player.getDamage());
                    }
                }
            }
            else {

            }
        }
    }

    public static Queue<Rectangle> testing() {
        return attacks;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public static void addAttack(Rectangle rect, boolean isPlayer) {
        attacks.add(rect); isPlayerAttack.add(isPlayer);
    }

    public static Vector2 getPlayerPos() {
        return new Vector2(player.getPosX(), player.getPosY());
    }
}
