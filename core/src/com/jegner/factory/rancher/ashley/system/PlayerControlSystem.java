package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.jegner.factory.rancher.ashley.component.BodyComponent;
import com.jegner.factory.rancher.ashley.component.CharacterStateComponent;
import com.jegner.factory.rancher.ashley.component.CharacterStateComponent.CharacterState;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.DirectionComponent;
import com.jegner.factory.rancher.ashley.component.PlayerComponent;
import com.jegner.factory.rancher.controller.KeyboardController;
import com.jegner.factory.rancher.resource.GameResources;

public class PlayerControlSystem extends IteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(PlayerComponent.class,
            BodyComponent.class, DirectionComponent.class, CharacterStateComponent.class).get();

    // Useful resources
    private KeyboardController keyboardController;
    private OrthographicCamera camera;
    private float zoomFactor;

    public PlayerControlSystem(GameResources gameResources) {
        super(family);

        this.keyboardController = gameResources.getKeyboardController();
        this.camera = gameResources.getCamera();
        this.zoomFactor = gameResources.getMetersPerPixel() / 30;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BodyComponent bodyComponent = CompMap.bodyCom.get(entity);
        Body body = bodyComponent.getBody();
        PlayerComponent playerComponent = CompMap.playerCom.get(entity);
        DirectionComponent directionComponent = CompMap.dirCom.get(entity);
        CharacterStateComponent characterStateComponent = CompMap.charStateCom.get(entity);

        // Setting up body speed
        if (keyboardController.isLeft()) {
            body.setLinearVelocity(MathUtils.lerp(body.getLinearVelocity().x,
                    -7f, 0.02f), body.getLinearVelocity().y);
            directionComponent.setCharDir(DirectionComponent.CharacterDirection.LEFT);
        }
        if (keyboardController.isRight()) {
            body.setLinearVelocity(MathUtils.lerp(body.getLinearVelocity().x,
                    7f, 0.02f), body.getLinearVelocity().y);
            directionComponent.setCharDir(DirectionComponent.CharacterDirection.RIGHT);
        }
        if (keyboardController.isUp()) {
            body.setLinearVelocity(body.getLinearVelocity().x,
                    MathUtils.lerp(body.getLinearVelocity().y,7f, 0.02f));
            directionComponent.setCharDir(DirectionComponent.CharacterDirection.UP);
        }
        if (keyboardController.isDown()) {
            body.setLinearVelocity(body.getLinearVelocity().x,
                    MathUtils.lerp(body.getLinearVelocity().y,-7f, 0.02f));
            directionComponent.setCharDir(DirectionComponent.CharacterDirection.DOWN);
        }

        if (!keyboardController.isDirection()) {
            body.setLinearVelocity(MathUtils.lerp(body.getLinearVelocity().x,
                    0, 0.01f), body.getLinearVelocity().y);
            characterStateComponent.setCharacterState(CharacterState.STANDING);
        } else {
            characterStateComponent.setCharacterState(CharacterState.WALKING);
        }

        // Handling scrolling
        // TODO end scrolling at edge of map
        // TODO put in seperate system
        if(keyboardController.isScrollUp()) {
            camera.zoom = MathUtils.clamp(camera.zoom - zoomFactor,0.006f,0.6f);
        }

        if(keyboardController.isScrollDown()) {
            camera.zoom = MathUtils.clamp(camera.zoom + zoomFactor,0.006f,0.6f);
        }

    }
}
