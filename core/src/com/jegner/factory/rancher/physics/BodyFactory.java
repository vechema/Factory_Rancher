package com.jegner.factory.rancher.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.jegner.factory.rancher.resource.GameResources;

public class BodyFactory {

    public enum FixtureMaterial {
        STEEL,
        WOOD,
        RUBBER,
        STONE,
    }

    private World world;
    private static BodyFactory instance;

    private BodyFactory(GameResources gameResources) {
        this.world = gameResources.getWorld();
    }

    public static BodyFactory getInstance(GameResources gameResources) {
        if (instance == null) {
            instance = new BodyFactory(gameResources);
        }
        return instance;
    }

    public static FixtureDef makeFixture(FixtureMaterial material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch (material) {
            case STEEL:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;
            case WOOD:
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.7f;
                fixtureDef.restitution = 0.3f;
                break;
            case RUBBER:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0f;
                fixtureDef.restitution = 1f;
                break;
            case STONE:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0f;
            default:
                fixtureDef.density = 7f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.3f;
        }
        return fixtureDef;
    }


    public Body makeCirclePolyBody(float posx, float posy, float radius, FixtureMaterial material, BodyType bodyType) {
        return makeCirclePolyBody(posx, posy, radius, material, bodyType,false);
    }

    public Body makeCirclePolyBody(float posx, float posy, float radius, FixtureMaterial material,
                                   BodyType bodyType, boolean fixedRotation) {
        // create the definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        // create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        boxBody.createFixture(makeFixture(material,circleShape));
        circleShape.dispose();
        return boxBody;
    }

    public Body makeBoxPolyBody(float posx, float posy, float width, float height, FixtureMaterial material, BodyType bodyType){
        return makeBoxPolyBody(posx, posy, width, height, material, bodyType, false);
    }

    public Body makeBoxPolyBody(float posx, float posy, float width, float height, FixtureMaterial material,
                                BodyType bodyType, boolean fixedRotation) {
        // create the definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        // create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, height/2);
        boxBody.createFixture(makeFixture(material,poly));
        poly.dispose();

        return boxBody;
    }
}
