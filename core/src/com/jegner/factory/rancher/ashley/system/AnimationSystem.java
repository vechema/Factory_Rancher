package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.jegner.factory.rancher.ashley.component.AnimationComponent;
import com.jegner.factory.rancher.ashley.component.CharacterStateComponent;
import com.jegner.factory.rancher.ashley.component.CharacterStateComponent.CharacterState;
import com.jegner.factory.rancher.ashley.component.CompMap;
import com.jegner.factory.rancher.ashley.component.DirectionComponent.CharacterDirection;
import com.jegner.factory.rancher.ashley.component.TextureComponent;
import com.jegner.factory.rancher.resource.GameResources;

public class AnimationSystem extends IteratingSystem {

    // Family of components for system
    private static final Family family = Family.all(AnimationComponent.class,
            CharacterStateComponent.class, TextureComponent.class).get();

    public AnimationSystem(GameResources gameResources) {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = CompMap.animCom.get(entity);
        CharacterStateComponent characterStateComponent = CompMap.charStateCom.get(entity);
        TextureComponent textureComponent = CompMap.texCom.get(entity);

        CharacterState characterState = characterStateComponent.getCharacterState();
        float stateTime = characterStateComponent.getTime();
        if (animationComponent.hasState(characterState)) {
            boolean stateLooping = characterStateComponent.isLooping();
            TextureRegion animationTexture = animationComponent.getAnimation(entity).getKeyFrame(stateTime, stateLooping);
            textureComponent.setTextureRegion(animationTexture);
        }

        characterStateComponent.setTime(stateTime + deltaTime);
    }
}
