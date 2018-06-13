package com.jegner.factory.rancher.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

public abstract class AbstractScreen implements Screen {

    protected final ScreenManager screenManager;
    protected final GameAssetManager assetManager;
    private final Viewport viewport;

    public AbstractScreen(ScreenManager screenManager, GameResources gameResources) {
        this.screenManager = screenManager;
        this.assetManager = gameResources.getAssetManager();
        this.viewport = gameResources.getViewport();
        Gdx.app.log("New Screen", shortClassName());
    }


    @Override
    public void show() {
        Gdx.app.log("Screen show", shortClassName());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.app.log("Screen render", shortClassName());
    }

    private String shortClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
