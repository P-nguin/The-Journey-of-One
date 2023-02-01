package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.thejourneyofone.game.GameManager;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.Resources.CharacterAnimations;

public class Character {
    private Sprite sprite;
    private float health, speed;

    public Rectangle hitBox;

    private CharacterOptions characterType;
    private CharacterAnimations curAnimation;
    private float timeCnt;

    private boolean rightMove;
    private boolean leftMove;
    private boolean upMove;
    private boolean downMove;
    private int curDirection; //0 is right, 1 is left

    private float damage;

    public Character(float health, float speed, float damage, float spriteSizeWidth, float spriteSizeHeight, float hitBoxWidth, float hitBoxHeight, CharacterOptions characterType, CharacterAnimations animation) {
        this.health = health; this.speed = speed; this.damage = damage;

        this.sprite = new Sprite();
        sprite.setSize(spriteSizeWidth,spriteSizeHeight);

        curDirection = 0;
        this.characterType = characterType;
        this.curAnimation = animation;
        timeCnt = 0;

        hitBox = new Rectangle(sprite.getX(), sprite.getY(), hitBoxWidth, hitBoxHeight);

        setTextureRegion(Resources.getAnimation(characterType, curAnimation, curDirection).getKeyFrame(timeCnt));
    }

    public void update(float dt) {
        timeCnt += dt;

        updateMove(dt);
        updateHitBox();
        updateAnimation(dt);
    }

    public void updateHitBox() {
        hitBox.setPosition(getPosX() - hitBox.getWidth()/2, getPosY() - hitBox.getHeight()/2);
    }

    public void updateAnimation(float dt) {
        Animation<TextureRegion> ani = Resources.getAnimation(characterType, curAnimation, curDirection);
        if(ani.isAnimationFinished(timeCnt) && ani.getPlayMode() != Animation.PlayMode.LOOP) {
            setAnimation(CharacterAnimations.Idle);
            timeCnt = 0;
        }

        sprite.setRegion(Resources.getAnimation(characterType, curAnimation, curDirection).getKeyFrame(timeCnt));
    }

    public void updateMove(float dt) {
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

        if(x != 0 || y != 0) move(x,y);
    }

    public void move(float x, float y) {
        sprite.translate(x, y);
        setAnimation(CharacterAnimations.Run);

        if(x < 0) curDirection = 1;
        else if(x > 0) curDirection =  0;
    }

    public void attack(int type) {
        leftMove = false; rightMove = false; upMove = false; downMove = false;
        if(type == 1) setAnimation(CharacterAnimations.Attack1);
        else if(type == 2) setAnimation(CharacterAnimations.Attack2);
    }

    //true if dead
    public boolean takeDamage(float damage) {
        health -= damage;
        if(health <= 0) {
            setAnimation(CharacterAnimations.Death);
            return true;
        }
        setAnimation(CharacterAnimations.Damaged);
        return false;
    }

    public void dispose() {

    }

    public void setTexture(TextureRegion texture) {
        sprite.setRegion(texture);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void updateDirection(int dir) {
        curDirection = dir;
    }

    public void setAnimation(CharacterAnimations newAnimation) {
        if(curAnimation != newAnimation) {
            curAnimation = newAnimation;
            timeCnt = 0;
        }
    }

    public void setTextureRegion(TextureRegion region) {
        sprite.setRegion(region);
    }

    public float getHealth() {
        return health;
    }

    public float getPosX() {
        return getX() + sprite.getWidth()/2;
    }

    public float getPosY() {
        return getY() + sprite.getHeight()/2;
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

    public float getSpeed() {
        return speed;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public float getDamage() {
        return damage;
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

    public void setTimeCnt(float timeCnt) {
        this.timeCnt = timeCnt;
    }

    public int getCurDirection() {
        return curDirection;
    }

    public CharacterAnimations getCurAnimation() {
        return curAnimation;
    }

    public CharacterOptions getCharacterType() {
        return characterType;
    }

    public float getTimeCnt() {
        return timeCnt;
    }

    public void setHitBoxPosition(float x, float y) {
        hitBox.setPosition(x,y);
    }
}
