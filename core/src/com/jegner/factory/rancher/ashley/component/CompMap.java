package com.jegner.factory.rancher.ashley.component;

import com.badlogic.ashley.core.ComponentMapper;

public class CompMap {
    public static final ComponentMapper<BodyComponent> bodyCom = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<PlayerComponent> playerCom = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TransformComponent> transCom = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<TiledMapComponent> tiledMapCom = ComponentMapper.getFor(TiledMapComponent.class);

}
