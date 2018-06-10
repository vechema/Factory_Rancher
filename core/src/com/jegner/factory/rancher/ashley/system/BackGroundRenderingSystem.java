package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.TiledMapComponent;

/**
 * Used to render backgrounds in the form of tiled maps
 */
public class BackGroundRenderingSystem extends SortedIteratingSystem {

    // Need the sprite batch for actual rendering
    private SpriteBatch spriteBatch;

    // Camera and viewport
    private OrthographicCamera camera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer renderer;

    public BackGroundRenderingSystem(SpriteBatch spriteBatch) {
        super(Family.all(TiledMapComponent.class).get(), new ZComparator());
        this.spriteBatch = spriteBatch;

        // Camera setup
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        camera.zoom = 0.3f;
        camera.setToOrtho(false);

        // Renderer
        this.renderer = new OrthogonalTiledMapRenderer(new TiledMap(), 1, spriteBatch);

        Gdx.app.log("BG RenderingSystem", "Constructor");
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        TiledMapComponent tiledMapComponent = CompMap.tiledMapCom.get(entity);
        TiledMap map = tiledMapComponent.getMap();
        //renderer = new OrthogonalTiledMapRenderer(map, 1, spriteBatch);
        renderer.setMap(map);

        camera.setToOrtho(false);
        camera.position.x = tiledMapComponent.getMapWidthInPixels() * .5f;
        camera.position.y = tiledMapComponent.getMapHeightInPixels() * .5f;

        camera.update();
        renderer.setView(camera);
        renderer.render();
    }
}
