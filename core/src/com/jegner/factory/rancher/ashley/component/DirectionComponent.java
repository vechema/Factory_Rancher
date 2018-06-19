package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class DirectionComponent implements Component, Poolable {

    private CharacterDirection charDir;
    public static final CharacterDirection DEFAULT_DIRECTION = CharacterDirection.DOWN;

    public CharacterDirection getCharDir() {
        return charDir;
    }

    public void setCharDir(CharacterDirection charDir) {
        this.charDir = charDir;
    }

    @Override
    public void reset() {
        charDir = CharacterDirection.DOWN;
    }

    public static enum CharacterDirection {
        UP, DOWN, RIGHT, LEFT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT,
    }
}
