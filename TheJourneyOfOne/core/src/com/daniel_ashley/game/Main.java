package com.daniel_ashley.game;

import com.badlogic.gdx.Game;
import com.daniel_ashley.game.screens.GameScreen;

public class Main extends Game {
	
	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
