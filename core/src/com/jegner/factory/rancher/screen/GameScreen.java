package com.jegner.factory.rancher.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jegner.factory.rancher.ashley.entity.PlayerFactory;
import com.jegner.factory.rancher.ashley.entity.TiledMapFactory;
import com.jegner.factory.rancher.ashley.system.BackGroundRenderingSystem;
import com.jegner.factory.rancher.ashley.system.PhysicsDebugSystem;
import com.jegner.factory.rancher.ashley.system.PhysicsSystem;
import com.jegner.factory.rancher.resource.GameResources;

public class GameScreen extends AbstractScreen{

    // Useful resources
    private PooledEngine engine;
    private TiledMapFactory tiledMapFactory;
    private PlayerFactory playerFactory;

    public GameScreen(ScreenManager screenManager, GameResources gameResources) {
        super(screenManager, gameResources);

        // Asset loading
        assetManager.queueMapLoading();
        assetManager.finishLoading();

        engine = gameResources.getEngine();

        // Entity Factories
        tiledMapFactory = TiledMapFactory.getInstance(gameResources);
        playerFactory = PlayerFactory.getInstance(gameResources);

        // Add systems to engine
        engine.addSystem(new BackGroundRenderingSystem(gameResources));
        engine.addSystem(new PhysicsDebugSystem(gameResources));
        engine.addSystem(new PhysicsSystem(gameResources));

        // Add entities to engine
        tiledMapFactory.createDirtMapEntity();
        playerFactory.createPlayerEntity();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        engine.update(delta);
    }

}
