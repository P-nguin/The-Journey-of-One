package com.daniel_ashley.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Character {
    private Sprite sprite;
    private float health;
    private float speed;

    public Character(Texture texture, float health, float speed) {
        sprite = new Sprite(texture);
        this.health = health;
        this.speed = speed;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    public void update(float dt) {

    }
    public void move(Vector2 move) {
        move.x *= speed; move.y *= speed;
        sprite.setPosition(sprite.getX() + move.x, sprite.getY() + move.y);
    }
    public void move(float x, float y) {
        x *= speed; y *= speed;
        sprite.setPosition(sprite.getX() + x, sprite.getY() + y);
    }
}
