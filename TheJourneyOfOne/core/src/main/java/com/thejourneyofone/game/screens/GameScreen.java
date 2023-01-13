package com.thejourneyofone.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.thejourneyofone.game.Main;
import com.thejourneyofone.game.characters.Player;

public class GameScreen implements Screen{

    private Main game;
    private Player player;
    private InputHandler inputHandler;

    public GameScreen(Main game) {
        this.game = game;
        player = new Player(5,5);
        inputHandler = new InputHandler(player);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(Gdx.graphics.getDeltaTime());

        game.batch.begin();

        player.draw(game.batch);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
