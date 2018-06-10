package com.jegner.factory.rancher.screen;

public class LoadingScreen extends AbstractScreen{

    public LoadingScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        screenManager.setToMainMenuScreen();
    }

}
