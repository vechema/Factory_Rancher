package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.PlayerComponent;
import com.jegner.factory.rancher.ashley.component.TransformComponent;
import com.jegner.factory.rancher.resource.GameResources;

public class RenderingSystem extends SortedIteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(TransformComponent.class).get();

    // Useful resources
    private Array<Entity> renderQueue;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public RenderingSystem(GameResources gameResources) {
        super(family, new ZComparator());
        renderQueue = new Array<Entity>();

        this.batch = gameResources.getSpriteBatch();
        this.camera = gameResources.getCamera();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : renderQueue) {
            //TextureComponent tex = textureM.get(entity);
            TransformComponent transformComponent = CompMap.transCom.get(entity);
            PlayerComponent playerComponent = CompMap.playerCom.get(entity);

            if(playerComponent != null) {
                camera.position.x = transformComponent.getPosition().x;
                camera.position.y = transformComponent.getPosition().y;
            }

            /*if (tex.region == null || t.isHidden) {
                continue;
            }*/


            /*float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();

            float originX = width/2f;
            float originY = height/2f;

            batch.draw(tex.region,
                    t.position.x - originX, t.position.y - originY,
                    originX, originY,
                    width, height,
                    PixelsToMeters(t.scale.x), PixelsToMeters(t.scale.y),
                    t.rotation);*/
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
