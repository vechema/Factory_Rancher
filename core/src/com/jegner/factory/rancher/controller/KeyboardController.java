package com.jegner.factory.rancher.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap;

public class KeyboardController implements InputProcessor {

    private boolean mouseLeft, mouseRight, mouseMiddle;
    private boolean isDragged;
    private Vector2 mouseLocation = new Vector2();

    private int leftKey, rightKey, upKey, downKey;
    private IntMap<Boolean> directions;

    private int scroll;

    public KeyboardController() {
        leftKey = Input.Keys.O;
        rightKey = Input.Keys.U;
        upKey = Input.Keys.PERIOD;
        downKey = Input.Keys.E;

        directions = new IntMap<Boolean>();
        directions.put(leftKey, false);
        directions.put(rightKey, false);
        directions.put(upKey, false);
        directions.put(downKey, false);

        scroll = 0;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean keyPressed = false;

        if(directions.containsKey(keycode)) {
            keyPressed = true;
            directions.put(keycode, true);
        }

        return keyPressed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyPressed = false;

        if(directions.containsKey(keycode)) {
            keyPressed = true;
            directions.put(keycode, false);
        }

        return keyPressed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            mouseLeft = true;
        } else if (button == Input.Buttons.RIGHT) {
            mouseRight = true;
        } else if (button == Input.Buttons.MIDDLE) {
            mouseMiddle = true;
        }

        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isDragged = false;

        if (button == Input.Buttons.LEFT) {
            mouseLeft = false;
        } else if (button == Input.Buttons.RIGHT) {
            mouseRight = false;
        } else if (button == Input.Buttons.MIDDLE) {
            mouseMiddle = false;
        }

        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        isDragged = true;
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    /**
     * Amount: up = -1, down = 1
     * @param amount
     * @return
     */
    @Override
    public boolean scrolled(int amount) {
        scroll = amount;
        return false;
    }

    private static final int SCROLL_UP = -1;
    private static final int SCROLL_DOWN = 1;

    public boolean isScrollUp() {
        return isScroll(SCROLL_UP);
    }

    public boolean isScrollDown() {
        return isScroll(SCROLL_DOWN);
    }

    private boolean isScroll(int amount) {
        boolean result = scroll == amount;
        if(result) {
            scroll = 0;
        }
        return result;

    }

    public boolean isLeft() {
        return directions.get(leftKey);
    }

    public boolean isRight() {
        return directions.get(rightKey);
    }

    public boolean isUp() {
        return directions.get(upKey);
    }

    public boolean isDown() {
        return directions.get(downKey);
    }

    public boolean isMouseLeft() {
        return mouseLeft;
    }

    public boolean isMouseRight() {
        return mouseRight;
    }

    public boolean isMouseMiddle() {
        return mouseMiddle;
    }

    public Vector2 getMouseLocation() {
        return mouseLocation;
    }
}
