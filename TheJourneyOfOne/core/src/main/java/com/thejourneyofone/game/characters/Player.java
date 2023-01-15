package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.AnimationOptions;
import com.thejourneyofone.game.screens.GameScreen;

public class Player extends Character {

    public static final int SIZEX = 189;
    public static final int SIZEY= 47;

    private boolean prevLeftMove, prevRightMove, prevUpMove, prevDownMove;

    private final float idleBattleMax = 5.0f;
    private float idleBattleTime = 0;

    public Player(float health, float speed) {
        super(health, speed, SIZEX / GameScreen.PPM*2.5f, SIZEY / GameScreen.PPM*2.5f);
        setAnimation(AnimationOptions.SwordOfStormsKneel);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void updateAnimation(float dt) {
        boolean isMoving = updateMove(dt);
        updateDirection();

        Animation curAni = Resources.getAnimation(getCurAnimation());
        if(curAni.isAnimationFinished(getTimeCnt()) && curAni.getPlayMode() != Animation.PlayMode.LOOP || getCurAnimation() == AnimationOptions.SwordOfStormsRun && !isMoving) {
            if(getCurAnimation() == AnimationOptions.SwordOfStormsAttack1) {
                checkKeys();
            }

            setAnimation(AnimationOptions.SwordOfStormsIdle);
        }

        if(getCurAnimation() == AnimationOptions.SwordOfStormsIdle) idleBattleTime += dt;
        if(idleBattleTime >= idleBattleMax) {
            setAnimation(AnimationOptions.SwordOfStormsKneel);
            idleBattleTime = 0;
        }
    }

    private void checkKeys() {
        setDownMove(prevDownMove);
        setRightMove(prevRightMove);
        setUpMove(prevUpMove);
        setLeftMove(prevLeftMove);
    }

    @Override
    public void setRightMove(boolean t) {
        super.setRightMove(t);
        prevRightMove = t;
    }

    @Override
    public void setLeftMove(boolean t) {
        super.setLeftMove(t);
        prevLeftMove = t;
    }

    @Override
    public void setUpMove(boolean t) {
        super.setUpMove(t);
        prevUpMove = t;
    }

    @Override
    public void setDownMove(boolean t) {
        super.setDownMove(t);
        prevDownMove = t;
    }

    @Override
    public void attack() {
        super.attack();
        setAnimation(AnimationOptions.SwordOfStormsAttack1);
    }

    @Override
    public boolean updateMove(float dt) {
        if(getCurAnimation() == AnimationOptions.SwordOfStormsAttack1) return false;
        return super.updateMove(dt);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void setAnimation(AnimationOptions newAnimation) {
        if(getCurAnimation() != newAnimation) {
            idleBattleTime = 0;
        }

        super.setAnimation(newAnimation);
    }
}
