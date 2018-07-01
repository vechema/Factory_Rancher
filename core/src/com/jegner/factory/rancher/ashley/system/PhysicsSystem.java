package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.jegner.factory.rancher.ashley.component.BodyComponent;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.TransformComponent;
import com.jegner.factory.rancher.resource.GameResources;

public class PhysicsSystem extends IteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(TransformComponent.class, BodyComponent.class).get();

    // Keep the simulation from running too much
    private static final float MAX_STEP_TIME = 1/60f;
    private static float accumulator = 0f;

    // Useful resources
    private World world;
    private Engine engine;
    private Array<Entity> bodiesQueue;

    public PhysicsSystem(GameResources gameResources) {
        super(family);

        this.world = gameResources.getWorld();
        this.engine = gameResources.getEngine();

        this.bodiesQueue = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;

        if (accumulator >= MAX_STEP_TIME) {
            world.step(MAX_STEP_TIME, 6, 2);
            accumulator -= MAX_STEP_TIME;

            for (Entity entity : bodiesQueue) {
                TransformComponent tfm = CompMap.transCom.get(entity);
                BodyComponent bodyComp = CompMap.bodyCom.get(entity);
                Vector2 position = bodyComp.getBody().getPosition();
                tfm.getPosition().x = position.x;
                tfm.getPosition().y = position.y;
                tfm.setRotation(bodyComp.getBody().getAngle() * MathUtils.radiansToDegrees);

                /*if(bodyComp.isDead) {
                    System.out.println("Removing a body and entity");
                    world.destroyBody(bodyComp.getBody());
                    engine.removeEntity(entity);
                }*/
            }
        }
        bodiesQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }
}
