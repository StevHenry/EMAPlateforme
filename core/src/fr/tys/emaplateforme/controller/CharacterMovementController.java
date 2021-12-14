package fr.tys.emaplateforme.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import fr.tys.emaplateforme.world.Character;
import fr.tys.emaplateforme.world.World;
import fr.tys.emaplateforme.world.blocks.Block;
import fr.tys.emaplateforme.world.Character.CharacterState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static fr.tys.emaplateforme.util.Constants.*;

public class CharacterMovementController implements InputProcessor {

    private static Map<Control, Boolean> keys = new HashMap<>();

    static {
        keys.put(Control.LEFT, false);
        keys.put(Control.RIGHT, false);
        keys.put(Control.JUMP, false);
    }

    private final World world;
    private final Character character;
    private ArrayList<Block> collidable = new ArrayList<Block>();
    private long jumpPressedTime;
    private boolean jumpingPressed;

    public CharacterMovementController(World world) {
        this.world = world;
        this.character = world.getCharacter();
    }

    /**
     * The main update method
     **/
    public void update(float delta) {
        processInput();
        character.getAcceleration().y = GRAVITY;
        character.getAcceleration().scl(delta);
        character.getVelocity().add(character.getAcceleration().x, character.getAcceleration().y);

        if (character.getAcceleration().x == 0) {
            character.getVelocity().x *= DAMP;
        }

        if (Math.abs(character.getVelocity().x) > PLAYER_MAXIMUM_MOVING_SPEED) {
            character.getVelocity().x = (character.getVelocity().x > 0 ? 1f : -1f) * PLAYER_MAXIMUM_MOVING_SPEED;
        }

        character.update(delta);
        checkBounds();
    }

    private void checkBounds() {
        if (character.getPosition().y <= 0) {
            character.getPosition().set(character.getPosition().x, 0f);
            resetStateVertical();
        } else if (character.getPosition().y > world.getHeight()) {
            character.getPosition().set(character.getPosition().x, world.getHeight());
            resetStateVertical();
        }

        if (character.getPosition().x < 0) {
            character.getPosition().set(0, character.getPosition().y);
            resetStateHorizontal();
        } else if (character.getPosition().x > world.getWidth()) {
            character.getPosition().set(world.getWidth(), character.getPosition().y);
            resetStateHorizontal();
        }
    }


    private void checkCollisionWithBlocks(float delta) {
        character.getVelocity().scl(delta);
        Rectangle emarioRECT = character.getHitbox();
        emarioRECT.set(character.getHitbox().x, character.getHitbox().y, character.getHitbox().width, character.getHitbox().height);
        int startX, endX;
        int startY = (int) character.getHitbox().y;
        int endY = (int) (character.getHitbox().y + character.getHitbox().height);
        if (character.getVelocity().x < 0) {
            startX = endX = (int) Math.floor(character.getHitbox().x + character.getVelocity().x);
        } else {
            startX = endX = (int) Math.floor(character.getHitbox().x + character.getHitbox().width + character.getVelocity().x);
        }
        populateCollidableBlocks(startX, startY, endX, endY);
        emarioRECT.x += character.getVelocity().x;
        world.getCollisionShapes().clear();
        for (Block block : collidable) {
            if (block == null) continue;
            if (emarioRECT.overlaps(block.getHitbox())) {
                character.getVelocity().x = 0;
                world.getCollisionShapes().add(block.getHitbox());
                break;
            }
        }
        emarioRECT.x = character.getPosition().x;
        startX = (int) character.getHitbox().x;
        endX = (int) (character.getHitbox().x + character.getHitbox().width);
        if (character.getVelocity().y < 0) {
            startY = endY = (int) Math.floor(character.getHitbox().y + character.getVelocity().y);
        } else {
            startY = endY = (int) Math.floor(character.getHitbox().y + character.getHitbox().height + character.getVelocity().y);
        }
        populateCollidableBlocks(startX, startY, endX, endY);
        emarioRECT.y += character.getVelocity().y;
        for (Block block : collidable) {
            if (block == null) continue;
            if (emarioRECT.overlaps(block.getHitbox())) {
                if (character.getVelocity().y < 0) {
                    resetStateVertical();
                }
                character.getVelocity().y = 0;
                world.getCollisionShapes().add(block.getHitbox());
                break;
            }
        }
        emarioRECT.y = character.getPosition().y;
        character.getPosition().add(character.getVelocity());
        character.getHitbox().x = character.getPosition().x;
        character.getHitbox().y = character.getPosition().y;
        character.getVelocity().scl(1 / delta);
    }

    private void populateCollidableBlocks(int startX, int startY, int endX, int endY) {
        collidable.clear();
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (x >= 0 && x < world.getWidth() && y >= 0 && y < world.getHeight()) {
                    world.getBlockAt(x,y).ifPresent(collidable::add);
                }
            }
        }
    }

    private void resetStateVertical() {
        if (character.isState(CharacterState.JUMPING)) {
            character.setState(character.getVelocity().x != 0 ? CharacterState.WALKING : CharacterState.IDLE);
        }
    }

    private void resetStateHorizontal() {
        if (!character.isState(CharacterState.JUMPING)) {
            character.setState(CharacterState.IDLE);
        }
    }


    private boolean processInput() {
        if (keys.get(Control.JUMP)) {
            if (!character.isState(CharacterState.JUMPING)) {
                jumpingPressed = true;
                jumpPressedTime = System.currentTimeMillis();
                character.setState(CharacterState.JUMPING);
                character.getVelocity().y = PLAYER_MAXIMUM_JUMPING_SPEED;
            } else {
                if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
                    jumpingPressed = false;
                } else {
                    if (jumpingPressed) {
                        character.getVelocity().y = PLAYER_MAXIMUM_JUMPING_SPEED;
                    }
                }
            }
        }
        if (keys.get(Control.LEFT)) {
            // left is pressed
            character.setFacingLeft();
            if (!character.isState(CharacterState.JUMPING)) {
                character.setState(CharacterState.WALKING);
            }
            character.getAcceleration().x = -ACCELERATION;
        } else if (keys.get(Control.RIGHT)) {
            // left is pressed
            character.setFacingRight();
            if (!character.isState(CharacterState.JUMPING)) {
                character.setState(CharacterState.WALKING);
            }
            character.getAcceleration().x = ACCELERATION;
        } else {
            if (!character.isState(CharacterState.JUMPING)) {
                character.setState(CharacterState.IDLE);
            }
            character.getAcceleration().x = 0;
        }
        return false;
    }

    public void jumpReleased() {
        if (keys.get(Control.JUMP)) {
            jumpingPressed = false;
        }
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
            case Input.Keys.LEFT:
                keys.put(Control.LEFT, down);
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                keys.put(Control.RIGHT, down);
                break;
            case Input.Keys.SPACE:
                keys.put(Control.JUMP, down);
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

    enum Control {
        LEFT, RIGHT, JUMP
    }
}
