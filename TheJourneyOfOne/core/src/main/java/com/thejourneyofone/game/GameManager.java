package com.thejourneyofone.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private static Queue<Float> attackDamages;
    private static ArrayList<Enemy> enemies, enemiesDead;
    private static Player player;

    public static void init(Player _player) {
        enemies = new ArrayList<>();
        enemiesDead = new ArrayList<>();
        attacks = new LinkedList<>();
        attackDamages = new LinkedList<>();
        isPlayerAttack = new LinkedList<>();
        player = _player;
    }

    public static void update(float dt) {
        updateAttack(dt);
        updateEnemies(dt);
    }

    public static void updateAttack(float dt) {
        while(!attacks.isEmpty()) {
            Rectangle cur = attacks.poll();
            float damage = attackDamages.poll();
            if(isPlayerAttack.poll()) {
                for(int i = 0 ; i < enemies.size(); i++) {
                    if(enemies.get(i).getHitBox().overlaps(cur)) {
                        System.out.println(enemies.get(i).getHealth()-1);
                        if(enemies.get(i).takeDamage(damage)) {
                            enemiesDead.add(enemies.get(i));
                            enemies.remove(i); i--;
                        }
                    }
                }
            }
            else {
                if(player.getHitBox().overlaps(cur)) {
                    if(!player.isBlocking() && player.takeDamage(damage)) { //maybe consider direction, idiot
                        System.out.println("Player not deleted because it will break things");
                    }
                    System.out.println("Player Damaged " + player.getHealth());
                }
            }
        }
    }

    public static void updateEnemies(float dt) {
        for(Enemy e : enemies) {
            e.update(dt);
        }
        for(int i = 0; i < enemiesDead.size(); i++) {
            Enemy e = enemiesDead.get(i);
            e.update(dt);
            if(!e.getCurAnimation().equals(Resources.CharacterAnimations.Death)) {
                e.dispose();
                enemiesDead.remove(i); i--;
            }
        }
    }

    public static void drawEnemies(SpriteBatch batch) {
        for(Enemy e : enemies) {
            e.draw(batch);
        }
        for(Enemy e : enemiesDead) {
            e.draw(batch);
        }
    }

    public static Queue<Rectangle> testing() {
        return attacks;
    }

    public static void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public static void addAttack(Rectangle rect, float damage, boolean isPlayer) {
        attacks.add(rect);
        attackDamages.add(damage);
        isPlayerAttack.add(isPlayer);
    }

    public static Vector2 getPlayerPos() {
        return new Vector2(player.getPosX(), player.getPosY());
    }
}
