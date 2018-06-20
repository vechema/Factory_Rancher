package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent implements Component, Poolable {

    private TextureRegion textureRegion;

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    @Override
    public void reset() {
        textureRegion = null;
    }
}
