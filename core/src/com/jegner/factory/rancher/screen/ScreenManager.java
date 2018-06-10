package com.jegner.factory.rancher.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.jegner.factory.rancher.FactoryRancherGame;
import com.jegner.factory.rancher.resource.GameAssetManager;

public class ScreenManager {

    // Game
    private FactoryRancherGame game;

    // Screens
    private LoadingScreen loadingScreen;
    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;
    private PauseScreen pauseScreen;

    public ScreenManager(FactoryRancherGame game) {
        this.game = game;

        loadingScreen = new LoadingScreen(this);
        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        pauseScreen = new PauseScreen(this);
    }

    public void setToLoadingScreen() {
        game.setScreen(loadingScreen);
    }

    public void setToGameScreen() {
        game.setScreen(gameScreen);
    }

    public void setToMainMenuScreen() {
        game.setScreen(mainMenuScreen);
    }

    public void setToPauseScreen() {
        game.setScreen(pauseScreen);
    }

    public GameAssetManager assetManager() {
        return game.getAssetManager();
    }
}
