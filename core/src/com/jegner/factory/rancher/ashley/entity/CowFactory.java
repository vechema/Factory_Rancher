package com.jegner.factory.rancher.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.ObjectMap;
import com.jegner.factory.rancher.ashley.component.AnimationComponent;
import com.jegner.factory.rancher.ashley.component.BodyComponent;
import com.jegner.factory.rancher.ashley.component.CharacterStateComponent;
import com.jegner.factory.rancher.ashley.component.DirectionComponent;
import com.jegner.factory.rancher.ashley.component.DirectionalTextureComponent;
import com.jegner.factory.rancher.ashley.component.TextureComponent;
import com.jegner.factory.rancher.ashley.component.TransformComponent;
import com.jegner.factory.rancher.physics.BodyFactory;
import com.jegner.factory.rancher.physics.BodyFactory.FixtureMaterial;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

import static com.jegner.factory.rancher.ashley.component.CharacterStateComponent.CharacterState;
import static com.jegner.factory.rancher.ashley.component.DirectionComponent.CharacterDirection;
import static com.jegner.factory.rancher.resource.GameResourceNames.cowAtlasFileName;
import static com.jegner.factory.rancher.resource.GameResourceNames.cowWalkDown;
import static com.jegner.factory.rancher.resource.GameResourceNames.cowWalkRight;
import static com.jegner.factory.rancher.resource.GameResourceNames.cowWalkUp;

public class CowFactory {

    // The singleton instance
    private static CowFactory instance;

    // Useful Resources
    private GameAssetManager assetManager;
    private PooledEngine engine;
    private BodyFactory bodyFactory;

    private CowFactory(GameResources gameResources) {

        this.assetManager = gameResources.getAssetManager();
        this.engine = gameResources.getEngine();

        this.bodyFactory = BodyFactory.getInstance(gameResources);
    }

    public static CowFactory getInstance(GameResources gameResources) {
        if(instance == null) {
            instance = new CowFactory(gameResources);
        }
        return instance;
    }

    public Entity createCowEntity() {

        // Create entity
        Entity entity = engine.createEntity();

        // Create components
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        CharacterStateComponent characterStateComponent = engine.createComponent(CharacterStateComponent.class);
        DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class);
        DirectionalTextureComponent directionalTextureComponent = engine.createComponent(DirectionalTextureComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        /*CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);*/

        // Set up body component
        float cowWidth = 1f;
        float cowHeight = 1f;
        int cowStartX = 5;
        int cowStartY = 5;
        Body body = bodyFactory.makeBoxPolyBody(cowStartX, cowStartY, cowWidth, cowHeight, FixtureMaterial.STONE, BodyType.DynamicBody, true);
        bodyComponent.setBody(body);

        // Set up Transform component
        transformComponent.getPosition().set(cowStartX, cowStartY,0);

        // Set up State component
        characterStateComponent.setCharacterState(CharacterState.STANDING);

        // Set up Direction component
        directionComponent.setCharDir(DirectionComponent.DEFAULT_DIRECTION);

        // Set up Directional Texture component
        TextureAtlas cowAtlas = assetManager.get(cowAtlasFileName, TextureAtlas.class);
        TextureRegion textureDown = cowAtlas.findRegion(cowWalkDown);
        TextureRegion textureUp = cowAtlas.findRegion(cowWalkUp);
        TextureRegion textureRight = cowAtlas.findRegion(cowWalkRight);
        TextureRegion textureLeft = new TextureRegion(textureRight);
        textureLeft.flip(true,false);

        ObjectMap textures = directionalTextureComponent.getTextures();
        textures.put(CharacterDirection.DOWN, textureDown);
        textures.put(CharacterDirection.UP, textureUp);
        textures.put(CharacterDirection.RIGHT, textureRight);
        textures.put(CharacterDirection.LEFT, textureLeft);

        // Set up Texture component
        textureComponent.setTextureRegion(textureDown);

        // Set up Animation component
        float frameDuration = 0.12f;

        Animation cowWalkDownAnimation = new Animation(frameDuration, cowAtlas.findRegions(cowWalkDown));
        cowWalkDownAnimation.setPlayMode(PlayMode.LOOP);
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.DOWN, cowWalkDownAnimation);

        Animation cowWalkUpAnimation = new Animation(frameDuration, cowAtlas.findRegions(cowWalkUp));
        cowWalkUpAnimation.setPlayMode(PlayMode.LOOP);
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.UP, cowWalkUpAnimation);

        Animation cowWalkRightAnimation = new Animation(frameDuration, cowAtlas.findRegions(cowWalkRight));
        cowWalkRightAnimation.setPlayMode(PlayMode.LOOP);
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.RIGHT, cowWalkRightAnimation);

        Animation<TextureRegion> cowWalkLeftAnimation = new Animation(frameDuration, cowAtlas.findRegions(cowWalkRight));
        cowWalkLeftAnimation.setPlayMode(PlayMode.LOOP);
        for (TextureRegion x : cowWalkLeftAnimation.getKeyFrames()) {
            x.flip(true,false);
        }
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.LEFT, cowWalkLeftAnimation);

        // Add Components to Entity
        entity.add(bodyComponent);
        entity.add(transformComponent);
        entity.add(characterStateComponent);
        entity.add(directionComponent);
        entity.add(directionalTextureComponent);
        entity.add(textureComponent);
        entity.add(animationComponent);

        // Add Entity to Engine
        engine.addEntity(entity);

        return entity;
    }
}
