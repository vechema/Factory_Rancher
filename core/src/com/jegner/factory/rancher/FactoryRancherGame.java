package com.jegner.factory.rancher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.screen.ScreenManager;

import static com.jegner.factory.rancher.resource.GameResources.dirtMapFileName;

public class FactoryRancherGame extends Game {

    // Screen Manager
    private ScreenManager screenManager;

    // Asset Manager
    private GameAssetManager assetManager;

    @Override
    public void create() {
        assetManager = new GameAssetManager();

        screenManager = new ScreenManager(this);
        screenManager.setToLoadingScreen();
    }

    /*@Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the camera and render
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }*/

    @Override
    public void dispose() {
        // Free resources
        assetManager.dispose();
    }

    public GameAssetManager getAssetManager() {
        return assetManager;
    }
}
