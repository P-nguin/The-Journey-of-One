package com.thejourneyofone.game.characters;

import com.badlogic.gdx.math.Vector2;
import com.thejourneyofone.game.GameManager;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.Resources.CharacterAnimations;

public class Enemy extends Character {

    private float detectionRange;
    private float cautionRange;
    private float attackRange;

    public Enemy(float health, float speed, float x, float y, float hitBoxWidth, float hitBoxHeight, CharacterOptions characterType, CharacterAnimations animations, float detectionRange, float cautionRange, float attackRange) {
        super(health, speed, x, y, hitBoxWidth, hitBoxHeight, characterType, animations);

        //To avoid having to take the square root when comparing distance.
        this.detectionRange = detectionRange*detectionRange;
        this.cautionRange = cautionRange;
        this.attackRange = attackRange;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        updateMove(dt);
    }

    @Override
    public void updateMove(float dt) {
        Vector2 playerPos = GameManager.getPlayerPos();
        Vector2 curPos = new Vector2(getPosX(), getPosY());
        float distance = curPos.dst2(playerPos);
        if(distance <= attackRange) {
            attack(1);
        }
        else if(distance <= cautionRange) { //Move Half Speed
            Vector2 dir = getDir(playerPos, curPos);
            move(dir.x*getSpeed()*dt/2, dir.y*getSpeed()*dt/2);
        }
        else if(distance <= detectionRange) {
            Vector2 dir = getDir(playerPos, curPos);
            move(dir.x*getSpeed()*dt, dir.y*getSpeed()*dt);
        }
    }

    private Vector2 getDir(Vector2 playerPos, Vector2 curPos) {
        return playerPos.sub(curPos).nor();
    }
}
