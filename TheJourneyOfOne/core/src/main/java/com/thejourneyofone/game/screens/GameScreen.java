package com.thejourneyofone.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.*;
import com.thejourneyofone.game.GameManager;
import com.thejourneyofone.game.Main;
import com.thejourneyofone.game.TileMapHelper;
import com.thejourneyofone.game.characters.Player;
import com.thejourneyofone.game.characters.StormOfSwordsEnemy;

public class GameScreen implements Screen{
    public static final float PPM = 32;

    private Main game;
    private InputHandler inputHandler;
    private OrthographicCamera gameCamera;
    private ScreenViewport gamePort;

    private Player player;

    Texture texture;

    public static ShapeRenderer testing;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    public GameScreen(Main game) {
        this.game = game;

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gamePort = new ScreenViewport(gameCamera);
        gamePort.setUnitsPerPixel(1/PPM);
        gamePort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        player = new Player(5,12);
        inputHandler = new InputHandler(player);

        GameManager.init(player);
        GameManager.addEnemy(new StormOfSwordsEnemy(5,3));

        texture = new Texture("libgdx.png");

        testing = new ShapeRenderer();

        this.tileMapHelper = new TileMapHelper();
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tileMapHelper.setupMap(), 1/PPM);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        testing.setProjectionMatrix(gameCamera.combined);
        testing.begin(ShapeRenderer.ShapeType.Line);
        testing.setColor(Color.RED);

        player.update(delta);
        gameCamera.position.set(player.getPosX(), player.getPosY(), 0);
        gameCamera.update();
        GameManager.update(delta);

        game.batch.setProjectionMatrix(gameCamera.combined);
        orthogonalTiledMapRenderer.setView(gameCamera);
        game.batch.begin();
        game.batch.draw(texture, 0, 0);

        player.draw(game.batch);
        GameManager.drawEnemies(game.batch);
        game.batch.end();

        testing.rect(player.hitBox.x, player.hitBox.y, player.hitBox.getWidth(), player.hitBox.getHeight());

        testing.setColor(Color.BLUE);
        //testing.rect(enemy.hitBox.x, enemy.hitBox.y, enemy.hitBox.getWidth(), enemy.hitBox.getHeight());

        testing.rect(player.getPosX() - 1f/2f - 0.1f, player.getPosY() + -1.4f, 6f, 1.6f);

        /*for(int i = 0; i < GameManager.testing().size(); i++) {
            testing.rect(GameManager.testing().get(i).x, GameManager.testing().get(i).y, GameManager.testing().get(i).getWidth(), GameManager.testing().get(i).height);
            GameManager.testing().remove(i); i--;
        } */
        testing.end();

        orthogonalTiledMapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
