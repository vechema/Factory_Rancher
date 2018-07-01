package com.jegner.factory.rancher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;
import com.jegner.factory.rancher.screen.ScreenManager;

public class FactoryRancherGame extends Game {

    private GameResources gameResources;
    private ScreenManager screenManager;

    @Override
    public void create() {
        gameResources = new GameResources();
        Gdx.app.log("Game","Screen size: " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());

        screenManager = new ScreenManager(this, gameResources);
        screenManager.setToLoadingScreen();
    }

    @Override
    public void dispose() {
        // Free resources
        gameResources.getAssetManager().dispose();
    }
}
