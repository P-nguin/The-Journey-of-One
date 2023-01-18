package com.thejourneyofone.game.characters;

import com.thejourneyofone.game.Resources;
import com.thejourneyofone.game.Resources.CharacterOptions;
import com.thejourneyofone.game.Resources.CharacterAnimations;
import com.thejourneyofone.game.screens.GameScreen;

public class Player extends Character {

    public static final int SIZEX = 189;
    public static final int SIZEY= 47;

    private boolean prevLeftMove, prevRightMove, prevUpMove, prevDownMove;

    private final float idleBattleMax = 5.0f;
    private float idleBattleTime = 0;

    public Player(float health, float speed) {
        //Facing right, could replace 0 with a static variable that would be housed in the Resources.
        super(health, speed, SIZEX / GameScreen.PPM*2.5f, SIZEY / GameScreen.PPM*2.5f, CharacterOptions.SwordOfStorms, CharacterAnimations.IdleKneel, 0);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void updateAnimation(float dt) {
        super.updateAnimation(dt);
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
    public void dispose() {
        super.dispose();
    }
}
