package com.thejourneyofone.game.characters;

import com.thejourneyofone.game.Resources.CharacterAnimations;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.screens.GameScreen;

public class StormOfSwordsEnemy extends Enemy {

    private static final float HITBOXWIDTH = 1.f;
    private static final float HITBOXHEIGHT = 2.f;
    private static final float HITBOXHEIGHTOFFSET = -1.6f;
    private static final float HITBOXWIDTHOFFSET = 0.1f;
    public static final int SIZEX = 189;
    public static final int SIZEY= 47;

    public StormOfSwordsEnemy(float health, float speed) {
        super(health, speed, SIZEX / GameScreen.PPM*2.5f, SIZEY / GameScreen.PPM*2.5f, HITBOXWIDTH, HITBOXHEIGHT, CharacterOptions.SwordOfStorms, CharacterAnimations.IdleKneel, 12.f, 8.f, 2.f);
    }

    @Override
    public void updateHitBox() {
        if(getCurDirection() == 1) {
            setHitBoxPosition(getPosX() - HITBOXWIDTH/2 - HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET);
        }
        else setHitBoxPosition(getPosX() - HITBOXWIDTH/2 + HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET);
    }
}
