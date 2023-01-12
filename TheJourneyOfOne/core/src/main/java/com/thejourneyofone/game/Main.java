package com.thejourneyofone.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thejourneyofone.game.characters.Player;
import com.thejourneyofone.game.screens.GameScreen;
import com.thejourneyofone.game.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

	public static final int WIDTH = 1280;
	public static final int HEIGHT= 720;
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Resources.load();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		Resources.dispose();
	}
}