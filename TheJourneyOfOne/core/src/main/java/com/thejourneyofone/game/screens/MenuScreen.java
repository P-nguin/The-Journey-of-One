package com.thejourneyofone.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.thejourneyofone.game.Main;

public class MenuScreen implements Screen {
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 120;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 120;
    private static final int EXIT_BUTTON_Y= 125;
    private static final int PLAY_BUTTON_Y= 350;
    Main game;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    Texture background;

    public MenuScreen(Main game){
        this.game = game;
        playButtonActive = new Texture("textures/UI/play_button_active.png");
        playButtonInactive = new Texture("textures/UI/play_button_inactive.png");
        exitButtonActive = new Texture("textures/UI/exit_button_active.png");
        exitButtonInactive = new Texture("textures/UI/exit_button_inactive.png");

        //background = new Texture("textures/UI/background.jpg");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        //game.batch.draw(background,0,0);

        int x = Main.WIDTH / 2 - EXIT_BUTTON_WIDTH/2;
        if(Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x &&  Main.HEIGHT - Gdx.input.getY()< EXIT_BUTTON_Y  + EXIT_BUTTON_HEIGHT && Main.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y,EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }else{
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y,EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        int x2 = Main.WIDTH / 2 - PLAY_BUTTON_WIDTH/2;
        if(Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x &&  Main.HEIGHT - Gdx.input.getY()< PLAY_BUTTON_Y  + PLAY_BUTTON_HEIGHT && Main.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive, x2, PLAY_BUTTON_Y,PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                game.setScreen(new GameScreen(game));
            }
        }else{
            game.batch.draw(playButtonInactive, x2, PLAY_BUTTON_Y,PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }




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
