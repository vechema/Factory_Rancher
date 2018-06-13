package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TransformComponent implements Component, Poolable {
    private final Vector3 position = new Vector3();
    private final Vector2 scale = new Vector2(1.0f,1.0f);
    private float rotation = 0.0f;
    private boolean isHidden = false;

    public Vector3 getPosition() {
        return position;
    }

    public Vector2 getScale() {
        return scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public void reset() {
        rotation = 0.0f;
        isHidden = false;
    }
}
