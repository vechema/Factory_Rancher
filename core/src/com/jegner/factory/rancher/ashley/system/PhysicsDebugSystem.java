package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.jegner.factory.rancher.ashley.component.BodyComponent;
import com.jegner.factory.rancher.resource.GameResources;

public class PhysicsDebugSystem extends IteratingSystem {


    // Family of components for system
    private static final Family family = Family.all().get();

    private Box2DDebugRenderer debugRenderer;
    private World world;
    private OrthographicCamera camera;

    public PhysicsDebugSystem(GameResources gameResources){
        super(family);
        debugRenderer = new Box2DDebugRenderer();
        this.world = gameResources.getWorld();
        this.camera = gameResources.getCamera();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        debugRenderer.render(world, camera.combined);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
