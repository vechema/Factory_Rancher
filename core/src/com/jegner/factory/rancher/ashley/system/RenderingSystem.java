package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.DirectionComponent;
import com.jegner.factory.rancher.ashley.component.PlayerComponent;
import com.jegner.factory.rancher.ashley.component.TextureComponent;
import com.jegner.factory.rancher.ashley.component.TransformComponent;
import com.jegner.factory.rancher.resource.GameResources;

public class RenderingSystem extends SortedIteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(TransformComponent.class, TextureComponent.class).get();

    // Useful resources
    private Array<Entity> renderQueue;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private float scale;

    public RenderingSystem(GameResources gameResources) {
        super(family, new ZComparator());
        renderQueue = new Array<Entity>();

        this.batch = gameResources.getSpriteBatch();
        this.camera = gameResources.getCamera();

        this.scale = gameResources.getMetersPerPixel();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent textureComponent = CompMap.texCom.get(entity);
            TransformComponent transformComponent = CompMap.transCom.get(entity);
            PlayerComponent playerComponent = CompMap.playerCom.get(entity);

            if(playerComponent != null) {
                camera.position.lerp(transformComponent.getPosition(), 0.05f);
            }

            if (textureComponent.getTextureRegion() == null || transformComponent.isHidden()) {
                continue;
            }

            TextureRegion textureRegion = textureComponent.getTextureRegion(entity);

            float width = textureRegion.getRegionWidth();
            float height = textureRegion.getRegionHeight();

            float originX = width/2f;
            float originY = height/2f;

            batch.draw(textureRegion,
                    transformComponent.getPosition().x - originX,
                    transformComponent.getPosition().y - originY,
                    originX, originY,
                    width, height,
                    transformComponent.getScale().x * scale,
                    transformComponent.getScale().y * scale,
                    transformComponent.getRotation());
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
