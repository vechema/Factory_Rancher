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
import com.jegner.factory.rancher.ashley.component.DirectionComponent;
import com.jegner.factory.rancher.ashley.component.DirectionalTextureComponent;
import com.jegner.factory.rancher.ashley.component.PlayerComponent;
import com.jegner.factory.rancher.ashley.component.CharacterStateComponent;
import com.jegner.factory.rancher.ashley.component.TextureComponent;
import com.jegner.factory.rancher.ashley.component.TransformComponent;
import com.jegner.factory.rancher.physics.BodyFactory;
import com.jegner.factory.rancher.physics.BodyFactory.FixtureMaterial;
import com.jegner.factory.rancher.resource.GameAssetManager;
import com.jegner.factory.rancher.resource.GameResources;

import static com.jegner.factory.rancher.resource.GameResourceNames.humanAtlasFileName;
import static com.jegner.factory.rancher.ashley.component.DirectionComponent.CharacterDirection;
import static com.jegner.factory.rancher.ashley.component.CharacterStateComponent.CharacterState;
import static com.jegner.factory.rancher.resource.GameResourceNames.humanWalkUp;
import static com.jegner.factory.rancher.resource.GameResourceNames.humanWalkDown;
import static com.jegner.factory.rancher.resource.GameResourceNames.humanWalkRight;

public class PlayerFactory {

    // The singleton instance
    private static PlayerFactory instance;

    // Useful Resources
    private GameAssetManager assetManager;
    private PooledEngine engine;
    private BodyFactory bodyFactory;

    private PlayerFactory(GameResources gameResources) {

        this.assetManager = gameResources.getAssetManager();
        this.engine = gameResources.getEngine();

        this.bodyFactory = BodyFactory.getInstance(gameResources);
    }

    public static PlayerFactory getInstance(GameResources gameResources) {
        if(instance == null) {
            instance = new PlayerFactory(gameResources);
        }
        return instance;
    }

    public Entity createPlayerEntity() {

        // Create entity
        Entity entity = engine.createEntity();

        // Create components
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        CharacterStateComponent characterStateComponent = engine.createComponent(CharacterStateComponent.class);
        DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class);
        DirectionalTextureComponent directionalTextureComponent = engine.createComponent(DirectionalTextureComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        /*CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);*/

        // Set up body component
        float playerWidth = .5f;
        float playerHeight = 2f;
        int playerStartX = 0;
        int playerStartY = 0;
        Body body = bodyFactory.makeBoxPolyBody(playerStartX, playerStartY, playerWidth, playerHeight, FixtureMaterial.STONE, BodyType.DynamicBody, true);
        bodyComponent.setBody(body);

        // Set up Transform component
        transformComponent.getPosition().set(playerStartX, playerStartY,0);

        // Set up State component
        characterStateComponent.setCharacterState(CharacterState.STANDING);

        // Set up Direction component
        directionComponent.setCharDir(DirectionComponent.DEFAULT_DIRECTION);

        // Set up Directional Texture component
        TextureAtlas humanAtlas = assetManager.get(humanAtlasFileName, TextureAtlas.class);
        TextureRegion textureDown = humanAtlas.findRegion(humanWalkDown);
        TextureRegion textureUp = humanAtlas.findRegion(humanWalkUp);
        TextureRegion textureRight = humanAtlas.findRegion(humanWalkRight);
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

        Animation humanWalkDownAnimation = new Animation(frameDuration, humanAtlas.findRegions(humanWalkDown));
        humanWalkDownAnimation.setPlayMode(PlayMode.LOOP);
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.DOWN, humanWalkDownAnimation);

        Animation humanWalkUpAnimation = new Animation(frameDuration, humanAtlas.findRegions(humanWalkUp));
        humanWalkUpAnimation.setPlayMode(PlayMode.LOOP);
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.UP, humanWalkUpAnimation);

        Animation humanWalkRightAnimation = new Animation(frameDuration, humanAtlas.findRegions(humanWalkRight));
        humanWalkRightAnimation.setPlayMode(PlayMode.LOOP);
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.RIGHT, humanWalkRightAnimation);

        Animation<TextureRegion> humanWalkLeftAnimation = new Animation(frameDuration, humanAtlas.findRegions(humanWalkRight));
        humanWalkLeftAnimation.setPlayMode(PlayMode.LOOP);
        for (TextureRegion x : humanWalkLeftAnimation.getKeyFrames()) {
            x.flip(true,false);
        }
        animationComponent.setAnimation(CharacterState.WALKING, CharacterDirection.LEFT, humanWalkLeftAnimation);

        // Add Components to Entity
        entity.add(bodyComponent);
        entity.add(transformComponent);
        entity.add(playerComponent);
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
