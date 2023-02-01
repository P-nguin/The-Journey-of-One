package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.thejourneyofone.game.GameManager;
import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.Resources.CharacterAnimations;
import com.thejourneyofone.game.screens.GameScreen;

public class Player extends Character {

    public static final int SIZEX = 189;
    public static final int SIZEY= 47;

    private static final float HITBOXWIDTH = 1.f;
    private static final float HITBOXHEIGHT = 2.f;
    private static final float HITBOXHEIGHTOFFSET = -1.6f;
    private static final float HITBOXWIDTHOFFSET = 0.1f;
    private static final float ATTACKHITBOX1WIDTH = 3.9f;
    private static final float ATTACKHITBOX1HEIGHT = 1.6f;
    private static final float ATTACKHITBOX1HEIGHTOFFSET = -1.4f; //FUTURE MAKE THE SECOND SWIPE SMALLER BUT HEY WHO REALLY CARES?
    private static final float ATTACKHITBOX1WIDTHOFFSET = 1.5f;

    private static final float damage = 1.f;


    private boolean prevLeftMove, prevRightMove, prevUpMove, prevDownMove;

    private final float idleBattleMax = 5.0f;
    private float idleBattleTime = 0;

    public Player(float health, float speed) {
        //Facing right, could replace 0 with a static variable that would be housed in the Resources.
        super(health, speed, damage, SIZEX / GameScreen.PPM*2.5f, SIZEY / GameScreen.PPM*2.5f, HITBOXWIDTH, HITBOXHEIGHT, CharacterOptions.SwordOfStorms, CharacterAnimations.IdleKneel);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void updateAnimation(float dt) {
        Animation<TextureRegion> ani = Resources.getAnimation(getCharacterType(), getCurAnimation(), getCurDirection());
        if (ani.isAnimationFinished(getTimeCnt()) && ani.getPlayMode() != Animation.PlayMode.LOOP) {
            setAnimation(CharacterAnimations.Idle);
            setTimeCnt(0);
        } else if (getCurAnimation() == CharacterAnimations.Attack1) {
            int curFrame = ani.getKeyFrameIndex(getTimeCnt());
            if(curFrame == 1 || curFrame == 4) {
                attack();
            }
            checkKeys();
        }

        setTexture(Resources.getAnimation(getCharacterType(), getCurAnimation(), getCurDirection()).getKeyFrame(getTimeCnt()));
    }

    @Override
    public void updateHitBox() {
        if(getCurDirection() == 1) {
            setHitBoxPosition(getPosX() - HITBOXWIDTH/2 - HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET);
        }
        else setHitBoxPosition(getPosX() - HITBOXWIDTH/2 + HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET);
    }

    @Override
    public void updateMove(float dt) {
        if(getCurAnimation() == CharacterAnimations.Attack1) return;
        super.updateMove(dt);
    }

    private void checkKeys() {
        setDownMove(prevDownMove);
        setRightMove(prevRightMove);
        setUpMove(prevUpMove);
        setLeftMove(prevLeftMove);
    }

    private void attack() {
        if(getCurDirection() == 1) {
            GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX1WIDTH/2 - ATTACKHITBOX1WIDTHOFFSET, getPosY() + ATTACKHITBOX1HEIGHTOFFSET, ATTACKHITBOX1WIDTH, ATTACKHITBOX1HEIGHT), true);
        }
        else GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX1WIDTH/2 + ATTACKHITBOX1WIDTHOFFSET, getPosY() + ATTACKHITBOX1HEIGHTOFFSET, ATTACKHITBOX1WIDTH, ATTACKHITBOX1HEIGHT), true);
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
    public void dispose() {
        super.dispose();
    }
}
