package com.jegner.factory.rancher;

import com.badlogic.gdx.Game;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;
import com.jegner.factory.rancher.screen.ScreenManager;

public class FactoryRancherGame extends Game {

    private GameResources gameResources;
    private ScreenManager screenManager;

    @Override
    public void create() {
        gameResources = new GameResources();

        screenManager = new ScreenManager(this, gameResources);
        screenManager.setToLoadingScreen();
    }

    @Override
    public void dispose() {
        // Free resources
        gameResources.getAssetManager().dispose();
    }
}
