package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.screens.GameScreen;

public class Character {
    private Sprite sprite;
    private float health, speed;

    private String curDirection = ""; //R or L
    private boolean flip = false;

    private TextureRegion region;

    public Character(float health, float speed, float x, float y) {
        this.health = health; this.speed = speed;
        this.sprite = new Sprite();
        sprite.setSize(x,y);
    }

    public void update(float dt) {

    }

    public void move(float x, float y) {
        sprite.setPosition((sprite.getX()+x), (sprite.getY()+y));

        if(x < 0) curDirection = "L";
        else if(x > 0) curDirection = "R";
    }

    public void dispose() {
    }

    public void setTexture(TextureRegion texture) {
        sprite.setRegion(texture);
        region = texture;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }

    public void updateDirection() {
        if(curDirection.equals("L")) {
            flip = true;
        }
        else flip = false;
    }

    public float getPosX() {
        return getX() + (region.getRegionWidth() + 11)/GameScreen.PPM;
    }

    public float getPosY() {
        return getY() + (region.getRegionHeight())/GameScreen.PPM;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getWidth() {
        return  sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public boolean shouldFlip() {
        return flip;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isFacingRight() {
        return curDirection.equals("R");
    }
}
