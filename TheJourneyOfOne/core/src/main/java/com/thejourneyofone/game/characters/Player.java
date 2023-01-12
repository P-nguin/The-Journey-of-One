package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.AnimationOptions;

public class Player extends Character {

    private boolean isRight = true, shouldFlip = false;
    private float timeCnt = 0;
    private float idleBattle = 0;
    private final float idleBattleMax = 3.0f;
    private boolean isIdleBattle = false;
    private AnimationOptions curAnimation;

    public Player(float health, float speed) {
        super(health, speed);
        curAnimation = AnimationOptions.SwordOfStormsKneel;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        timeCnt += dt;
        if(Resources.getAnimation(curAnimation).isAnimationFinished(timeCnt)) {
            setAnimation(AnimationOptions.SwordOfStormsIdle); idleBattle = 0;
        }

        if(isIdleBattle && idleBattle > idleBattleMax) {
            idleBattle = 0; isIdleBattle = false;
            setAnimation(AnimationOptions.SwordOfStormsKneel);
        }
        getSprite().setRegion(Resources.getAnimation(curAnimation).getKeyFrame(timeCnt));
    }

    @Override
    public void move(float x, float y) {
        super.move(x, y);

        setAnimation(AnimationOptions.SwordOfStormsRun);

        if(x < 0 && isRight) {
            isRight = false;
            shouldFlip = true;
        }
        else if(x > 0 && !isRight) {
            isRight = true;
            shouldFlip = true;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void setAnimation(AnimationOptions newAnimation) {
        curAnimation = newAnimation;
        if(!isRight) shouldFlip = true;
    }
}
