package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class CharacterStateComponent implements Component, Poolable{

    public static final CharacterState DEFAULT_STATE = CharacterState.STANDING;
    public CharacterState characterState = DEFAULT_STATE;
    private float time = 0f;
    private boolean isLooping = false;

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public void setLooping(boolean looping) {
        isLooping = looping;
    }

    @Override
    public void reset() {
        characterState = DEFAULT_STATE;
    }

    public static enum CharacterState {
        STANDING,WALKING
    }
}
