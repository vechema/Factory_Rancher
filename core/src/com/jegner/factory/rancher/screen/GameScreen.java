package com.jegner.factory.rancher.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.jegner.factory.rancher.ashley.entity.PlayerFactory;
import com.jegner.factory.rancher.ashley.entity.TiledMapFactory;
import com.jegner.factory.rancher.ashley.system.BackGroundRenderingSystem;
import com.jegner.factory.rancher.ashley.system.PhysicsDebugSystem;
import com.jegner.factory.rancher.ashley.system.PhysicsSystem;
import com.jegner.factory.rancher.ashley.system.PlayerControlSystem;
import com.jegner.factory.rancher.ashley.system.RenderingSystem;
import com.jegner.factory.rancher.controller.KeyboardController;
import com.jegner.factory.rancher.resource.GameResources;

public class GameScreen extends AbstractScreen{

    // Useful resources
    private PooledEngine engine;
    private TiledMapFactory tiledMapFactory;
    private PlayerFactory playerFactory;
    private KeyboardController keyboardController;

    public GameScreen(ScreenManager screenManager, GameResources gameResources) {
        super(screenManager, gameResources);
        this.keyboardController = gameResources.getKeyboardController();

        // Asset loading
        assetManager.queueMapLoading();
        assetManager.queueCharacterLoading();
        assetManager.finishLoading();

        engine = gameResources.getEngine();

        // Entity Factories
        tiledMapFactory = TiledMapFactory.getInstance(gameResources);
        playerFactory = PlayerFactory.getInstance(gameResources);

        // Add systems to engine
        engine.addSystem(new BackGroundRenderingSystem(gameResources));
        engine.addSystem(new PhysicsDebugSystem(gameResources));
        engine.addSystem(new PhysicsSystem(gameResources));
        engine.addSystem(new RenderingSystem(gameResources));
        engine.addSystem(new PlayerControlSystem(gameResources));

        // Add entities to engine
        tiledMapFactory.createDirtMapEntity();
        playerFactory.createPlayerEntity();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(keyboardController);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        engine.update(delta);
    }

}
