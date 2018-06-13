package com.jegner.factory.rancher.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.jegner.factory.rancher.FactoryRancherGame;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

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

    public ScreenManager(FactoryRancherGame game, GameResources gameResources) {
        Gdx.app.log("Screen manager", "start constructor");

        this.game = game;

        loadingScreen = new LoadingScreen(this, gameResources);
        gameScreen = new GameScreen(this, gameResources);
        mainMenuScreen = new MainMenuScreen(this, gameResources);
        pauseScreen = new PauseScreen(this, gameResources);

        // Set screen trackers to non-null
        currentScreen = loadingScreen;
        previousScreen = loadingScreen;

        Gdx.app.log("Screen manager", "end constructor");
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
}
