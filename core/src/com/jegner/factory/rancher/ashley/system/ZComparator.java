package com.jegner.factory.rancher.ashley.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.jegner.factory.rancher.ashley.component.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    private ComponentMapper<TransformComponent> cmTrans;

    public ZComparator() {
        cmTrans = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        float az = cmTrans.get(entityA).getPosition().z;
        float bz = cmTrans.get(entityB).getPosition().z;
        return Float.compare(az,bz);
    }
}
