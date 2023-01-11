package com.daniel_ashley.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.daniel_ashley.game.Main;
import com.daniel_ashley.game.characters.*;

public class GameScreen implements Screen, InputProcessor {

    SpriteBatch batch;
    final Main game;
    OrthographicCamera camera;

    Player player;

    public GameScreen(final Main game) {
        this.game = game;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        player = new Player(1f, 1f);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        playerMovement();

        ScreenUtils.clear(0, 0, 0.2f, 1);
        batch.begin();

        player.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    private void playerMovement() {
        Vector2 move = new Vector2(0,0);
        if(Gdx.input.isKeyPressed(Input.Keys.W)) move.y += 1;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) move.y -= 1;
        if(Gdx.input.isKeyPressed(Input.Keys.A)) move.x -= 1;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) move.x += 1;
        player.move(move);
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
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
