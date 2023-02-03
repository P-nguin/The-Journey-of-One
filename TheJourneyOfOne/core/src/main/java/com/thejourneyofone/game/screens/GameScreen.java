package com.thejourneyofone.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.*;
import com.thejourneyofone.game.GameManager;
import com.thejourneyofone.game.Main;
import com.thejourneyofone.game.TileMapHelper;
import com.thejourneyofone.game.characters.MainCamera;
import com.thejourneyofone.game.characters.Player;
import com.thejourneyofone.game.characters.StormOfSwordsEnemy;

public class GameScreen implements Screen{
    public static final float PPM = 32;

    private Main game;
    private MainCamera mainCamera;
    private InputHandler inputHandler;

    private Player player;

    public static ShapeRenderer testing;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    public GameScreen(Main game) {
        this.game = game;

        mainCamera = new MainCamera();

        this.tileMapHelper = new TileMapHelper();
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tileMapHelper.getTiledMap(), 1/PPM);

        player = new Player(5,12);
        player.setPosition(10,10);
        inputHandler = new InputHandler(player);

        GameManager.init(player, tileMapHelper.getTiledMap());
        GameManager.addEnemy(new StormOfSwordsEnemy(5,3));

        testing = new ShapeRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        testing.setProjectionMatrix(MainCamera.camera.combined);
        testing.begin(ShapeRenderer.ShapeType.Line);
        testing.setColor(Color.RED);

        player.update(delta);
        MainCamera.update(delta, player);
        GameManager.update(delta);

        game.batch.setProjectionMatrix(MainCamera.camera.combined);
        game.batch.begin();

        orthogonalTiledMapRenderer.setView(MainCamera.camera);
        orthogonalTiledMapRenderer.render();

        player.draw(game.batch);
        GameManager.drawEnemies(game.batch);
        game.batch.end();

        testing.rect(player.hitBox.x, player.hitBox.y, player.hitBox.getWidth(), player.hitBox.getHeight());
        testing.setColor(Color.BLUE);

        GameManager.testingMap();

        testing.end();
    }

    @Override
    public void resize(int width, int height) {
        MainCamera.viewport.update(width, height);
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
