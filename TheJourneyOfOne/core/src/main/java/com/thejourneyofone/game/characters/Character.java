package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Character {

    private Sprite sprite;
    private float health, speed;

    public Character(float health, float speed) {
        this.health = health; this.speed = speed;
        this.sprite = new Sprite();
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
        if(x < 0 && !sprite.isFlipX()) {
            sprite.flip(true, false);
        }
        else if(x > 0 && sprite.isFlipX()) {
            sprite.flip(true, false);
        }

        sprite.setPosition(sprite.getX()+x, sprite.getY()+y);
    }

    public void setTexture(Texture texture) {
        sprite.setTexture(texture);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }
}
