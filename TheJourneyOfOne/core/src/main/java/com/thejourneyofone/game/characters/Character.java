package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.AnimationOptions;
import com.thejourneyofone.game.screens.GameScreen;

public class Character {
    private Sprite sprite;
    private float health, speed;

    private AnimationOptions curAnimation;
    private float timeCnt;

    private String curDirection = ""; //R or L
    private boolean flip = false;
    private boolean leftMove;

    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

    private TextureRegion region;

    public Character(float health, float speed, float x, float y) {
        this.health = health; this.speed = speed;
        this.sprite = new Sprite();
        sprite.setSize(x,y);
    }

    public void update(float dt) {
        timeCnt += dt;

        updateAnimation(dt);
        //Flip direction of character
        TextureRegion keyFrame = Resources.getAnimation(curAnimation).getKeyFrame(timeCnt);
        if(shouldFlip() != keyFrame.isFlipX()) {
            keyFrame.flip(true, false);
        }
        setTexture(Resources.getAnimation(curAnimation).getKeyFrame(timeCnt));
    }

    public void updateAnimation(float dt) {

    }

    public boolean updateMove(float dt) {
        boolean ret = false;
        float x = 0, y = 0;
        if(rightMove) {
            x += getSpeed() * dt;
        }
        if(leftMove) {
            x -= getSpeed() * dt;
        }
        if(upMove) {
            y += getSpeed() * dt;
        }
        if(downMove) {
            y -= getSpeed() * dt;
        }

        if(x != 0 || y != 0) {
            setAnimation(AnimationOptions.SwordOfStormsRun);
            ret = true;
        }
        move(x,y);

        return ret;
    }

    public void move(float x, float y) {
        sprite.setPosition((sprite.getX()+x), (sprite.getY()+y));

        if(x < 0) curDirection = "L";
        else if(x > 0) curDirection = "R";
    }

    public void attack() {
        leftMove = false; rightMove = false; upMove = false; downMove = false;
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
        flip = curDirection.equals("L");
    }

    public void setAnimation(AnimationOptions newAnimation) {
        if(curAnimation != newAnimation) {
            curAnimation = newAnimation;
            timeCnt = 0;
        }
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

    public boolean isLeftMove() {
        return leftMove;
    }

    public boolean isRightMove() {
        return rightMove;
    }

    public boolean isUpMove() {
        return upMove;
    }

    public boolean isDownMove() {
        return downMove;
    }

    public void setRightMove(boolean t) {
        if(leftMove & t) leftMove = false;
        rightMove = t;
    }

    public void setLeftMove(boolean t) {
        if(rightMove & t) rightMove = false;
        leftMove = t;
    }

    public void setUpMove(boolean t) {
        if(downMove & t) downMove = false;
        upMove = t;
    }

    public void setDownMove(boolean t) {
        if(upMove & t) upMove = false;
        downMove = t;
    }

    public AnimationOptions getCurAnimation() {
        return curAnimation;
    }

    public float getTimeCnt() {
        return timeCnt;
    }
}
