package com.thejourneyofone.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thejourneyofone.game.Resources;

public class MainCamera {
    public static float PPM = 32;
    private static float LERP = 0.08f;
    private static float lerp;

    public static OrthographicCamera camera;
    public static ScreenViewport viewport;

    public MainCamera() {
        camera = new OrthographicCamera();
        camera.update();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        viewport = new ScreenViewport(camera);
        viewport.setUnitsPerPixel(1/PPM);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        lerp = LERP;
    }

    public static void update(float dt, Player player) {
        if(player.getCurAnimation().equals(Resources.CharacterAnimations.Attack2)) {
            if(player.getCurDirection() == 0) camera.position.set(new Vector2(camera.position.x + (player.getPosX() + 3f - camera.position.x)*lerp*0.9f, camera.position.y + (player.getPosY() - camera.position.y)*lerp*0.9f), 0);
            else camera.position.set(new Vector2(camera.position.x + (player.getPosX() - 3f - camera.position.x)*lerp*0.9f, camera.position.y + (player.getPosY() - camera.position.y)*lerp*0.9f), 0);
        }

        camera.position.set(new Vector2(camera.position.x + (player.getPosX() - camera.position.x)*lerp, camera.position.y + (player.getPosY() - camera.position.y)*lerp), 0);
        camera.update();
    }
}
