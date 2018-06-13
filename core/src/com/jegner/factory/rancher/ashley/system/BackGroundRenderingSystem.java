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
import com.jegner.factory.rancher.resource.GameResources;

/**
 * Used to render backgrounds in the form of tiled maps
 */
public class BackGroundRenderingSystem extends SortedIteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(TiledMapComponent.class).get();

    // Need the sprite batch for actual rendering
    private SpriteBatch spriteBatch;

    // Camera and viewport
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    public BackGroundRenderingSystem(GameResources gameResources) {
        super(family, new ZComparator());
        this.spriteBatch = gameResources.getSpriteBatch();

        // Camera setup
        this.camera = gameResources.getCamera();

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

        camera.update();
        renderer.setView(camera);
        renderer.render();
    }
}
