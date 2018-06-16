package com.jegner.factory.rancher.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jegner.factory.rancher.ashley.component.TiledMapComponent;
import com.jegner.factory.rancher.physics.BodyFactory;
import com.jegner.factory.rancher.physics.BodyFactory.FixtureMaterial;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

import static com.jegner.factory.rancher.resource.GameResourceNames.dirtMapFileName;

public class TiledMapFactory {

    // The singleton instance
    private static TiledMapFactory instance;

    // Useful resources
    private GameAssetManager assetManager;
    private PooledEngine engine;
    private BodyFactory bodyFactory;
    private float scale;

    private TiledMapFactory(GameResources gameResources) {

        this.assetManager = gameResources.getAssetManager();
        this.engine = gameResources.getEngine();
        this.bodyFactory = BodyFactory.getInstance(gameResources);
        this.scale = gameResources.getMetersPerPixel();
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

        // Get bodies
        for (MapObject mapObject : map.getLayers().get("Fence Objects").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
            float rectX = (rect.x + rect.width / 2) * scale;
            float rectY = (rect.y + rect.height / 2) * scale;
            float rectWidth = rect.width * scale;
            float rectHeight = rect.height * scale;
            bodyFactory.makeBoxPolyBody(rectX, rectY, rectWidth,rectHeight, FixtureMaterial.STONE, BodyType.StaticBody);
        }

        // Add Components to Entity
        entity.add(tiledMapComponent);

        // Add Entity to Engine
        engine.addEntity(entity);

        return entity;
    }
}
