package com.jegner.factory.rancher.resource;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jegner.factory.rancher.FactoryRancherGame;
import com.jegner.factory.rancher.screen.ScreenManager;

/**
 * Used to hold things that the game is going to have ONE of.
 * Can just pass this around, instead of each little thing
 */
public class GameResources {

    // Physics
    private World world;

    // ECS
    private PooledEngine engine;

    // Resources
    private GameAssetManager assetManager;
    private GamePreferences preferences;

    // Camera
    private OrthographicCamera camera;
    private Viewport viewport;

    // Drawing
    private SpriteBatch spriteBatch;

    public GameResources() {
        Gdx.app.log("Game Resources", "start constructor");

        this.world = new World(new Vector2(0,0),true);

        this.engine = new PooledEngine();

        this.assetManager = new GameAssetManager();
        this.preferences = new GamePreferences();

        this.camera = new OrthographicCamera();
        this.viewport = new ScreenViewport(this.camera);

        this.spriteBatch = new SpriteBatch();
        Gdx.app.log("Game Resources", "end constructor");
    }

    public World getWorld() {
        return world;
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public GameAssetManager getAssetManager() {
        return assetManager;
    }

    public GamePreferences getPreferences() {
        return preferences;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
