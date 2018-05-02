package com.jegner.factory.rancher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyGdxGame extends ApplicationAdapter {
	// Map
	private TiledMap map;
	private AssetManager manager;

	// Map properties
	private int tileWidth, tileHeight,
			mapWidthInTiles, mapHeightInTiles,
			mapWidthInPixels, mapHeightInPixels;

	//Display size;
	private int displayWidth;
	private int displayHeight;

	// Camera and render
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;

	@Override
	public void create () {
		// Map loading
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load("dirt/dirtMap.tmx", TiledMap.class);
		manager.finishLoading();
		map = manager.get("dirt/dirtMap.tmx", TiledMap.class);

		// Read properties
		MapProperties properties = map.getProperties();
		tileWidth         = properties.get("tilewidth", Integer.class);
		tileHeight        = properties.get("tileheight", Integer.class);
		mapWidthInTiles   = properties.get("width", Integer.class);
		mapHeightInTiles  = properties.get("height", Integer.class);
		mapWidthInPixels  = mapWidthInTiles  * tileWidth;
		mapHeightInPixels = mapHeightInTiles * tileHeight;

		// Set up the camera
		displayWidth = Gdx.graphics.getWidth();
		displayHeight = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(displayWidth, displayHeight);
		camera.zoom = 0.3f;
		camera.position.x = mapWidthInPixels * .5f;
		camera.position.y = mapHeightInPixels * .5f;

		// Instantiation of the render for the map object
		renderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update the camera and render
		camera.update();
		renderer.setView(camera);
		renderer.render();
	}

	@Override
    public void resize(int width, int height) {
	    displayWidth = Gdx.graphics.getWidth();
        displayHeight = Gdx.graphics.getHeight();
	    camera.setToOrtho(true, displayWidth, displayHeight);
        camera.position.x = mapWidthInPixels * .5f;
        camera.position.y = mapHeightInPixels * .5f;
    }

	@Override
	public void dispose () {
		// Free resources
		manager.dispose();
	}
}
