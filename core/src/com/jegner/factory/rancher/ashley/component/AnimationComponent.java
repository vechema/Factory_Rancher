package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool.Poolable;

import static com.jegner.factory.rancher.ashley.component.CharacterStateComponent.CharacterState;

public class AnimationComponent implements Component, Poolable {

    private ObjectMap<CharacterState, Animation<TextureRegion>> animations = new ObjectMap<CharacterState, Animation<TextureRegion>>();

    public ObjectMap<CharacterState, Animation<TextureRegion>> getAnimations() {
        return animations;
    }

    public void setAnimations(ObjectMap<CharacterState, Animation<TextureRegion>> animations) {
        this.animations = animations;
    }

    @Override
    public void reset() {
        animations = new ObjectMap<CharacterState, Animation<TextureRegion>>();
    }
}
