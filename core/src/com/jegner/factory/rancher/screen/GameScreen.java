package com.jegner.factory.rancher.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jegner.factory.rancher.ashley.entity.TiledMapFactory;
import com.jegner.factory.rancher.ashley.system.BackGroundRenderingSystem;

public class GameScreen extends AbstractScreen{

    // Sprite Batch
    private SpriteBatch spriteBatch;

    // Map Renderer
    private OrthogonalTiledMapRenderer renderer;

    // Engine
    private PooledEngine engine;

    // Entity Factories
    private TiledMapFactory tiledMapFactory;

    public GameScreen(ScreenManager screenManager) {
        super(screenManager);

        // Asset loading
        assetManager.queueMapLoading();
        assetManager.finishLoading();

        // Sprite Batch
        spriteBatch = new SpriteBatch();

        // Renderer
        renderer = new OrthogonalTiledMapRenderer(new TiledMap(), 1, spriteBatch);

        // Engine
        engine = new PooledEngine();

        // Entity Factories
        tiledMapFactory = new TiledMapFactory(assetManager, engine);

        // Add systems to engine
        engine.addSystem(new BackGroundRenderingSystem(spriteBatch));

        // Add entities to engine
        tiledMapFactory.createDirtMapEntity();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        engine.update(delta);
    }

}
