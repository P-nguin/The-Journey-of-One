package com.thejourneyofone.game.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private static final float PLAYERWIDTH = 189;
    private static final float PLAYERHEIGHT = 47;

    private static final float HITBOXWIDTH = 1.f;
    private static final float HITBOXHEIGHT = 2.f;
    private static final float HITBOXHEIGHTOFFSET = -1.6f;
    private static final float HITBOXWIDTHOFFSET = -0.2f;
    private static final float ATTACKHITBOX1WIDTH = 3.9f;
    private static final float ATTACKHITBOX1HEIGHT = 1.6f;
    private static final float ATTACKHITBOX1HEIGHTOFFSET = -1.4f; //FUTURE MAKE THE SECOND SWIPE SMALLER BUT HEY WHO REALLY CARES?
    private static final float ATTACKHITBOX1WIDTHOFFSET = 1.5f;
    private static final float ATTACKHITBOX2WIDTH = 6.4f;
    private static final float ATTACKHITBOX2HEIGHT = 1.6f;
    private static final float ATTACKHITBOX2HEIGHTOFFSET = -1.4f; //FUTURE MAKE THE SECOND SWIPE SMALLER BUT HEY WHO REALLY CARES?
    private static final float ATTACKHITBOX2WIDTHOFFSET = -0.6f;

    private static final float ATTACK2MOVE = 3.5f;

    private static final float DAMAGE = 1f;

    private boolean isBlocking;
    private boolean rightMove;
    private boolean leftMove;
    private boolean upMove;
    private boolean downMove;
    private boolean prevLeftMove, prevRightMove, prevUpMove, prevDownMove;

    private int hasAttacked;

    private final float idleBattleMax = 5.0f;
    private float idleBattleTime = 0;

    @Override
    public void draw(SpriteBatch batch) { //TESTING PURPOSES
        super.draw(batch);
        Rectangle box = new Rectangle(getPosX() - ATTACKHITBOX2WIDTHOFFSET - ATTACKHITBOX2WIDTH, getPosY() + ATTACKHITBOX2HEIGHTOFFSET, ATTACKHITBOX2WIDTH, ATTACKHITBOX2HEIGHT);
        GameScreen.testing.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        box = new Rectangle(getPosX() + ATTACKHITBOX2WIDTHOFFSET, getPosY() + ATTACKHITBOX2HEIGHTOFFSET, ATTACKHITBOX2WIDTH, ATTACKHITBOX2HEIGHT);
        GameScreen.testing.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        box = new Rectangle(getPosX() + 2.9f + HITBOXWIDTHOFFSET, getPosY() + HITBOXHEIGHTOFFSET, HITBOXWIDTH, HITBOXHEIGHT);
        GameScreen.testing.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        box = new Rectangle(getPosX() - 2.9f - HITBOXWIDTHOFFSET*2, getPosY() + HITBOXHEIGHTOFFSET, HITBOXWIDTH, HITBOXHEIGHT);
        GameScreen.testing.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    public Player(float health, float speed) {
        //Facing right, could replace 0 with a static variable that would be housed in the Resources.
        super(health, speed, DAMAGE, PLAYERWIDTH / GameScreen.PPM, PLAYERHEIGHT / GameScreen.PPM, HITBOXWIDTH, HITBOXHEIGHT, CharacterOptions.SwordOfStorms, CharacterAnimations.IdleKneel);
        hasAttacked = -1;
        isBlocking = true;
    }

    @Override
    public void update(float dt) {
        setTimeCnt(getTimeCnt() + dt);
        updateMove(dt);
        updateHitBox();
        updateAnimation(dt);
    }

    @Override
    public void updateAnimation(float dt) {
        Animation<TextureRegion> ani = Resources.getAnimation(getCharacterType(), getCurAnimation(), getCurDirection());
        if (ani.isAnimationFinished(getTimeCnt()) && ani.getPlayMode() != Animation.PlayMode.LOOP) {
            boolean wasAttack2 = getCurAnimation().equals(CharacterAnimations.Attack2);
            setAnimation(CharacterAnimations.Idle);
            setTimeCnt(0);

            //Set correct position
            if(wasAttack2) {
                hasAttacked = -1;
                if(getCurDirection() == 0) {
                    move(ATTACK2MOVE, 0);
                }
                else move(-ATTACK2MOVE, 0);
            }
        } else if (getCurAnimation().equals(CharacterAnimations.Attack1)) {
            int curFrame = ani.getKeyFrameIndex(getTimeCnt());
            if((curFrame == 1 || curFrame == 4) && hasAttacked != curFrame) {
                attack1();
                hasAttacked = curFrame;
            }
        } else if(getCurAnimation().equals(CharacterAnimations.Attack2)) {
            int curFrame = ani.getKeyFrameIndex(getTimeCnt());
            if(curFrame == 5 && hasAttacked != curFrame) {
                attack2();
                hasAttacked = curFrame;
            }
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
    public void attack(int type) {
        if(type == 1 && !getCurAnimation().equals(CharacterAnimations.Attack2)) {
            setAnimation(CharacterAnimations.Attack1);
        }
        else if(type == 2 && !getCurAnimation().equals(CharacterAnimations.Attack1)) {
            setAnimation(CharacterAnimations.Attack2);
        }
    }

    private void attack1() {
        if(getCurDirection() == 1) {
            GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX1WIDTH/2 - ATTACKHITBOX1WIDTHOFFSET, getPosY() + ATTACKHITBOX1HEIGHTOFFSET, ATTACKHITBOX1WIDTH, ATTACKHITBOX1HEIGHT),
                    DAMAGE, true);
        }
        else {
            GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX1WIDTH/2 + ATTACKHITBOX1WIDTHOFFSET, getPosY() + ATTACKHITBOX1HEIGHTOFFSET, ATTACKHITBOX1WIDTH, ATTACKHITBOX1HEIGHT),
                    DAMAGE, true);
        }
    }

    private void attack2() {
        if(getCurDirection() == 1) {
            GameManager.addAttack(new Rectangle(getPosX() - ATTACKHITBOX2WIDTHOFFSET - ATTACKHITBOX2WIDTH, getPosY() + ATTACKHITBOX2HEIGHTOFFSET, ATTACKHITBOX2WIDTH, ATTACKHITBOX2HEIGHT),
                    DAMAGE*3, true);
        }
        else {
            GameManager.addAttack(new Rectangle(getPosX() + ATTACKHITBOX2WIDTHOFFSET, getPosY() + ATTACKHITBOX2HEIGHTOFFSET, ATTACKHITBOX2WIDTH, ATTACKHITBOX2HEIGHT),
                    DAMAGE*3, true);
        }
    }

    public void setBlock(boolean t) {
        isBlocking = t;
        if(isBlocking) setAnimation(CharacterAnimations.Block);
        else setAnimation(CharacterAnimations.Idle);
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void updateMove(float dt) { // MAKE THE CAN MOVE TWO POINTS INSTEAD, LOWER THE PLAYER POSITION, also fix dash bug
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

        move(x,y);
    }

    @Override
    public void move(float x, float y) {
        if(getCurAnimation().equals(CharacterAnimations.Attack1) || getCurAnimation().equals(CharacterAnimations.Attack2) ||
                getCurAnimation().equals(CharacterAnimations.Block))
        {
            return;
        }
        else if(x == 0 && y == 0) {
            setAnimation(CharacterAnimations.Idle);
            return;
        }

        setAnimation(CharacterAnimations.Run);

        if(GameManager.canMove(getPosX() + x, getPosY())) {
            getSprite().translate(x,0);
        }
        if(GameManager.canMove(getPosX(), getPosY() + y)) {
            getSprite().translate(0,y);
        }

        if(x < 0) updateDirection(1);
        else if(x > 0) updateDirection(0);
    }

    public void setRightMove(boolean t) {
        rightMove = t;
    }

    public void setLeftMove(boolean t) {
        leftMove = t;
    }

    public void setUpMove(boolean t) {
        upMove = t;
    }

    public void setDownMove(boolean t) {
        downMove = t;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
