package com.thejourneyofone.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thejourneyofone.game.Main;
import com.thejourneyofone.game.characters.Player;

public class GameScreen implements Screen{

    public static final int WORLDX = 100;
    public static final int WORLDY = 100;

    private Main game;
    private Player player;
    private InputHandler inputHandler;
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(Main game) {
        this.game = game;
        float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

        player = new Player(5,15);
        player.setSize(3*2.85f*player.getSize().y/player.getSize().x, 3*player.getSize().y/player.getSize().x);

        inputHandler = new InputHandler(player);

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLDX * aspectRatio, WORLDY * aspectRatio, camera);
        viewport.apply();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(Gdx.graphics.getDeltaTime());

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //camera.position.set(player.getPosition(), 1);
        camera.update();

        player.draw(game.batch);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
