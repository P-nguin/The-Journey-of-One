package com.thejourneyofone.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thejourneyofone.game.characters.Character;
import com.thejourneyofone.game.characters.Enemy;
import com.thejourneyofone.game.characters.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameManager {
    private static Queue<Rectangle> attacks;
    private static ArrayList<Enemy> enemies;
    private static Player player;

    public static void init(Player player) {
        enemies = new ArrayList<>();
        attacks = new LinkedList<>();
    }

    public static void update(float dt) {
        updateAttack(dt);
    }

    public static void updateAttack(float dt) {
        while(!attacks.isEmpty()) {
            
        }
    }

    public static void addAttack(Rectangle rect) {
        attacks.add(rect);
    }

    public static Vector2 getPlayerPos() {
        return new Vector2(player.getPosX(), player.getPosY());
    }
}
