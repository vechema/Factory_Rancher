package com.jegner.factory.rancher.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.jegner.factory.rancher.ashley.component.TiledMapComponent;
import com.jegner.factory.rancher.resource.GameAssetManager;

import static com.jegner.factory.rancher.resource.GameResources.dirtMapFileName;

public class TiledMapFactory {

    // Asset Manager
    private GameAssetManager assetManager;

    // Engine
    private PooledEngine engine;

    public TiledMapFactory(GameAssetManager assetManager, PooledEngine engine) {

        this.assetManager = assetManager;
        this.engine = engine;
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
