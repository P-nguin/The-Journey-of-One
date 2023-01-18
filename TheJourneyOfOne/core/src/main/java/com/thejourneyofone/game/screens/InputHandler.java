package com.thejourneyofone.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.thejourneyofone.game.characters.Player;

public class InputHandler implements InputProcessor {

    Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.A) {
            player.setLeftMove(true);
        }
        if(keycode == Keys.D) {
            player.setRightMove(true);
        }
        if(keycode == Keys.W) {
            player.setUpMove(true);
        }
        if(keycode == Keys.S) {
            player.setDownMove(true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.A) {
            player.setLeftMove(false);
        }
        if(keycode == Keys.D) {
            player.setRightMove(false);
        }
        if(keycode == Keys.W) {
            player.setUpMove(false);
        }
        if(keycode == Keys.S) {
            player.setDownMove(false);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Buttons.LEFT) {
            player.attack(1);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
