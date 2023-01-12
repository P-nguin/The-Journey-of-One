package com.thejourneyofone.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.AnimationOptions;

public class Player extends Character {

    private static final float SIZEX = 134;
    private static final float SIZEY= 47;

    private float timeCnt = 0;
    private AnimationOptions curAnimation = AnimationOptions.SwordOfStormsKneel;

    private boolean leftMove, rightMove;

    private final float idleBattleMax = 5.0f;
    private float idleBattleTime = 0;
    private boolean isKneel = true;
    private boolean isRunning = false;
    private boolean isIdling = false;

    public Player(float health, float speed) {
        super(health, speed, SIZEX, SIZEY);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        timeCnt += dt;

        isRunning = updateMove(dt);

        if(isRunning) {
            setAnimation(AnimationOptions.SwordOfStormsRun);
            isKneel = false;
            isIdling = false;
        }
        else if(isIdling) {
            idleBattleTime += dt;
            setAnimation(AnimationOptions.SwordOfStormsIdle);
            if(idleBattleTime >= idleBattleMax) {
                isIdling = false; isKneel = true;
                idleBattleTime = 0;
            }
        }
        else if(isKneel) setAnimation(AnimationOptions.SwordOfStormsKneel);
        else {
            isIdling =  true;
        }

        setTexture(Resources.getAnimation(curAnimation).getKeyFrame(timeCnt));
    }

    public void setRightMove(boolean t) {
        if(leftMove & t) leftMove = false;
        rightMove = t;
    }

    public void setLeftMove(boolean t) {
        if(rightMove & t) rightMove = false;
        leftMove = t;
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

        if(x != 0 || y != 0) {
            ret = true;
        }
        move(x,y);
        return ret;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void setAnimation(AnimationOptions newAnimation) {
        curAnimation = newAnimation;
    }
}
