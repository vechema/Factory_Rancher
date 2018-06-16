package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jegner.factory.rancher.ashley.component.BodyComponent;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.PlayerComponent;
import com.jegner.factory.rancher.controller.KeyboardController;
import com.jegner.factory.rancher.resource.GameResources;

public class PlayerControlSystem extends IteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(PlayerComponent.class, BodyComponent.class).get();

    // Useful resources
    private KeyboardController keyboardController;

    public PlayerControlSystem(GameResources gameResources) {
        super(family);

        this.keyboardController = gameResources.getKeyboardController();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BodyComponent bodyComponent = CompMap.bodyCom.get(entity);
        Body body = bodyComponent.getBody();
        PlayerComponent playerComponent = CompMap.playerCom.get(entity);

        // Setting up body speed
        int deltaX = 0;
        int deltaY = 0;

        if (keyboardController.isLeft()) {
            deltaX -=10;
        }

        if (keyboardController.isRight()) {
            deltaX +=10;
        }

        if (keyboardController.isDown()) {
            deltaY -=10;
        }

        if (keyboardController.isUp()) {
            deltaY +=10;
        }

        body.setLinearVelocity(new Vector2(deltaX, deltaY));

    }
}
