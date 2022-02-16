package fr.tys.emaplateforme.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.Map;

public class GameInputsManager implements InputProcessor {

    private final Map<EMAControl, Boolean> keys = new HashMap<>();

    public GameInputsManager() {
        keys.put(EMAControl.LEFT, false);
        keys.put(EMAControl.RIGHT, false);
        keys.put(EMAControl.JUMP, false);
    }

    /**
     * @param control whose state is needed
     * @return whether the control is active or not
     */
    public boolean getKeyState(EMAControl control) {
        return keys.get(control);
    }


    @Override
    public boolean keyDown(int keycode) {
        controlTouched(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        controlTouched(keycode, false);
        return true;
    }

    private void controlTouched(int keycode, boolean down) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.Q:
            case Input.Keys.LEFT:
                keys.put(EMAControl.LEFT, down);
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                keys.put(EMAControl.RIGHT, down);
                break;
            case Input.Keys.SPACE:
            case Input.Keys.UP:
                keys.put(EMAControl.JUMP, down);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    /**
     * Resets the keys state
     */
    public void reset() {
        for (EMAControl control : keys.keySet()) {
            keys.put(control, false);
        }
    }
}
