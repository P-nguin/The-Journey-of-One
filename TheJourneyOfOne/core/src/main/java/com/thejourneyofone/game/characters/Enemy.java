package com.thejourneyofone.game.characters;

import com.badlogic.gdx.math.Vector2;
import com.thejourneyofone.game.EnemyManager;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.Resources.CharacterAnimations;

public class Enemy extends Character {

    private float detectionRange;

    public Enemy(float health, float speed, float x, float y, CharacterOptions characterType, CharacterAnimations animations, float detectionRange) {
        super(health, speed, x, y, characterType, animations);
        this.detectionRange = detectionRange*detectionRange; //To avoid having to take the square root when comparing distance.
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        updateMove(dt);
    }

    @Override
    public void updateMove(float dt) {
        Vector2 playerPos = EnemyManager.getPlayerPos();
        Vector2 curPos = new Vector2(getPosX(), getPosY());
        if(curPos.dst2(playerPos) <= detectionRange) {
            Vector2 dif = new Vector2();
            dif.set(playerPos).sub(curPos);
            dif = dif.nor();

            move(dif.x*getSpeed()*dt, dif.y*getSpeed()*dt);
        }
    }
}
