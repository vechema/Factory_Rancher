package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool.Poolable;

import static com.jegner.factory.rancher.ashley.component.CharacterStateComponent.CharacterState;
import static com.jegner.factory.rancher.ashley.component.DirectionComponent.CharacterDirection;
import static com.jegner.factory.rancher.ashley.component.DirectionComponent.DEFAULT_DIRECTION;

public class AnimationComponent implements Component, Poolable {

    private ObjectMap<CharacterState, ObjectMap<CharacterDirection, Animation<TextureRegion>>> animations = new ObjectMap<CharacterState, ObjectMap<CharacterDirection, Animation<TextureRegion>>>();

    public static final CharacterState DEFAULT_ANIMATION_STATE = CharacterState.WALKING;

    public ObjectMap<CharacterState, ObjectMap<CharacterDirection, Animation<TextureRegion>>> getAnimations() {
        return animations;
    }

    public void setAnimations(ObjectMap<CharacterState, ObjectMap<CharacterDirection, Animation<TextureRegion>>> animations) {
        this.animations = animations;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        setAnimation(DEFAULT_ANIMATION_STATE, DEFAULT_DIRECTION, animation);
    }

    public void setAnimation(CharacterState characterState, Animation<TextureRegion> animation) {
        setAnimation(characterState, DEFAULT_DIRECTION, animation);
    }

    public void setAnimation(CharacterDirection characterDirection, Animation<TextureRegion> animation) {
        setAnimation(DEFAULT_ANIMATION_STATE, characterDirection, animation);
    }

    public void setAnimation(CharacterState characterState, CharacterDirection characterDirection, Animation<TextureRegion> animation) {
        if(animations.get(characterState) == null) {
            animations.put(characterState, new ObjectMap<CharacterDirection, Animation<TextureRegion>>());
        }

        animations.get(characterState).put(characterDirection, animation);
    }

    public boolean hasState(CharacterState characterState) {
        return animations.containsKey(characterState);
    }

    public Animation<TextureRegion> getAnimation(CharacterState characterState) {
        return getAnimation(characterState, DEFAULT_DIRECTION);
    }

    public Animation<TextureRegion> getAnimation(CharacterDirection characterDirection) {
        return getAnimation(DEFAULT_ANIMATION_STATE, characterDirection);
    }

    public Animation<TextureRegion> getAnimation(CharacterState characterState, CharacterDirection characterDirection) {
        return animations.get(characterState).get(characterDirection);
    }

    public Animation<TextureRegion> getAnimation(Entity entity) {

        DirectionComponent directionComponent = CompMap.dirCom.get(entity);
        CharacterStateComponent characterStateComponent = CompMap.charStateCom.get(entity);
        Animation<TextureRegion> animation;

        CharacterState characterState = characterStateComponent == null ? DEFAULT_ANIMATION_STATE : characterStateComponent.getCharacterState();
        CharacterDirection characterDirection = directionComponent == null ? DEFAULT_DIRECTION : directionComponent.getCharDir();

        return getAnimation(characterState, characterDirection);
    }

    @Override
    public void reset() {
        animations = new ObjectMap<CharacterState, ObjectMap<CharacterDirection, Animation<TextureRegion>>>();
    }
}
