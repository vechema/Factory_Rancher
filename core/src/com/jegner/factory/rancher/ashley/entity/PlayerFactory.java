package com.jegner.factory.rancher.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jegner.factory.rancher.ashley.component.BodyComponent;
import com.jegner.factory.rancher.ashley.component.PlayerComponent;
import com.jegner.factory.rancher.ashley.component.TransformComponent;
import com.jegner.factory.rancher.physics.BodyFactory;
import com.jegner.factory.rancher.physics.BodyFactory.FixtureMaterial;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

public class PlayerFactory {

    // The singleton instance
    private static PlayerFactory instance;

    // Useful Resources
    private GameAssetManager assetManager;
    private PooledEngine engine;
    private BodyFactory bodyFactory;

    private PlayerFactory(GameResources gameResources) {

        this.assetManager = gameResources.getAssetManager();
        this.engine = gameResources.getEngine();

        this.bodyFactory = BodyFactory.getInstance(gameResources);
    }

    public static PlayerFactory getInstance(GameResources gameResources) {
        if(instance == null) {
            instance = new PlayerFactory(gameResources);
        }
        return instance;
    }

    public Entity createPlayerEntity() {

        // Create entity
        Entity entity = engine.createEntity();

        // Create components
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        /*TextureComponent texture = engine.createComponent(TextureComponent.class);
        AnimationComponent animCom = engine.createComponent(AnimationComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);*/

        // Set up body component
        float playerWidth = .5f;
        float playerHeight = 2f;
        int playerStartX = 0;
        int playerStartY = 0;
        Body body = bodyFactory.makeBoxPolyBody(playerStartX, playerStartY, playerWidth, playerHeight, FixtureMaterial.STONE, BodyType.DynamicBody, true);
        bodyComponent.setBody(body);

        Gdx.app.log("Player factory", "Player weight: " + body.getMass());
        // Set up Transform component
        transformComponent.getPosition().set(playerStartX, playerStartY,0);

        // Add Components to Entity
        entity.add(bodyComponent);
        entity.add(transformComponent);
        entity.add(playerComponent);

        // Add Entity to Engine
        engine.addEntity(entity);

        return entity;
    }
}
