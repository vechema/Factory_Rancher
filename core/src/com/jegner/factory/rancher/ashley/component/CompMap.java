package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.ComponentMapper;

public class CompMap {
    public static final ComponentMapper<AnimationComponent> animCom = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<BodyComponent> bodyCom = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<CharacterStateComponent> charStateCom = ComponentMapper.getFor(CharacterStateComponent.class);
    public static final ComponentMapper<DirectionComponent> dirCom = ComponentMapper.getFor(DirectionComponent.class);
    public static final ComponentMapper<DirectionalTextureComponent> dirTexCom = ComponentMapper.getFor(DirectionalTextureComponent.class);
    public static final ComponentMapper<PlayerComponent> playerCom = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> texCom = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<TiledMapComponent> tiledMapCom = ComponentMapper.getFor(TiledMapComponent.class);
    public static final ComponentMapper<TransformComponent> transCom = ComponentMapper.getFor(TransformComponent.class);

}
