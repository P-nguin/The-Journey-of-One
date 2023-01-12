package com.thejourneyofone.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thejourneyofone.game.characters.Player;
import com.thejourneyofone.game.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture image;
	private GameScreen gameScreen;

	Player player;

	@Override
	public void create() {
		batch = new SpriteBatch();
		image = new Texture("libgdx.png");
		Resources.load();

		player = new Player(3,2);
		//gameScreen = new GameScreen(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(image, 140, 210);
		player.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		image.dispose();
		Resources.dispose();
	}
}