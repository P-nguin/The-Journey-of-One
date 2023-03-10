package com.thejourneyofone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.thejourneyofone.game.characters.Player;

import java.util.HashMap;

public class Resources {

    private static TextureAtlas atlas;
    //0 is right, 1 is left
    private static HashMap<CharacterOptions, HashMap<CharacterAnimations, Animation[]>> animationMap;
    private static HashMap<String, TextureRegion> textureMap;

    public Resources() {}

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        animationMap = new HashMap<>();
        textureMap = new HashMap<>();
                                    //idle,     kneel,  run,    attack1,    attack2,    block       damage,     death
        float[][] animationTimes = {{.2f,       .1f,    .1f,    .1f,        0.095f,     .1f,        .13f,        .11f},  // storms of swords
                                    {.2f,       .1f,    .1f,    .1f,        .12f,       .1f,        .1f,        .1f},   // something
                                    {.2f,       .1f,    .1f,    .1f,        .12f,       .1f,        .1f,        .1f}};  // something

                                   //idle,  kneel,  run,    attack1,    attack2,    block       damage,     death
        boolean[] animationLoop = {true,    true,   true,   false,      false,      true,       false,      false};

                                    //storm of swords
        int[][] animationCharactersSize = {{Player.SIZEX, Player.SIZEY},{}}; int cnt1 = 0;

        //StormsOfSwords, StormHead, ShadowOfStorms
        for(CharacterOptions i : CharacterOptions.values()) {
            animationMap.put(i, new HashMap<>());

            String parentFolder = i.name(); int cnt2 = 0;
            for(CharacterAnimations j : CharacterAnimations.values()) {
                System.out.println(parentFolder+"/"+i.name()+j.name()+"Left");
                Animation uno = createAnimation(animationTimes[cnt1][cnt2], atlas.findRegion(parentFolder+"/"+i.name()+j.name()+"Left").split(animationCharactersSize[cnt1][0], animationCharactersSize[cnt1][1]), animationLoop[cnt2]);
                System.out.println(parentFolder+"/"+i.name()+j.name()+"Right");
                Animation dos = createAnimation(animationTimes[cnt1][cnt2], atlas.findRegion(parentFolder+"/"+i.name()+j.name()+"Right").split(animationCharactersSize[cnt1][0], animationCharactersSize[cnt1][1]), animationLoop[cnt2]);
                animationMap.get(i).put(j, new Animation[] {dos, uno});
                cnt2++;
            }
            cnt1++;
            break; // HERE only because I don't have the other textures setup
        }
    }

    public static Animation<TextureRegion> getAnimation(CharacterOptions character, CharacterAnimations animation, int dir) {
        return animationMap.get(character).get(animation)[dir];
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

    public enum CharacterOptions {
        SwordOfStorms,
        StormHead,
        ShadowOfStorms
    }

    public enum CharacterAnimations {
        Idle,
        IdleKneel,
        Run,
        Attack1,
        Attack2,
        Block,
        Damaged,
        Death
    }
}
