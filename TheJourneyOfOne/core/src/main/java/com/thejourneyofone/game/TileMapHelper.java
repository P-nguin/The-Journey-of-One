package com.thejourneyofone.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileMapHelper {
    private TiledMap tiledMap;
    public TileMapHelper(){

    }

    public TiledMap setupMap()
    {
        tiledMap = new TmxMapLoader().load("levels/map1.tmx");
        return tiledMap;
    }
}
