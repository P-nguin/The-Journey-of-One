package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.thejourneyofone.game.GameManager;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.CharacterAnimations;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.screens.GameScreen;

public class StormOfSwordsEnemy extends Enemy {

    private static final float HITBOXWIDTH = 1.f;
    private static final float HITBOXHEIGHT = 2.f;
    private static final float HITBOXHEIGHTOFFSET = -1.6f;
    private static final float HITBOXWIDTHOFFSET = 0.1f;
    private static final float ATTACKHITBOX1WIDTH = 3.9f;
    private static final float ATTACKHITBOX1HEIGHT = 1.6f;
    private static final float ATTACKHITBOX1HEIGHTOFFSET = -1.4f; //FUTURE MAKE THE SECOND SWIPE SMALLER BUT HEY WHO REALLY CARES?
    private static final float ATTACKHITBOX1WIDTHOFFSET = 1.5f;
    public static final int SIZEX = 189;
    public static final int SIZEY= 47;

    private static final float damage = 1.0f;

    private int hasAttacked;

    public StormOfSwordsEnemy(float health, float speed) {
        super(health, speed, damage, SIZEX / GameScreen.PPM*2.5f, SIZEY / GameScreen.PPM*2.5f, HITBOXWIDTH, HITBOXHEIGHT, CharacterOptions.SwordOfStorms, CharacterAnimations.IdleKneel, 12.f, 8.f, 2.f);
        hasAttacked = -1;
    }

    @Override
    public void updateHitBox() {
        if(getCurDirection() == 1) {
            setHitBoxPosition(getPosX() - HITBOXWIDTH/2 - HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET);
        }
        else setHitBoxPosition(getPosX() - HITBOXWIDTH/2 + HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET);
    }

    @Override
    public void updateAnimation(float dt) {
        Animation<TextureRegion> ani = Resources.getAnimation(getCharacterType(), getCurAnimation(), getCurDirection());
        if(ani.isAnimationFinished(getTimeCnt()) && ani.getPlayMode() != Animation.PlayMode.LOOP) {
            setAnimation(CharacterAnimations.Idle);
            setTimeCnt(0);
        } else if (getCurAnimation() == CharacterAnimations.Attack1) {
            int curFrame = ani.getKeyFrameIndex(getTimeCnt());
            if((curFrame == 1 || curFrame == 4) && hasAttacked != curFrame) {
                attack();
                hasAttacked = curFrame;
            }
        }

        setTextureRegion(Resources.getAnimation(getCharacterType(), getCurAnimation(), getCurDirection()).getKeyFrame(getTimeCnt()));
    }

    private void attack() {
        if(getCurDirection() == 1) {
            GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX1WIDTH/2 - ATTACKHITBOX1WIDTHOFFSET, getPosY() + ATTACKHITBOX1HEIGHTOFFSET, ATTACKHITBOX1WIDTH, ATTACKHITBOX1HEIGHT),
                    damage, false);
        }
        else GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX1WIDTH/2 + ATTACKHITBOX1WIDTHOFFSET, getPosY() + ATTACKHITBOX1HEIGHTOFFSET, ATTACKHITBOX1WIDTH, ATTACKHITBOX1HEIGHT),
                    damage, false);
    }
}
