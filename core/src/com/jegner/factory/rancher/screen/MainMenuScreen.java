package com.jegner.factory.rancher.screen;


import com.badlogic.gdx.Gdx;

public class MainMenuScreen extends AbstractScreen{

    public MainMenuScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        screenManager.setToGameScreen();
    }

}
