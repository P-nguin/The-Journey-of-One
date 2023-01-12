package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Character {

    private Sprite sprite;
    private float health, speed;

    public Character(float health, float speed, float x, float y) {
        this.health = health; this.speed = speed;
        this.sprite = new Sprite();
        sprite.setSize(x,y);
    }

    public Character(Texture texture, float health, float speed) {
        this.health = health; this.speed = speed;
        this.sprite = new Sprite(texture);
    }

    public void update(float dt) {

    }

    public void move(Vector2 move) {
        move(move.x, move.y);
    }

    public void move(float x, float y) {
        sprite.setPosition(sprite.getX()+x, sprite.getY()+y);
    }

    public void dispose() {
    }

    public void setTexture(Texture texture) {
        sprite.setTexture(texture);
    }

    public void setTexture(TextureRegion texture) {
        sprite.setRegion(texture);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void flip(boolean flip) {
        sprite.flip(flip, false);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getSpeed() {
        return speed;
    }
}
