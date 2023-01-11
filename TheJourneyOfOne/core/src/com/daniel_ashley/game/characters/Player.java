package com.daniel_ashley.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {
    public Player(float health, float speed) {
        super(new Texture(Gdx.files.internal("Textures/Idle-0.png")), health, speed);
    }
}
