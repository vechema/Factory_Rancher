package com.jegner.factory.rancher.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.jegner.factory.rancher.ashley.component.TiledMapComponent;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

import static com.jegner.factory.rancher.resource.GameResourceNames.dirtMapFileName;

public class TiledMapFactory {

    // The singleton instance
    private static TiledMapFactory instance;

    // Useful resources
    private GameAssetManager assetManager;
    private PooledEngine engine;

    private TiledMapFactory(GameResources gameResources) {

        this.assetManager = gameResources.getAssetManager();
        this.engine = gameResources.getEngine();
    }

    public static TiledMapFactory getInstance(GameResources gameResources) {
        if(instance == null) {
            instance = new TiledMapFactory(gameResources);
        }
        return instance;
    }

    public Entity createDirtMapEntity() {

        // Create entity
        Entity entity = engine.createEntity();

        // Create components
        TiledMapComponent tiledMapComponent = engine.createComponent(TiledMapComponent.class);
        TiledMap map = assetManager.get(dirtMapFileName, TiledMap.class);
        tiledMapComponent.setMap(map);

        // Add Components to Entity
        entity.add(tiledMapComponent);

        // Add Entity to Engine
        engine.addEntity(entity);

        return entity;
    }
}
