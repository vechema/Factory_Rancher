package com.jegner.factory.rancher.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import static com.jegner.factory.rancher.resource.GameResourceNames.dirtMapFileName;

public class GameAssetManager {
    private final AssetManager manager = new AssetManager();

    public void queueMapLoading() {
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(dirtMapFileName, TiledMap.class);
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public <T> T get(String fileName, Class<T> type) {
        return manager.get(fileName, type);
    }

    public void dispose() {
        manager.dispose();
    }
}
