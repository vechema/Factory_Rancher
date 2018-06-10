package com.jegner.factory.rancher.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

    // Screen tracker
    private AbstractScreen currentScreen;
    private AbstractScreen previousScreen;

    public ScreenManager(FactoryRancherGame game) {
        this.game = game;

        loadingScreen = new LoadingScreen(this);
        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        pauseScreen = new PauseScreen(this);

        // Set screen trackers to non-null
        currentScreen = loadingScreen;
        previousScreen = loadingScreen;
    }

    public void setToLoadingScreen() {
        changeScreen(loadingScreen);
    }

    public void setToGameScreen() {
        changeScreen(gameScreen);
    }

    public void setToMainMenuScreen() {
        changeScreen(mainMenuScreen);
    }

    public void setToPauseScreen() {
        changeScreen(pauseScreen);
    }

    private void changeScreen(AbstractScreen screen) {
        previousScreen = currentScreen;
        currentScreen = screen;
        Gdx.app.log("Set screen", "pre: " + previousScreen.getClass().getSimpleName()
                + ", current: " + currentScreen.getClass().getSimpleName());
        game.setScreen(screen);
        previousScreen.dispose();
    }

    public GameAssetManager getAssetManager() {
        return game.getAssetManager();
    }
}
