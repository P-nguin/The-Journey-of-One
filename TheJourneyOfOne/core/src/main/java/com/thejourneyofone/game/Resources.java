package com.thejourneyofone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class Resources {

    private static TextureAtlas atlas;
    private static HashMap<AnimationOptions, Animation<TextureRegion>> animationMap;
    private static HashMap<TextureOptions, TextureRegion> textureMap;

    public Resources() {}

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        animationMap = new HashMap<>();
        textureMap = new HashMap<>();

        String parentFolder = "sword of storms/";
        TextureRegion[][] alreadyPacked = atlas.findRegion(parentFolder+"SwordOfStormsAttack1").split(134,47);
        animationMap.put(AnimationOptions.SwordOfStormsAttack1, createAnimation(0.1f, alreadyPacked, false));

        alreadyPacked = atlas.findRegion(parentFolder+"SwordOfStormsAttack2").split(134,47);
        animationMap.put(AnimationOptions.SwordOfStormsAttack2, createAnimation(0.1f, alreadyPacked, false));

        alreadyPacked = atlas.findRegion(parentFolder+"SwordOfStormsDamagedAndDeath").split(134,47);
        animationMap.put(AnimationOptions.SwordOfStormsDamaged, createAnimation(0.1f, new TextureRegion[][]{{alreadyPacked[0][0], alreadyPacked[1][0]}}, false));

        alreadyPacked =
                new TextureRegion[][] {{alreadyPacked[2][0], alreadyPacked[3][0], alreadyPacked[4][0], alreadyPacked[5][0], alreadyPacked[6][0],
                                        alreadyPacked[7][0], alreadyPacked[8][0], alreadyPacked[9][0], alreadyPacked[10][0], alreadyPacked[11][0]}};
        animationMap.put(AnimationOptions.SwordOfStormsDeath, createAnimation(0.1f, alreadyPacked, false));

        alreadyPacked = atlas.findRegion(parentFolder+"SwordOfStormsIdle").split(134,47);
        animationMap.put(AnimationOptions.SwordOfStormsIdle, createAnimation(0.1f, alreadyPacked, true));

        alreadyPacked = atlas.findRegion(parentFolder+"SwordOfStormsIdleKneel").split(134,47);
        animationMap.put(AnimationOptions.SwordOfStormsKneel, createAnimation(0.1f, alreadyPacked, false));

        alreadyPacked = atlas.findRegion(parentFolder+"SwordOfStormsRun").split(134,47);
        animationMap.put(AnimationOptions.SwordOfStormsRun, createAnimation(0.1f, alreadyPacked, true));
    }

    public static Animation<TextureRegion> getAnimation(AnimationOptions animation) {
        return animationMap.get(animation);
    }

    private static Animation<TextureRegion> createAnimation(float frameDuration, String fileName, boolean loop) {
        Array<TextureAtlas.AtlasRegion> region = atlas.findRegions(fileName);
        if(loop)
            return new Animation(frameDuration, atlas.findRegions(fileName), Animation.PlayMode.LOOP);
        else
            return new Animation(frameDuration, atlas.findRegions(fileName), Animation.PlayMode.NORMAL);
    }

    private static Animation<TextureRegion> createAnimation(float frameDuration, TextureRegion[][] frames, boolean loop) {
        Array<TextureRegion> region = new Array<>();
        for(TextureRegion[] i : frames) {
            for(TextureRegion j : i) {
                region.add(j);
            }
        }
        if(loop)
            return new Animation<>(frameDuration, region, Animation.PlayMode.LOOP);
        else
            return new Animation<>(frameDuration, region, Animation.PlayMode.NORMAL);
    }

    public static void dispose() {
        atlas.dispose();
    }

    public enum AnimationOptions {
        SwordOfStormsAttack1,
        SwordOfStormsAttack2,
        SwordOfStormsDamaged,
        SwordOfStormsDeath,
        SwordOfStormsIdle,
        SwordOfStormsKneel,
        SwordOfStormsRun
    }

    public enum TextureOptions {
        ShadowOfStorms,
        StormHead,
        SwordOfStorms
    }
}
