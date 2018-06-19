package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool.Poolable;

import static com.jegner.factory.rancher.ashley.component.DirectionComponent.DEFAULT_DIRECTION;
import static com.jegner.factory.rancher.ashley.component.DirectionComponent.CharacterDirection;

public class TextureComponent implements Component, Poolable {

    private ObjectMap<CharacterDirection, TextureRegion> textures = new ObjectMap<CharacterDirection, TextureRegion>();

    public ObjectMap<CharacterDirection, TextureRegion> getTextures() {
        return textures;
    }

    public void setTextures(ObjectMap<CharacterDirection, TextureRegion> textures) {
        this.textures = textures;
    }

    public TextureRegion getTextureRegion() {
        return getTextureRegion(DEFAULT_DIRECTION);
    }

    public TextureRegion getTextureRegion(CharacterDirection characterDirection) {
        return textures.get(characterDirection);
    }

    public TextureRegion getTextureRegion(Entity entity) {
        DirectionComponent directionComponent = CompMap.dirCom.get(entity);
        TextureRegion textureRegion;

        if (directionComponent != null) {
            Gdx.app.log("Get Texture Region", ""+ directionComponent.getCharDir());
            textureRegion = getTextureRegion(directionComponent.getCharDir());
        } else {
            textureRegion = getTextureRegion();
        }
        return textureRegion;
    }

    @Override
    public void reset() {
        textures = new ObjectMap<CharacterDirection, TextureRegion>();
    }
}
