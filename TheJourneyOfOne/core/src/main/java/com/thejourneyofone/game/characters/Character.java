package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Character {

    private Sprite sprite;
    private float health, speed;

    private String curDirection; //R or L
    private boolean flip = false;

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

        if(x < 0) curDirection = "L";
        else if(x > 0) curDirection = "R";
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

    public void updateDirection() {
        if(curDirection == "L") {
            flip = true;
        }
        else flip = false;
    }

    public float getX() {
        return sprite.getX();
    }

    public boolean shouldFlip() {
        return flip;
    }

    public float getY() {
        return sprite.getY();
    }

    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    public void setSize(float x, float y) {
        sprite.setSize(x,y);
    }

    public Vector2 getSize() {
        return new Vector2(sprite.getHeight(), sprite.getWidth());
    }

    public float getSpeed() {
        return speed;
    }
}
