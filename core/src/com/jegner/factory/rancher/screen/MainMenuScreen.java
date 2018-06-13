package com.jegner.factory.rancher.screen;

import com.jegner.factory.rancher.resource.GameResources;

public class MainMenuScreen extends AbstractScreen{

    public MainMenuScreen(ScreenManager screenManager, GameResources gameResources) {
        super(screenManager, gameResources);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        screenManager.setToGameScreen();
    }

}
