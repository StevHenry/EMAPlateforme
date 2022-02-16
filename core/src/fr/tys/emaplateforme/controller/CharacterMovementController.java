package fr.tys.emaplateforme.controller;

import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.world.Character;
import fr.tys.emaplateforme.world.Character.CharacterState;
import fr.tys.emaplateforme.world.World;
import fr.tys.emaplateforme.world.blocks.Block;
import fr.tys.emaplateforme.world.blocks.CollidableBlock;
import fr.tys.emaplateforme.world.blocks.InteractiveBlock;

import java.util.function.Consumer;

import static fr.tys.emaplateforme.util.Constants.*;

public class CharacterMovementController {

    private final EMAPlateforme game;
    private final World world;
    private final Character character;
    private final GameInputsManager gameInputsManager;
    private boolean jumpReleased = true;
    private long jumpKeyPressedTimestamp;

    public CharacterMovementController(EMAPlateforme game, World world, GameInputsManager gameInputsManager) {
        this.game = game;
        this.world = world;
        this.character = world.getCharacter();
        this.gameInputsManager = gameInputsManager;
    }

    /**
     * The main update method
     **/
    public void update(float delta) {
        processInputs();
        updatePosition(delta);
        checkBlockCollisions();
        checkWorldBounds();
        character.update();
    }

    /**
     * Moves the character depending on the pressed keys
     */
    private void processInputs() {
        if (gameInputsManager.getKeyState(EMAControl.JUMP)) {
            if (!character.isJumping() && jumpReleased) {
                jumpKeyPressedTimestamp = System.currentTimeMillis();
                jumpReleased = false;
                character.jump();
                character.setState(CharacterState.MOVING);
                character.getVelocity().y += PLAYER_MAXIMUM_JUMPING_SPEED;
            } else if ((System.currentTimeMillis() - jumpKeyPressedTimestamp) <= MAXIMUM_JUMP_TIME_PRESS) {
                character.getVelocity().y = PLAYER_MAXIMUM_JUMPING_SPEED;
            }
        } else {
            jumpReleased = true;
        }

        if (gameInputsManager.getKeyState(EMAControl.LEFT)) {
            character.setFacingLeft();
            character.getAcceleration().x = -SIDEWAYS_ACCELERATION * (character.isJumping() ? 0.5f : 1);
            character.setState(CharacterState.MOVING);
        } else if (gameInputsManager.getKeyState(EMAControl.RIGHT)) {
            character.setFacingRight();
            character.getAcceleration().x = SIDEWAYS_ACCELERATION * (character.isJumping() ? 0.5f : 1);
            character.setState(CharacterState.MOVING);
        } else {
            character.getAcceleration().x = 0;
        }
    }

    /**
     * Sets limits in order to the map size (independently of the world)
     */
    private void checkWorldBounds() {
        if (character.getPosition().y < -10) {
            game.pause();
            character.incrementDeath();
            game.setDeathScreen();
        } else if (character.getPosition().y > world.getHeight()) {
            character.getPosition().set(character.getPosition().x, world.getHeight());
            character.getVelocity().set(character.getVelocity().x, 0);
        }

        if (character.getPosition().x < 0) {
            character.getPosition().set(0F, character.getPosition().y);
            character.getVelocity().set(0, character.getVelocity().y);
        } else if (character.getPosition().x > world.getWidth()) {
            character.getPosition().set(world.getWidth(), character.getPosition().y);
            character.getVelocity().set(0, character.getVelocity().y);
        }
    }

    /**
     * Collides character hitbox with block hitboxes
     */
    private void checkBlockCollisions() {
        Consumer<Block> cancelDown = block -> {
            if (block instanceof CollidableBlock) {
                CollidableBlock collidableBlock = (CollidableBlock) block;
                if (character.getPosition().y - block.getPosition().y < collidableBlock.getHitbox().getHeight()) {
                    character.cancelMovementY();
                    character.land();
                }
            }
            interact(block);
        };
        Consumer<Block> cancelUp = block -> {
            if (block instanceof CollidableBlock) {
                if (character.getPosition().y + PLAYER_HEIGHT >= block.getPosition().y) {
                    character.cancelMovementY();
                }
            }
            interact(block);
        };
        Consumer<Block> cancelLeft = block -> {
            if (block instanceof CollidableBlock) {
                CollidableBlock collidableBlock = (CollidableBlock) block;
                if (character.getPosition().x - block.getPosition().x < collidableBlock.getHitbox().getWidth()) {
                    character.cancelMovementX();
                }
            }
            interact(block);
        };
        Consumer<Block> cancelRight = block -> {
            if (block instanceof CollidableBlock) {
                if (character.getPosition().x + PLAYER_WIDTH >= block.getPosition().x) {
                    character.cancelMovementX();
                }
            }
            interact(block);
        };
        //Down
        world.getBlockAt(character.getPosition().x, character.getPosition().y).ifPresent(cancelDown);
        world.getBlockAt(character.getPosition().x + PLAYER_WIDTH, character.getPosition().y).ifPresent(cancelDown);
        //Up
        world.getBlockAt(character.getPosition().x, character.getPosition().y + PLAYER_HEIGHT).ifPresent(cancelUp);
        world.getBlockAt(character.getPosition().x + PLAYER_WIDTH, character.getPosition().y + PLAYER_HEIGHT).ifPresent(cancelUp);
        //Left
        world.getBlockAt(character.getPosition().x, character.getPosition().y).ifPresent(cancelLeft);
        world.getBlockAt(character.getPosition().x, character.getPosition().y + PLAYER_HEIGHT).ifPresent(cancelLeft);
        //Right
        world.getBlockAt(character.getPosition().x + PLAYER_WIDTH, character.getPosition().y).ifPresent(cancelRight);
        world.getBlockAt(character.getPosition().x + PLAYER_WIDTH, character.getPosition().y + PLAYER_HEIGHT).ifPresent(cancelRight);
    }

    public void interact(Block block) {
        if (block instanceof InteractiveBlock) {
            ((InteractiveBlock) block).interact(game);
        }
    }

    /**
     * Calculates the character physics
     */
    private void updatePosition(float delta) {
        /* Acceleration */
        character.getAcceleration().y = GRAVITY;
        character.getAcceleration().scl(delta);

        /* Velocity */
        character.getVelocity().add(character.getAcceleration());

        // Effect of inertia
        if (character.getAcceleration().x == 0 || Math.signum(character.getAcceleration().x) != Math.signum(character.getVelocity().x)) {
            character.getVelocity().x *= DAMP;
        }
        // Speed threshold
        if (Math.abs(character.getVelocity().x) > PLAYER_MAXIMUM_MOVING_SPEED) {
            character.getVelocity().x = Math.signum(character.getVelocity().x) * PLAYER_MAXIMUM_MOVING_SPEED;
        }

        /* Position */
        character.getPosition().add(character.getVelocity().x * delta, character.getVelocity().y * delta);
        resetState();
    }

    /**
     * Sets the player state to IDLE if its speed is zero
     */
    private void resetState() {
        if (character.isState(CharacterState.MOVING) && character.getVelocity().len2() == 0f) {
            character.setState(CharacterState.IDLE);
        }
    }

    /**
     * Resets the keys state
     */
    public void reset() {
        gameInputsManager.reset();
    }
}
